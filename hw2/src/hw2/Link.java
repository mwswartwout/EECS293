package hw2;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Link contains a set of two {@code Users}, an ArrayList of {@code Dates} and a {@code Validity} valid
 * 
 * @author Matthew Swartwout, mws85
 *
 */
public class Link 
{
	private Set<User> users;
	private ArrayList<Date> dates;
	private Validity valid;
	private Activity active;
	
	/**
	 * Empty constructor created INVALID {@code Link}
	 */
	public Link()
	{
		users = new HashSet<User>();
		dates = new ArrayList<Date>();
		valid = Validity.INVALID;
		active = Activity.INACTIVE;
	}
	
	/**
	 * Sets the two users for a link and marks it VALID
	 * 
	 * @param users The set of two users to be linked
	 * @return true if users were set, false if {@code Link} already existed or more than two {@code Users} were entered
	 * @throws NullPointerException if passed null inputs
	 */
	public boolean setUsers(Set<User> users, SocialNetworkStatus status) throws NullPointerException
	{
		HelperMethods.checkForNullInput(users);
		
		User[] userArray = users.toArray(new User[0]);
		
		if (isValid()) //Validity check
		{
			status.setStatus(Status.ALREADY_VALID);
			return false;
		}
		if (users.size() > 2) //Checks for more than two users
		{
			status.setStatus(Status.INVALID_USERS);
			return false;
		}
		if (!userArray[0].isValid() || !userArray[1].isValid()) //Make sure users are valid
		{
			status.setStatus(Status.INVALID_USERS);
			return false;
		}
		else
		{
			this.users = users; //Set users
			valid = Validity.VALID; //Set validity
			status.setStatus(Status.SUCCESS);
			return true;
		}
	}
	
	/**
	 * Checks whether or not a {@code Link} is valid
	 * 
	 * @return true if valid, false if not
	 */
	public boolean isValid()
	{		
		if (valid == Validity.VALID)
			return true;
		else
			return false;
	}
	
	/**
	 * Getter for the set of two {@code Users} of a {@code Link}
	 * 
	 * @return Set of {@code Users} if {@code Link} is valid
	 * @throws UninitializedObjectException throws exception if {@code Link} is INVALID
	 */
	public Set<User> getUsers() throws UninitializedObjectException
	{	
		checkForValid();
		
		return users;
	}
	
	/**
	 * Establishes a connection at a specified {@code Date}
	 * 
	 * @param date The date for a connection to be established
	 * @return true if connection was established, false if connection was already active or if date came before most recent connection
	 * @throws UninitializedObjectException throws exception if {@code Link} is invalid
	 * @throws NullPointerException if passed null inputs
	 */
	public boolean establish(Date date, SocialNetworkStatus status) throws UninitializedObjectException, NullPointerException
	{
		HelperMethods.checkForNullInput(date);
		checkForValid();
		
		if (isActive(date)) //Activity check
		{
			status.setStatus(Status.ALREADY_ACTIVE);
			return false;
		}
		else if (dates.size() != 0 && date.before(dates.get(dates.size() - 1))) //Check if date precedes most recent connection
		{
			status.setStatus(Status.INVALID_DATE);
			return false;
		}
		else 
		{
			active = Activity.ACTIVE;
			dates.add(date); //Add date to dates list
			status.setStatus(Status.SUCCESS);
			return true;
		}
	}
	
	/**
	 * Tears down a connection on a specified date
	 * 
	 * @param date The date at which to tear down the connection
	 * @return true if connection was torn down, false if connection was already inactive or if date precedes most recent connection
	 * @throws UninitializedObjectException throws exception if {@code Link} is invalid
	 * @throws NullPointerException if passed null inputs
	 */
	public boolean tearDown(Date date, SocialNetworkStatus status) throws UninitializedObjectException, NullPointerException
	{
		HelperMethods.checkForNullInput(date);
		checkForValid();
		
		if (!isActive(date)) //Activity check
		{
			status.setStatus(Status.ALREADY_INACTIVE);
			return false;
		}
		else if (date.before(dates.get(dates.size() - 1))) //Check if date precedes most recent connection
		{
			status.setStatus(Status.INVALID_DATE);
			return false;
		}
		else
		{
			active = Activity.INACTIVE; //Set activity for date
			dates.add(date); //Add date to dates list
			status.setStatus(Status.SUCCESS);
			return true;
		}
		
	}
	
	/**
	 * Checks to see whether a connection was active at a specific date
	 * 
	 * @param date The date to be checked
	 * @return true if connection was ACTIVE, false if connection was INACTIVE
	 * @throws UninitializedObjectException throws exception if {@code Link} is not valid
	 * @throws NullPointerException if passed null inputs
	 */
	public boolean isActive(Date date) throws UninitializedObjectException, NullPointerException
	{
		HelperMethods.checkForNullInput(date);
		checkForValid();
		
		if (dates.size() == 0)
			return false;
		else if (dates.indexOf(nextEvent(date)) % 2 == 1) // If the index of the next Event mod 2 is 1, then the link was inactive at the specified date
			return false;
		else if (nextEvent(date) == null && dates.size() % 2 == 0) //If the event input was the last event in the dates list, and dates.size() % 2 = 0 then link is inactive
			return false;
		else //If connection is valid + not inactive then it must be ACTIVE
			return true; 
	}
	
	/**
	 * Gets the first event in the dates list
	 * 
	 * @return the first event in the dates list
	 * @throws UninitializedObjectException throws exception is {@code Link} is not valid}
	 */
	public Date firstEvent() throws UninitializedObjectException
	{
		
		checkForValid();
			
		if (dates.size() == 0) //Check if there are no dates
			return null;
						
		else
			return dates.get(0);
	}
	
	/**
	 * Iterates through the dates list until it finds the first event after the specified date
	 * 
	 * @param date The date to find the next event for
	 * @return The next event after the specified date, or null if there is no next event
	 * @throws UninitializedObjectException throws exception if {@code Link} is invalid
	 * @throws NullPointerException if passed null inputs
	 */
	public Date nextEvent(Date date) throws UninitializedObjectException, NullPointerException
	{
		HelperMethods.checkForNullInput(date);
		checkForValid();
		
		if (dates.size() == 0)
			return null;
		
		Date iterateDate = dates.get(0);
		int iterateCount = 1;
			
		while (iterateDate.before(date)) //Checks if current iterateDate is before the specified date
		{
			if (iterateCount == dates.size()) //Stops loop if it reaches end of dates list and there is no valid next event
				return null;
				
			iterateDate = dates.get(iterateCount); //Moves on to next event
			iterateCount++;
		}
			
		return iterateDate;
	}
	
	/**
	 * Converts {@code Link} into a human readable String
	 * 
	 * @return Returns a string with the {@code Link's} {@code Users} and the event dates or an error message
	 */
	public String toString()
	{
		String print = "";
		
		if (isValid()) //Validity check
		{
			Iterator<User> iterator = users.iterator();
			
			while (iterator.hasNext()) //Iterates over users and adds their IDs to the string
				print += "User: " +iterator.next().getID() + ". ";
			
			Date currentDate;
			
			for (int i = 0; i < dates.size(); i++) //Iterates through the event list
			{
				currentDate = dates.get(i);
				print  += currentDate.toString(); //Adds Month + Date + Year to the string
			}
		}
		
		else
			print = "Invalid Link: Uninitialized IDs"; //Prints error if link is invalid
		
		return print;
	}
	
	/**
	 * Checks if a link is valid
	 * 
	 * @throws UninitializedObjectException Throws exception if the link is not valid
	 */
	private void checkForValid() throws UninitializedObjectException
	{
		if (isValid())
			throw new UninitializedObjectException("The link is not valid"); 
	}
	
	public Activity getActivity()
	{
		return active;
	}
}
