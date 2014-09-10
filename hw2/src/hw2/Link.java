package hw2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Link contains a set of two {@code Users}, an ArrayList of {@code Dates} and a {@code Validity} status
 * 
 * @author Matthew Swartwout
 *
 */
public class Link 
{
	private Set<User> users;
	private ArrayList<Date> dates;
	private Validity status;
	
	/**
	 * Empty constructor created INVALID {@code Link}
	 */
	public Link()
	{
		users = new HashSet<User>();
		dates = new ArrayList<Date>();
		status = Validity.INVALID;
	}
	
	/**
	 * Sets the two users for a link and marks it VALID
	 * 
	 * @param users The set of two users to be linked
	 * @return true if users were set, false if {@code Link} already existed or more than two {@code Users} were entered
	 * @throws NullPointerException if passed null inputs
	 */
	public boolean setUsers(Set<User> users) throws NullPointerException
	{
		//Check for null inputs
		if (users == null)
			throw new NullPointerException();
		
		User[] userArray = users.toArray(new User[0]);
		
		if (isValid()) //Validity check
			return false;
		if (users.size() > 2) //Checks for more than two users
			return false;
		if (!userArray[0].isValid() || !userArray[1].isValid()) //Make sure users are valid
			return false;
		
		else
		{
			this.users = users; //Set users
			status = Validity.VALID; //Set validity
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
		if (status == Validity.VALID)
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
		if (isValid())
			return users;
		else
			throw new UninitializedObjectException();
	}
	
	/**
	 * Establishes a connection at a specified {@code Date}
	 * 
	 * @param date The date for a connection to be established
	 * @return true if connection was established, false if connection was already active or if date came before most recent connection
	 * @throws UninitializedObjectException throws exception if {@code Link} is invalid
	 * @throws NullPointerException if passed null inputs
	 */
	public boolean establish(Date date) throws UninitializedObjectException, NullPointerException
	{
		//Check for null inputs
		if (date == null)
			throw new NullPointerException();
		
		if (isActive(date)) //Activity check
			return false;
		else if (dates.size() != 0 && date.isBefore(dates.get(dates.size() - 1))) //Check if date precedes most recent connection
			return false;
		else if (!isValid()) //Validity check
			throw new UninitializedObjectException();
		else 
		{
			date.setActivity(Activity.ACTIVE); //Set activity for date
			dates.add(date); //Add date to dates list
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
	public boolean tearDown(Date date) throws UninitializedObjectException, NullPointerException
	{
		//Check for null inputs
		if (date == null)
			throw new NullPointerException();
		
		if (!isActive(date)) //Activity check
			return false;
		else if (date.isBefore(dates.get(dates.size() - 1))) //Check if date precedes most recent connection
			return false;
		else if (!isValid()) //Validity check
			throw new UninitializedObjectException();
		else
		{
			date.setActivity(Activity.INACTIVE); //Set activity for date
			dates.add(date); //Add date to dates list
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
		//Check for null inputs
		if (date == null)
			throw new NullPointerException();
		else if (!isValid()) //Validity check
			throw new UninitializedObjectException();
		else if (dates.size() == 0)
			return false;
		else if (nextEvent(date) != null && nextEvent(date) != date && nextEvent(date).getActivity() == Activity.ACTIVE) //If next event is active, then connection was inactive at time of date
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
		if (isValid()) //Validity check
		{
			if (dates.size() == 0) //Check if there are no dates
				return null;
						
			else
				return dates.get(0);
		}
		
		else
			throw new UninitializedObjectException();
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
		//Check for null inputs
		if (date == null)
			throw new NullPointerException();
		
		else if (dates.size() == 0)
			return null;
		
		if (isValid()) //Validity check
		{
			Date iterateDate = dates.get(0);
			int iterateCount = 1;
			
			while (iterateDate.isBefore(date)) //Checks if current iterateDate is before the specified date
			{
				if (iterateCount == dates.size()) //Stops loop if it reaches end of dates list and there is no valid next event
					return null;
				
				iterateDate = dates.get(iterateCount); //Moves on to next event
				iterateCount++;
			}
			
			return iterateDate;
		}

		else 
			throw new UninitializedObjectException();
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
				print  += currentDate.getMonth().getValue() + " " + currentDate.getDay().getValue() + " " + currentDate.getYear() + " - "; //Adds Month + Date + Year to the string
			}
		}
		
		else
			print = "Invalid Link: Uninitialized IDs"; //Prints error if link is invalid
		
		return print;
	}
}
