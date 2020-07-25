package employee.schedule.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Utility {
	
	public Utility() {
		
	}

	public double getRandomDesiredEarning() {
		return (double) ((Math.random() * (90 - 30)) + 30);
	}

	public double getRandomHourlyEarning() {
		return (double) ((Math.random() * (10 - 5)) + 5);
	}

	public LocalDateTime getRandomLocalDateTime() {
		LocalDateTime startTime = LocalDateTime.of(2020, Month.AUGUST, 1, 00, 00);		
		ZonedDateTime zoneOfStartTime = startTime.atZone(ZoneId.of("Europe/Paris"));
		long beginTimeInMilliseconds = zoneOfStartTime.toInstant().toEpochMilli();

		LocalDateTime endTime = LocalDateTime.of(2020, Month.AUGUST, 8, 00, 00);		
		ZonedDateTime zoneOfEndTime = endTime.atZone(ZoneId.of("Europe/Paris"));
		long endTimeInMilliseconds = zoneOfEndTime.toInstant().toEpochMilli();

		LocalDateTime ldt = Instant
				.ofEpochMilli(getRandomTimeInMillisBetweenTwoDates(beginTimeInMilliseconds, endTimeInMilliseconds))
				.atZone(ZoneId.systemDefault()).toLocalDateTime();

		return ldt;
	}

	private static long getRandomTimeInMillisBetweenTwoDates(long beginTimeInMilliseconds, long endTimeInMilliseconds) {
		long diff = endTimeInMilliseconds - beginTimeInMilliseconds + 1;
		return beginTimeInMilliseconds + (long) (Math.random() * diff);
	}
}
