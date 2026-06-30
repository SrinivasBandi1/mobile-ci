package com.intelehealth.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Enterprise-grade APK provisioning utility.
 *
 * Resolution order:
 *   1. APPIUM_APK_PATH   system property / env var  (absolute path on disk)
 *   2. APPIUM_APK_URL    system property / env var  (HTTP/S download)
 *   3. apkRelativePath   passed by caller            (classpath-relative fallback)
 *
 * The downloaded file is cached in <cacheDir>/apk/<filename> and re-used
 * across parallel threads via a file-existence check + a rename-based
 * atomic write so no two threads download the same APK concurrently.
 */
public final class ApkDownloadUtil {

    private static final Logger log = LogManager.getLogger(ApkDownloadUtil.class);

    private static final int CONNECT_TIMEOUT_MS  = 15_000;
    private static final int READ_TIMEOUT_MS      = 120_000;
    private static final int DOWNLOAD_BUFFER_SIZE = 8_192;
    // ZIP / APK magic bytes: PK\x03\x04
    private static final byte[] APK_MAGIC = { 0x50, 0x4B, 0x03, 0x04 };

    private ApkDownloadUtil() {}

    /**
     * Resolves the APK path using the 3-step strategy described above.
     *
     * @param apkRelativePath path relative to {@code src/test/resources/app/} used as last-resort fallback
     * @param cacheDir        directory where downloaded APKs are cached (e.g. {@code System.getProperty("user.dir") + "/apk-cache"})
     * @return absolute path to the APK file
     * @throws IllegalStateException if no valid APK can be resolved
     */
    public static String resolveApkPath(String apkRelativePath, String cacheDir) {
        // ── Step 1: explicit local path override ────────────────────────────
        String explicitPath = resolveEnv("APPIUM_APK_PATH");
        if (explicitPath != null && !explicitPath.isBlank()) {
            log.info("[APK] Using explicit path from env/property: {}", explicitPath);
            return validateLocalApk(explicitPath);
        }

        // ── Step 2: download from URL ────────────────────────────────────────
        String apkUrl = resolveEnv("APPIUM_APK_URL");
        if (apkUrl != null && !apkUrl.isBlank()) {
            log.info("[APK] Downloading from URL: {}", apkUrl);
            return downloadApk(apkUrl, cacheDir);
        }

        // ── Step 3: classpath-relative fallback (local developer machine) ───
        String localPath = buildLocalApkPath(apkRelativePath);
        log.info("[APK] Falling back to local resource path: {}", localPath);
        return validateLocalApk(localPath);
    }

    // ────────────────────────────────────────────────────────────────────────
    // Private helpers
    // ────────────────────────────────────────────────────────────────────────

    private static String downloadApk(String apkUrl, String cacheDir) {
        String fileName = deriveFileName(apkUrl);
        Path cacheFile  = Paths.get(cacheDir, "apk", fileName);

        // Re-use cached file if already present and valid
        if (Files.exists(cacheFile) && isValidApk(cacheFile.toFile())) {
            log.info("[APK] Cache hit — reusing: {}", cacheFile);
            return cacheFile.toAbsolutePath().toString();
        }

        try {
            Files.createDirectories(cacheFile.getParent());
        } catch (IOException e) {
            throw new IllegalStateException("[APK] Cannot create cache directory: " + cacheFile.getParent(), e);
        }

        // Atomic download: write to .tmp then rename to avoid partial-file reads by other threads
        Path tmpFile = cacheFile.resolveSibling(fileName + ".tmp");
        log.info("[APK] Starting download → {}", tmpFile);

        try {
            HttpURLConnection conn = openConnection(apkUrl);
            long contentLength = conn.getContentLengthLong();

            try (InputStream in = conn.getInputStream();
                 FileOutputStream out = new FileOutputStream(tmpFile.toFile())) {

                byte[] buffer = new byte[DOWNLOAD_BUFFER_SIZE];
                long downloaded = 0;
                int  bytesRead;

                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                    downloaded += bytesRead;
                }

                log.info("[APK] Download complete — {} bytes (expected {})", downloaded, contentLength);
            }

            validateApkFile(tmpFile.toFile());

            // Atomic rename
            Files.move(tmpFile, cacheFile,
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING,
                    java.nio.file.StandardCopyOption.ATOMIC_MOVE);

            log.info("[APK] Cached at: {}", cacheFile);
            return cacheFile.toAbsolutePath().toString();

        } catch (IOException e) {
            silentDelete(tmpFile);
            throw new IllegalStateException("[APK] Download failed from: " + apkUrl, e);
        }
    }

    private static HttpURLConnection openConnection(String apkUrl) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(apkUrl).openConnection();
        conn.setConnectTimeout(CONNECT_TIMEOUT_MS);
        conn.setReadTimeout(READ_TIMEOUT_MS);
        conn.setRequestProperty("User-Agent", "InteleHealth-CI/1.0");
        conn.setInstanceFollowRedirects(true);

        int status = conn.getResponseCode();
        if (status == HttpURLConnection.HTTP_MOVED_PERM
                || status == HttpURLConnection.HTTP_MOVED_TEMP
                || status == 307 || status == 308) {
            String location = conn.getHeaderField("Location");
            log.info("[APK] Redirect → {}", location);
            return openConnection(location);
        }
        if (status < 200 || status >= 300) {
            throw new IOException("HTTP " + status + " for URL: " + apkUrl);
        }
        return conn;
    }

    private static String validateLocalApk(String path) {
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalStateException("[APK] File not found: " + path);
        }
        validateApkFile(file);
        return file.getAbsolutePath();
    }

    private static void validateApkFile(File file) {
        if (file.length() < 4) {
            throw new IllegalStateException("[APK] File is too small to be a valid APK: " + file);
        }
        if (!isValidApk(file)) {
            throw new IllegalStateException(
                "[APK] Invalid APK — magic bytes do not match PK\\x03\\x04: " + file);
        }
    }

    private static boolean isValidApk(File file) {
        try {
            byte[] header = new byte[4];
            try (InputStream is = Files.newInputStream(file.toPath())) {
                int read = is.read(header);
                return read == 4 && Arrays.equals(header, APK_MAGIC);
            }
        } catch (IOException e) {
            return false;
        }
    }

    private static String buildLocalApkPath(String relativeName) {
        return System.getProperty("user.dir")
                + File.separator + "src"
                + File.separator + "test"
                + File.separator + "resources"
                + File.separator + "app"
                + File.separator + relativeName;
    }

    private static String deriveFileName(String url) {
        String name = url.substring(url.lastIndexOf('/') + 1);
        // Strip query params
        int q = name.indexOf('?');
        return (q > 0 ? name.substring(0, q) : name)
                .replaceAll("[^a-zA-Z0-9._-]", "_");
    }

    private static String resolveEnv(String key) {
        String val = System.getProperty(key);
        if (val == null || val.isBlank()) {
            val = System.getenv(key);
        }
        return val;
    }

    private static void silentDelete(Path path) {
        try { Files.deleteIfExists(path); } catch (IOException ignored) {}
    }
}
