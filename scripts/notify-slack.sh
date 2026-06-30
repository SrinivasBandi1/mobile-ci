#!/usr/bin/env bash
# ─────────────────────────────────────────────────────────────────────────────
# Slack Notification Script — Mobile Automation CI/CD
#
# Usage:
#   export SLACK_WEBHOOK_URL="https://hooks.slack.com/services/XXX/YYY/ZZZ"
#   ./scripts/notify-slack.sh
#
# Env vars (all provided by GitHub Actions workflow):
#   SLACK_WEBHOOK_URL   required — Incoming Webhook URL from Slack App
#   TEST_STATUS         PASSED | FAILED
#   TOTAL               total test count
#   PASSED_COUNT        passed count
#   FAILED_COUNT        failed count
#   BROKEN_COUNT        broken count
#   SKIPPED_COUNT       skipped count
#   REPORT_URL          GitHub Pages report URL
#   GITHUB_RUN_NUMBER   workflow run number
#   GITHUB_RUN_ID       workflow run ID
#   GITHUB_REF_NAME     branch name
#   GITHUB_ACTOR        who triggered
#   GITHUB_REPOSITORY   owner/repo
#   GITHUB_SERVER_URL   https://github.com
# ─────────────────────────────────────────────────────────────────────────────
set -euo pipefail

# ── Defaults ────────────────────────────────────────────────────────────────
TEST_STATUS="${TEST_STATUS:-UNKNOWN}"
TOTAL="${TOTAL:-0}"
PASSED_COUNT="${PASSED_COUNT:-0}"
FAILED_COUNT="${FAILED_COUNT:-0}"
BROKEN_COUNT="${BROKEN_COUNT:-0}"
SKIPPED_COUNT="${SKIPPED_COUNT:-0}"
REPORT_URL="${REPORT_URL:-}"
RUN_URL="${GITHUB_SERVER_URL:-https://github.com}/${GITHUB_REPOSITORY:-}/actions/runs/${GITHUB_RUN_ID:-}"

# ── Color + emoji by status ──────────────────────────────────────────────────
if [[ "$TEST_STATUS" == "PASSED" ]]; then
  COLOR="#2EB886"
  STATUS_EMOJI=":white_check_mark:"
else
  COLOR="#E01E5A"
  STATUS_EMOJI=":x:"
fi

# ── Build payload ────────────────────────────────────────────────────────────
PAYLOAD=$(cat <<EOF
{
  "attachments": [
    {
      "color": "${COLOR}",
      "blocks": [
        {
          "type": "header",
          "text": {
            "type": "plain_text",
            "text": "${STATUS_EMOJI} Intelehealth Mobile Automation — ${TEST_STATUS}"
          }
        },
        {
          "type": "section",
          "fields": [
            { "type": "mrkdwn", "text": "*Branch:*\n\`${GITHUB_REF_NAME:-N/A}\`" },
            { "type": "mrkdwn", "text": "*Run:*\n<${RUN_URL}|#${GITHUB_RUN_NUMBER:-?}>" },
            { "type": "mrkdwn", "text": "*Triggered by:*\n${GITHUB_ACTOR:-N/A}" },
            { "type": "mrkdwn", "text": "*Time:*\n$(date -u '+%Y-%m-%d %H:%M UTC')" }
          ]
        },
        {
          "type": "section",
          "fields": [
            { "type": "mrkdwn", "text": "*Total Tests:*\n${TOTAL}" },
            { "type": "mrkdwn", "text": "*Passed:*\n:large_green_circle: ${PASSED_COUNT}" },
            { "type": "mrkdwn", "text": "*Failed:*\n:red_circle: ${FAILED_COUNT}" },
            { "type": "mrkdwn", "text": "*Skipped:*\n:white_circle: ${SKIPPED_COUNT}" }
          ]
        },
        {
          "type": "actions",
          "elements": [
            {
              "type": "button",
              "text": { "type": "plain_text", "text": "View Allure Report" },
              "url": "${REPORT_URL}",
              "style": "primary"
            },
            {
              "type": "button",
              "text": { "type": "plain_text", "text": "View GitHub Run" },
              "url": "${RUN_URL}"
            }
          ]
        }
      ]
    }
  ]
}
EOF
)

# ── Send to Slack ────────────────────────────────────────────────────────────
if [[ -z "${SLACK_WEBHOOK_URL:-}" ]]; then
  echo "ERROR: SLACK_WEBHOOK_URL is not set. Skipping notification." >&2
  exit 0
fi

HTTP_STATUS=$(curl -s -o /dev/null -w "%{http_code}" \
  -X POST \
  -H "Content-Type: application/json" \
  -d "${PAYLOAD}" \
  "${SLACK_WEBHOOK_URL}")

if [[ "$HTTP_STATUS" -eq 200 ]]; then
  echo "Slack notification sent successfully (HTTP $HTTP_STATUS)"
else
  echo "ERROR: Slack notification failed (HTTP $HTTP_STATUS)" >&2
  exit 1
fi
