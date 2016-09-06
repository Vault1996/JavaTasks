package by.epam.cinemarating.function;

import java.sql.Timestamp;

public class DateConverter {
	public static Timestamp stringToTimestamp(String time) {
		time = time.replace('T',' ');
		time = time + ":00";
		return Timestamp.valueOf(time);
	}
}
