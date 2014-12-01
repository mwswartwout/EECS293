package hw4;

public class Friend 
{
	private User user;
	private int distance;
	private Validity valid;
	
	/**
	 * Constructor creates invalid Friend
	 */
	public Friend()
	{
		valid = Validity.INVALID;
	}
	
	/**
	 * Sets the User for a Friend, and its distance
	 * 
	 * @param user The User to best set
	 * @param distance The distance to be sest
	 * @return Returns false if Friend is already valid, sets the user and distance and return true otherwise
	 */
	public boolean set(User user, int distance)
	{
		HelperMethods.checkForNullInput(user, distance);
		
		if (isValid())
		{
			return false;
		}
		
		else
		{
			this.user = user;
			this.distance = distance;
			valid = Validity.VALID;	
			
			return true;
		}
	}
	
	/**
	 * Gets the User of a Friend
	 * 
	 * @return Returns the User of a Friend
	 * @throws UninitializedObjectException Throws exception if the Friend is invalid
	 */
	public User getUser() throws UninitializedObjectException
	{
		checkForValid();
		
		return user;
	}
	
	/**
	 * Gets the distance of a Friend
	 * 
	 * @return Returns the distance of a Friend
	 * @throws UninitializedObjectException Throws exception is the Friend is invalid
	 */
	public int getDistance() throws UninitializedObjectException
	{
		checkForValid();
		
		return distance;
	}
	
	/**
	 * Returns a user readable string representing the Friend
	 * 
	 * @return The string that represents the Friend
	 */
	public String toString()
	{
		if (valid == Validity.INVALID)
			return "Invalid Friend";
		else
			return "This friend's user is " + user + " and its distance is " + distance + ".";
	}
	
	/**
	 * Checks if a Friend is valid
	 * 
	 * @throws UninitializedObjectException If the link isn't valid if throws an exception
	 */
	private void checkForValid() throws UninitializedObjectException
	{
		if (valid == Validity.INVALID)
			throw new UninitializedObjectException("The Friend is not valid");
	}
	
	/**
	 * Checks if the Friend is valid
	 * @return True if the Friend is valid, false otherwise
	 */
	private boolean isValid()
	{
		if (valid == Validity.VALID)
			return true;
		else
			return false;
	}
}
