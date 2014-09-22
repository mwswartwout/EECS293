package hw2;

public class HelperMethods 
{
	/**
	 * Checks if any inputs to a method are null
	 * 
	 * @param arguments varargs from a method
	 * @throws NullPointerException Throws exception if any of the arguments are null
	 */
	public static void checkForNullInput(Object... arguments) throws NullPointerException
	{
		for(Object element : arguments)
		{
			if (element == null)
				throw new NullPointerException("One of the inputs was null");
		}
	}
}
