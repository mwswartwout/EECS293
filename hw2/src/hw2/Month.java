package hw2;

/**
 * Enum for months, January - December
 * 
 * @author Matthew Swartwout
 *
 */
public enum Month 
{
	JANUARY(1), 
	FEBRUARY(2), 
	MARCH(3), 
	APRIL(4), 
	MAY(5), 
	JUNE(6), 
	JULY(7), 
	AUGUST(8), 
	SEPTEMBER(9), 
	OCTOBER(10), 
	NOVEMBER(11), 
	DECEMBER(12);
	
	private int value; //Each month has a numerical value associated with it
	
	/**
	 * Sets the value of the month
	 * 
	 * @param value The value of the month
	 */
	private Month(int value)
	{
		this.value = value;
	}
	
	/**
	 * Gets the value of the month
	 * 
	 * @return The numerical value of the month
	 */
	public int getValue()
	{
		return value;
	}
}
