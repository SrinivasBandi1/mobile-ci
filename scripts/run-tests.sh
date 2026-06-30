#!/usr/bin/env bash
# ─────────────────────────────────────────────────────────────────────────────
# run-tests.sh  —  Called by android-emulator-runner after emulator boots.
#
# Env vars injected by the workflow:
#   SUITE          TestNG suite file (default: testng.xml)
#   EMULATOR       true/false
#   PLATFORM_NAME  Android
#   UDID           emulator-5554
#   DEVICE_NAME    Pixel_5_API_33
#   SYSTEM_PORT    10000
# ─────────────────────────────────────────────────────────────────────────────
set -euo pipefail

SUITE="${SUITE:-testng.xml}"
EMULATOR_FLAG="${EMULATOR:-true}"
PLATFORM="${PLATFORM_NAME:-Android}"
UDID="${UDID:-emulator-5554}"
DEVICE="${DEVICE_NAME:-Pixel_5_API_33}"
PORT="${SYSTEM_PORT:-10000}"
MAX_APPIUM_WAIT=60   # seconds

echo "════════════════════════════════════════════════════════"
echo "  Intelehealth Mobile CI  —  Test Execution"
echo "  Suite       : $SUITE"
echo "  Emulator    : $EMULATOR_FLAG"
echo "  Device      : $DEVICE  ($UDID)"
echo "  Appium port : 4723"
echo "════════════════════════════════════════════════════════"

# ── 1. Start Appium server in background ─────────────────────────────────────
echo "[Appium] Starting server..."
appium server --address 127.0.0.1 --port 4723 --log appium-server.log --log-timestamp --log-no-colors &
APPIUM_PID=$!
echo "[Appium] PID: $APPIUM_PID"

# ── 2. Wait for Appium to be ready ───────────────────────────────────────────
echo "[Appium] Waiting for readiness..."
ELAPSED=0
until curl -sf http://127.0.0.1:4723/status | grep -q '"ready":true'; do
  if [ $ELAPSED -ge $MAX_APPIUM_WAIT ]; then
    echo "[Appium] ERROR: server did not become ready after ${MAX_APPIUM_WAIT}s"
    cat appium-server.log || true
    exit 1
  fi
  sleep 2
  ELAPSED=$((ELAPSED + 2))
done
echo "[Appium] Ready after ${ELAPSED}s"

# ── 3. Confirm emulator is visible to ADB ────────────────────────────────────
echo "[ADB] Devices:"
adb devices

# ── 4. Run TestNG suite via Maven ─────────────────────────────────────────────
echo "[Maven] Running suite: $SUITE"
mvn test \
  -B \
  --no-transfer-progress \
  -Dsurefire.suiteXmlFiles="$SUITE" \
  -DEMULATOR="$EMULATOR_FLAG" \
  -DPLATFORM_NAME="$PLATFORM" \
  -DUDID="$UDID" \
  -DDEVICE_NAME="$DEVICE" \
  -DSYSTEM_PORT="$PORT" \
  -DAPK_PATH="$(pwd)/src/test/resources/app/ida.apk" \
  -Dmaven.test.failure.ignore=true \
  || true   # Don't fail the script on test failures — let the report stage decide

# ── 5. Stop Appium ────────────────────────────────────────────────────────────
echo "[Appium] Stopping server (PID $APPIUM_PID)"
kill "$APPIUM_PID" 2>/dev/null || true

echo "[Done] Test execution complete."
