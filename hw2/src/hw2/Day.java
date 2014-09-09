package hw2;

/**
 * Enum for days of the month, 1st-31st
 * 
 * @author Matthew Swartwout
 *
 */
public enum Day 
{
	FIRST(1), 
	SECOND(2), 
	THIRD(3), 
	FOURTH(4), 
	FIFTH(5), 
	SIXTH(6), 
	SEVENTH(7), 
	EIGHTH(8), 
	NINTH(9), 
	TENTH(10), 
	ELEVENTH(11), 
	TWELFTH(12), 
	THIRTEENTH(13), 
	FOURTEENTH(14), 
	FIFTEENTH(15), 
	SIXTEENTH(16), 
	SEVENTEENTH(17), 
	EIGHTEENTH(18), 
	NINETEENTH(19), 
	TWENTIETH(20), 
	TWENTYFIRST(21), 
	TWENTYSECOND(22), 
	TWENTYTHIRD(23), 
	TWENTYFOURTH(24), 
	TWENTYFIFTH(25), 
	TWENTYSIXTH(26), 
	TWENTYSEVENTH(27), 
	TWENTYEIGHT(28), 
	TWENTYNINTH(29),
	THIRTIETH(30), 
	THIRTYFIRST(31);
	
	private int value; //Each day has a numerical value associated with it
	
	/**
	 * Sets the value of the day
	 * 
	 * @param value The numerical value of the day
	 */
	private Day(int value)
	{
		this.value = value;
	}
	
	/**
	 * Getter for the day's value
	 * 
	 * @return The numerical value of the day
	 */
	public int getValue()
	{
		return value;
	}
	
	
}