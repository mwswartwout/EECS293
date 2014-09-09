package hw2;

/**
 * {@code Date} contains a month, day, and year
 * 
 * @author Matthew Swartwout
 *
 */
public class Date 
{
		
	private Month month;
	private Day day;
	private int year;
	private Activity activity;
	
	/**
	 * Constructor, each {@code Date} must have a month, day, and year
	 * 
	 * @param month The month
	 * @param day 	The day
	 * @param year	The year
	 */
	public Date(Month month, Day day, int year)
	{
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
	/**
	 * Checks to see if a {@code Date}'s month/day combination is valid
	 * 
	 * @return true if valid, false if invalid
	 */
	public boolean isValid()
	{
		
		//Checks each month
		switch(month)
		{
			//Months with 31 days
			case JANUARY: 
			case MARCH:
			case MAY:
			case JULY:
			case AUGUST:
			case OCTOBER:
			case DECEMBER:
			{
				//Nothing required because these months have 31 days
			}
			
			//Months with 30 days
			case APRIL:
			case JUNE:
			case SEPTEMBER:
			case NOVEMBER:
			{
				//Day can't be greater than 30
				if ( day.getValue() == 31)
					return false;
			}
			
			//February is special due to leap year
			case FEBRUARY:
			{
				//Check for leap year
				if ((year % 4 == 0 && year % 100 != 0) || (year % 4 == 0 && year % 100 != 0 && year %400 == 0))
				{
					//Allow 29 days in leap year
					if ( day.getValue() > 29)
						return false;
				}
				
				//If not leap year then day can't be greater than 28
				else if (day.getValue() >28)
					return false;
			}
		
		}
		
		return true;
	}
	
	/**
	 * Checks if a date is before another date
	 * 
	 * @param date The date to be compared to
	 * @return True if date is before given date, false if it is after
	 */
	public boolean isBefore(Date date)
	{
		if (year < date.year) //If year is earlier then date is earlier
			return true;
		else if (year == date.year && month.getValue() < date.month.getValue()) //If year is same but month is earlier then date is earlier
			return true;
		else if (year == date.year && month.getValue() == date.month.getValue() && day.getValue() < date.day.getValue()) //If year is same and month is same but day is earlier then date is earlier
			return true;
		else //Else date is later
			return false;
	}
	
	/**
	 * Getter for {@code Month}
	 * 
	 * @return The date's month
	 */
	public Month getMonth()
	{
		return month;
	}
	
	/**
	 * Getter for {@code Day)
	 * 
	 * @return The date's day
	 */
	public Day getDay()
	{
		return day;
	}
	
	/**
	 * Getter for year
	 * 
	 * @return The date's year
	 */
	public int getYear()
	{
		return year;
	}
	
	public void setActivity(Activity activity)
	{
		this.activity = activity;
	}
	
	public Activity getActivity()
	{
		return activity;
	}
}
