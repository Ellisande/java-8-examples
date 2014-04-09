package sf.examples;
import static org.junit.Assert.assertEquals;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

/**
 * Use local time.
 * Show addition on local time.
 * Show local date.
 * Show addition / subtraction on local date.
 * Show problematic addition on local date.
 * Show using a period for addition.
 * Show using clock for testing.
 * Show immutable example.
 * Show date time formatter.
 * @param args
 */
public class JavaTimeTest {
	
	Clock myZone = Clock.systemDefaultZone();
	Clock dayLightSavings = Clock.fixed(Instant.parse("2014-03-09T07:00:00.00Z"), ZoneId.of("America/Chicago"));
	Clock marchFifth = Clock.fixed(Instant.parse("2014-03-05T07:00:00.00Z"), ZoneId.of("America/Phoenix"));

	@Test
	public void localTimeTest(){
		LocalTime now = LocalTime.now();
		System.out.println("Current local time "+now);
	}
	
	@Test
	public void addToLocalTime(){
		LocalTime time = LocalTime.now();
		LocalTime fiveHoursFromNow = time.plus(5, ChronoUnit.HOURS);
		
		System.out.println("Five hours from now "+fiveHoursFromNow);
	}
	
	@Test
	public void localDate(){
		LocalDate today = LocalDate.now();
		
		System.out.println("Today is " + today);
	}
	
	@Test
	public void mathOnLocalDate(){
		LocalDate fiveDaysFromNow = LocalDate.now().plus(5, ChronoUnit.DAYS);
		
		System.out.println("Five days from now is "+fiveDaysFromNow);
		
		LocalDate fiveDaysAgo = fiveDaysFromNow.minus(10, ChronoUnit.DAYS);
		
		System.out.println("Five days ago is " + fiveDaysAgo);
	}
	
	//TODO: Work on this.
	@Test
	public void problematicMathOnLocalTime(){
		LocalTime justBeforeDaylightSavings = LocalTime.now(dayLightSavings);
		
		System.out.println("Just before daylight savings it was "+ justBeforeDaylightSavings);
		
		LocalTime aDayLater = justBeforeDaylightSavings.plus(24, ChronoUnit.HOURS);
		
		System.out.println("One day later it is " + aDayLater);
	}
	
	@Test
	public void problematicMathOnLocalDate(){
		LocalDate march5th = LocalDate.now(marchFifth);
		
		System.out.println("Jumping to March 5th "+march5th);
		assertEquals(3, march5th.getMonthValue());
		assertEquals(5, march5th.getDayOfMonth());
		
		LocalDate hopefullyApril5th = march5th.plus(1, ChronoUnit.MONTHS);
		LocalDate hopefullyMay5th = hopefullyApril5th.plus(1, ChronoUnit.MONTHS);
		LocalDate maybeApril4th = march5th.plus(30, ChronoUnit.DAYS);
		
		System.out.println("Hopefully a month later will be the 5th day of the month "+hopefullyApril5th);
		System.out.println("Hopefully a month later will be the 5th day of the month "+hopefullyMay5th);
		
		assertEquals(4, hopefullyApril5th.getMonthValue());
		assertEquals(5, hopefullyApril5th.getDayOfMonth());
		
		assertEquals(5, hopefullyMay5th.getMonthValue());
		assertEquals(5, hopefullyMay5th.getDayOfMonth());
		
		
		System.out.println("If I explicitly add 30 days I want April 4th " + maybeApril4th);
		
		assertEquals(4, maybeApril4th.getMonthValue());
		assertEquals(4, maybeApril4th.getDayOfMonth());
	}
	
	@Test
	public void addWithPeroid(){
		LocalDate march5th = LocalDate.now(marchFifth);
		
		System.out.println("Jumping to March 31st "+march5th);
		assertEquals(3, march5th.getMonthValue());
		assertEquals(5, march5th.getDayOfMonth());
		
		Period oneMonth = Period.ofMonths(1);
		Period thirtyDays = Period.ofDays(30);
		
		LocalDate hopefullyApril5th = march5th.plus(oneMonth);
		LocalDate hopefullyMay5th = hopefullyApril5th.plus(oneMonth);
		LocalDate maybeApril4th = march5th.plus(thirtyDays);
		
		System.out.println("Hopefully a month later will be the 5th day of the month "+hopefullyApril5th);
		System.out.println("Hopefully a month later will be the 5th day of the month "+hopefullyMay5th);
		
		assertEquals(4, hopefullyApril5th.getMonthValue());
		assertEquals(5, hopefullyApril5th.getDayOfMonth());
		
		assertEquals(5, hopefullyMay5th.getMonthValue());
		assertEquals(5, hopefullyMay5th.getDayOfMonth());
		
		
		System.out.println("If I explicitly add 30 days I want April 4th " + maybeApril4th);
		
		assertEquals(4, maybeApril4th.getMonthValue());
		assertEquals(4, maybeApril4th.getDayOfMonth());
	}
	
	
	@Test
	public void immutableObjectExample(){
		SubClass holderClass = new SubClass();
		LocalDate today = holderClass.getDate();
		
		today.plus(5, ChronoUnit.MONTHS);
		
		assertEquals(3, holderClass.getDate().getMonthValue());
	}
	
	@Test
	public void dateTimeForamtter(){
		DateTimeFormatter formatIt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		OffsetDateTime today = OffsetDateTime.now();
		
		System.out.println("mm-dd-yyyy format of today "+today.format(formatIt));
		
		DateTimeFormatter crazyFormat = new DateTimeFormatterBuilder().appendPattern("yyyy").appendInstant().toFormatter();
		
		System.out.println("Crazy format for displaying the date "+today.format(crazyFormat));
	}
	
	public  class SubClass {
		private LocalDate date = LocalDate.now(marchFifth);
		
		public LocalDate getDate(){
			return date;
		}
	}
}
