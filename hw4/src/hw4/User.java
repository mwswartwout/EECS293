package hw4;

/**
 * {@code User} has an ID that is a String, and a status that is a Validity
 * 
 * @author Matthew Swartwout, mws85
 *
 */
public class User
{
	private String ID, firstName, middleName, lastName, email, phoneNumber;
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
	public boolean setID(String ID) throws NullPointerException, UninitializedObjectException
	{
		HelperMethods.checkForNullInput(ID);
		
		//If the ID isn't null, and no User exists with that ID, return true
		this.ID = ID; //Set ID to desired ID
		status = Validity.VALID; //Set status to Valid
		return true;
	}
	
	/**
	 * Gets the ID of a User
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
			return "Invalid User: Uninitialized ID";
	}
	
	private void checkForValid() throws UninitializedObjectException
	{
		if (!isValid())
			throw new UninitializedObjectException();
	}
	
	/**
	 * Sets the firstName of the User
	 * @param name The first name to be set
	 * @throws UninitializedObjectException throws exception if user is not valid
	 * @throws NullPointerException throws exception if inputs are null
	 */
	public User setFirstName(String name) throws UninitializedObjectException, NullPointerException
	{
		HelperMethods.checkForNullInput(name);
		checkForValid();
		
		firstName = name;
		return this;
	}
	
	/**
	 * Sets the middleName of the User
	 * @param name The middleName to be set
	 * @throws UninitializedObjectException throws exception if user is not valid
	 * @throws NullPointerException throws exception if any inputs are null
	 */
	public User setMiddleName(String name) throws UninitializedObjectException, NullPointerException
	{
		HelperMethods.checkForNullInput(name);
		checkForValid();
		
		middleName = name;
		return this;
	}
	
	/**
	 * Sets the lastName of the User
	 * @param name The lastName to be set
	 * @throws UninitializedObjectException throws exception if user is not valid
	 * @throws NullPointerException throws exception if any inputs are null
	 */
	public User setLastName(String name) throws UninitializedObjectException, NullPointerException
	{
		HelperMethods.checkForNullInput(name);
		checkForValid();
		
		lastName = name;
		return this;
	}
	
	/**
	 * Sets the email of the User
	 * @param name The email to be set
	 * @throws UninitializedObjectException throws exception if user is not valid
	 * @throws NullPointerException throws exception if any inputs are null
	 */
	public User setEmail(String name) throws UninitializedObjectException, NullPointerException
	{
		HelperMethods.checkForNullInput(name);
		checkForValid();
		
		email = name;
		return this;
	}
	
	/**
	 * Sets the phoneNumber of the User
	 * @param name The phoneNumber to be set
	 * @throws UninitializedObjectException throws exception if user is not valid
	 * @throws NullPointerException throws exception if any inputs are null
	 */
	public User setPhoneNumber(String name) throws UninitializedObjectException, NullPointerException
	{
		HelperMethods.checkForNullInput(name);
		checkForValid();
		
		phoneNumber = name;
		return this;
	}
	
	/**
	 * Getter for a User's firstName
	 * @return The firstName of the User
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Getter for a User's middleName
	 * @return The middleName of the User
	 */
	public String getMiddleName()
	{
		return middleName;
	}
	
	/**
	 * Getter for a User's lastName
	 * @return The lastName of the User
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * Getter for a User's email
	 * @return The email of the User
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 * Getter for a User's phoneNumber
	 * @return The phoneNumber of the User
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
}
