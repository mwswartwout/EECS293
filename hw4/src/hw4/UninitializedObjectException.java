package hw4;
/**
 * UninitializedObjectException class with basic instructuors extended from Exception
 * 
 * @author Matthew Swartwout
 *
 */
@SuppressWarnings("serial") //Exception will not be serialized, so safe to ignore
public class UninitializedObjectException extends Exception
{
	/**
	 * Null constructor
	 */
	public UninitializedObjectException()
	{
		
	}
	
	/**
	 * Constructor with only a message
	 * 
	 * @param message Exception detail message
	 */
	public UninitializedObjectException(String message)
	{
		super(message);
	}
	
	/**
	 * Constructor with only a cause
	 * 
	 * @param cause Exception cause
	 */
	public UninitializedObjectException(Throwable cause)
	{
		super(cause);
	}
	
	/**
	 * Constructor with a message and cause
	 * 
	 * @param message Exception detail message
	 * @param cause Exception cause
	 */
	public UninitializedObjectException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	
}
