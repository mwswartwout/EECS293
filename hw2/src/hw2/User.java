package hw2;

/**
 * {@code User} has an ID that is a String, and a status that is a Validity
 * 
 * @author Matthew Swartwout
 *
 */
public class User 
{
	private String ID;
	private Validity status;
	
	/**
	 * Constructs an empty, invalid {@code User}
	 */
	public User()
	{
		status = Validity.INVALID;
	}
	
	/**
	 * Sets the ID of a {@code User}
	 * 
	 * @param ID The desired ID for the {@code User}
	 * @return true if {@code User} did not exist in network with same ID, false if same ID did exist
	 */
	public boolean setID(String ID)
	{
		//Throw exception if ID is null
		if (ID == null)
			throw new NullPointerException();
		
		//If a user exists with the same ID, return false
		else if (SocialNetwork.isMember(ID))
			return false;
		
		//If the ID isn't null, and no User exists with that ID, return true
		else
		{
			this.ID = ID; //Set ID to desired ID
			status = Validity.VALID; //Set status to Valid
			return true;
		}
	}
	
	/**
	 * Get's the ID of a User
	 * 
	 * @return The User's ID if the User is valid, null is User is invalid
	 */
	public String getID()
	{
		if (isValid())
			return ID;
		else
			return null;
	}
	
	/**
	 * Checks if a user is valid
	 * 
	 * @return true if valid, false otherwise
	 */
	public boolean isValid()
	{
		if (status == Validity.VALID)
			return true;
		else
			return false;
	}
	
	/**
	 * Prints a human readable String with the User's ID number, or an error message
	 */
	public String toString()
	{
		if (isValid())
			return "Valid user: ID = " + ID +".";
		else
			return "Invalid User: Unitialized ID";
	}
}
