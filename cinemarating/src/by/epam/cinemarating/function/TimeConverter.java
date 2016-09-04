package by.epam.cinemarating.function;

import java.sql.Timestamp;

public class TimeConverter {
	private static final String DAYS = " days, ";
	private static final String HOURS = " hours, ";
	private static final String MINUTES = " minutes, ";
	private static final String SECONDS = " seconds.";

	public static String findDifference(Timestamp firstTimestamp, Timestamp secondTimestamp) {
		long diff = firstTimestamp.getTime() - secondTimestamp.getTime();

		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);

		return diffDays + DAYS +
				diffHours + HOURS +
				diffMinutes + MINUTES +
				diffSeconds + SECONDS;
	}
}
