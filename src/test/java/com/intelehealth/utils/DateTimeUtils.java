package com.intelehealth.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateTimeUtils {

	private static final DateTimeFormatter SLOT_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private static final DateTimeFormatter SLOT_TIME_FORMATTER = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);

	private static final DateTimeFormatter SLOT_API_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yy");

	/**
	 * Returns today's date in dd/MM/yyyy format e.g. "28/04/2026"
	 */
	public static String getSlotDate(int daysFromToday) {
		return LocalDate.now().plusDays(daysFromToday).format(SLOT_DATE_FORMATTER);
	}

	/**
	 * Returns the day name for today or future date e.g. "Monday", "Tuesday"
	 */
	public static String getSlotDay(int daysFromToday) {
		return LocalDate.now().plusDays(daysFromToday).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	}

	/**
	 * Returns today's date in dd-MM-yy format for getAppointmentSlots API e.g.
	 * "28-04-26"
	 */
	public static String getTodayForSlotApi() {
		return LocalDate.now().format(SLOT_API_DATE_FORMATTER);
	}

	/**
	 * Returns tomorrow's date in dd-MM-yy format e.g. "29-04-26"
	 */
	public static String getTomorrowForSlotApi() {
		return LocalDate.now().plusDays(1).format(SLOT_API_DATE_FORMATTER);
	}

	/**
	 * Rounds current time to next 30-min slot. Also adjusts slotDate and slotDay in
	 * the appointment map if midnight crossover is detected.
	 */
	public static String getNextSlotTimeAndAdjustDate(java.util.Map<String, Object> appointment) {
		LocalTime now = LocalTime.now();
		int minute = now.getMinute();
		LocalTime rounded;

		if (minute < 30) {
			rounded = now.withMinute(30).withSecond(0).withNano(0);
		} else {
			rounded = now.plusHours(1).withMinute(0).withSecond(0).withNano(0);
		}

		boolean isNextDay = rounded.isBefore(now);

		if (isNextDay) {
			appointment.put("slotDate", getSlotDate(1));
			appointment.put("slotDay", getSlotDay(1));
		} else {
			appointment.put("slotDate", getSlotDate(0));
			appointment.put("slotDay", getSlotDay(0));
		}

		System.out.println("[DateTimeUtils] slotDate=" + appointment.get("slotDate") + " | slotDay="
				+ appointment.get("slotDay") + " | slotTime=" + rounded.format(SLOT_TIME_FORMATTER));

		return rounded.format(SLOT_TIME_FORMATTER);
	}
}