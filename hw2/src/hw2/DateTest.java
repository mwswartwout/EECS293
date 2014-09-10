package hw2;

import static org.junit.Assert.*;

import org.junit.Test;

public class DateTest {

	@Test
	public void testIsValid() 
	{
		//31 day test case
		Date validDate = new Date(Month.JANUARY, Day.THIRTYFIRST, 2014);
		assertTrue(validDate.isValid());
		
		//30 day test, valid
		validDate = new Date(Month.APRIL, Day.THIRTIETH, 2014);
		assertTrue(validDate.isValid());
		
		//30 day test, invalid
		validDate = new Date(Month.APRIL, Day.THIRTYFIRST, 2014);
		assertFalse(validDate.isValid());
		
		//29 day test, valid
		validDate = new Date(Month.FEBRUARY, Day.TWENTYNINTH, 2000);
		assertTrue(validDate.isValid());
		
		//28 day test, invalid
		validDate = new Date(Month.FEBRUARY, Day.TWENTYNINTH, 2001);
		assertFalse(validDate.isValid());
	}

	@Test
	public void testIsBefore() 
	{
		Date date = new Date(Month.FEBRUARY, Day.FIFTH, 2014);
		
		//Previous year test
		Date year = new Date(Month.FEBRUARY, Day.FIFTH, 2013);
		assertTrue(year.isBefore(date));
		
		//Previous Month test
		Date month = new Date(Month.JANUARY, Day.FIFTH, 2014);
		assertTrue(month.isBefore(date));
		
		//Previous Day test
		Date day = new Date(Month.FEBRUARY, Day.FOURTH, 2014);
		assertTrue(day.isBefore(date));
		
		//Invalid test
		Date invalid = new Date(Month.JANUARY, Day.FIFTH, 2015);
		assertFalse(invalid.isBefore(date));
	}

}
