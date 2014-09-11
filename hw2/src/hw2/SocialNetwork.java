package hw2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * SocialNetwork implements adding, finding, and linking {@code Users} in a network
 * @author Matthew Swartwout
 *
 */
public class SocialNetwork 
{
	private static Set<User> networkUsers; //The network Users
	private static Set<Link> networkLinks; //The networks Links
	
	/**
	 * Constructor creates an emtpy social network
	 */
	public SocialNetwork()
	{
		//Instantiate empty network
		networkUsers = new HashSet<User>();
		networkLinks = new HashSet<Link>();
	}
	
	/**
	 * Adds a user to the network if there is no conflict with the user's ID
	 * 
	 * @param user
	 * @return false if user exists with the same ID and true otherwise.
	 * @throws NullPointerException if any inputs are null
	 */
	public boolean addUser(User user) throws NullPointerException
	{
		//Check for null inputs
		if (user == null)
			throw new NullPointerException();
			
		//Check if member already has same ID
		if (user.getID() != null && isMember(user.getID()))
			return false;
		else
		{
			//Add user to the network
			networkUsers.add(user);
			return true;
		}
	}
	
	/**
	 * Checks if there is a user with the given ID
	 * 
	 * @param id the ID to be checked
	 * @return true if a user exists with the given ID and false otherwise
	 * @throws NullPointerException if any inputs are null
	 */
	public static boolean isMember(String ID) throws NullPointerException
	{
		//Check for null inputs
		if (ID == null)
			throw new NullPointerException();
		
		//Instantiate network userIterator
		Iterator<User> userIterator = networkUsers.iterator();
		
		while (userIterator.hasNext())
		{
			//Checks for user with the same ID
			if (userIterator.next().getID() == ID)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Returns the {@code User} with the given ID
	 *  
	 * @param id the ID to be searched for
	 * @return a {@code User} with the same ID and null if no {@code User} with the same ID exists
	 * @throws NullPointerException if any inputs are null
	 */
	public User getUser(String id) throws NullPointerException
	{
		//Check for null inputs
		if (id == null)
			throw new NullPointerException();
		
		//Instantiate the network userIterator
		Iterator<User> userIterator = networkUsers.iterator();
		User iteratorHold = null;
		
		if (networkUsers.size() > 0)
			iteratorHold = userIterator.next();
		
		while (iteratorHold != null)
		{
			//Checks for user with the same ID
			if (iteratorHold.getID() == id)
				return iteratorHold;
			
			iteratorHold = userIterator.next();
		}
		
		return null;
	}
	
	/**
	 * Receives a set of IDs and finds the link with the matching users
	 * 
	 * @param ids The IDs to be searched for
	 * @return The {@code Link} if it exists, null if it doesn't
	 * @throws NullPointerException if any inputs are null
	 * @throws  
	 */
	public Link findLink(Set<String> ids) throws UninitializedObjectException, NullPointerException
	{
		//Check for null inputs
		if (ids == null)
			throw new NullPointerException();
		
		Iterator<Link> linkIterator = networkLinks.iterator(); //Set iterator
		Link iteratorHold = null;
		
		if (networkLinks.size() > 0)
			iteratorHold = linkIterator.next();
		
		String[] idArray = ids.toArray(new String[0]); //Holds the ID set
		
		while (iteratorHold != null)
		{
	
			User[] userArray = iteratorHold.getUsers().toArray(new User[0]); //Get Users from current Link
				
			if (userArray[0].getID() == idArray[0] && userArray[1].getID() == idArray[1]) //Checks if Users are identical
				return iteratorHold;
				
			if (userArray[0].getID() == idArray[1] && userArray[1].getID() == idArray[0]) //Checks if Users are identical but in a different order
				return iteratorHold;
			
			iteratorHold = linkIterator.next();
		}
		
		//If proper Link isn't found, return null
		return null;
	}
	
	/**
	 * Establishes a link between two users at a specified date
	 * 
	 * @param ids The IDs of the two {@code Users} to be connected
	 * @param date The date at which to establish the {@code Link}
	 * @return True if link was established, false otherwise
	 * @throws UninitializedObjectException throws exception if passed {@code Users} that are not valid
	 * @throws NullPointerException if any inputs are null
	 */
	public boolean establishLink(Set<String> ids, Date date) throws UninitializedObjectException, NullPointerException
	{
		//Check for null inputs
		if (ids == null || date == null)
			throw new NullPointerException();
		
		String[] idArray = ids.toArray(new String[0]); //Array to hold IDs
		
		if (idArray.length != 2 || idArray[0] == idArray[1]) //Check if there are only two IDs, and if they are distinct
			return false;
		
		if (!isMember(idArray[0]) || !isMember(idArray[1])) //Check that both IDs are valid members of the network
			return false;
		
		Link foundLink = findLink(ids); //Find if there is an existing Link between the users
		
		if (foundLink != null) //If there is an existing Link
		{
			if (foundLink.establish(date)) //Establish the new Link
				return true;
			
			else
				return false;
		}
		
		else //If the two users have never been linked
		{
			Link newLink = new Link(); //Create new Link
			networkLinks.add(newLink); //Add new Link to the networkLinks
			
			Set<User> userSet = new HashSet<User>(); //Create a new User Set
			userSet.add(getUser(idArray[0])); //Add user1 to the User Set
			userSet.add(getUser(idArray[1])); //Add user2 to the User Set
			
			newLink.setUsers(userSet); //Set the two users into the new LInk
			
			if (newLink.establish(date)) //Establish the new link
			{
				return true;
			}
			
			else
				return false;
		}
	}
	
	/**
	 * Tears down a link between two users on a given date
	 * 
	 * @param ids The IDs of the {@code Users} with the {@code Link} to be torn down
	 * @param date The date at which to tear down the {@code Link}
	 * @return True if link was torn down successfully, false if otherwise
	 * @throws UninitializedObjectException Throws exception if passed {@code Users} that are not valid
	 * @throws NullPointerException if any inputs are null
	 */
	public boolean tearDownLink(Set<String> ids, Date date) throws UninitializedObjectException, NullPointerException
	{
		//Check for null inputs
		if (ids == null || date == null)
			throw new NullPointerException();
		
		String[] idArray = ids.toArray(new String[0]); //Array to hold IDs
		
		if (idArray.length != 2 || idArray[0] == idArray[1]) //Check if there are only two IDs, and if they are distinct
			return false;
		
		if (!isMember(idArray[0]) || !isMember(idArray[1])) //Check that both IDs are valid members of the network
			return false;
		
		Link foundLink = findLink(ids); //Find if there is an existing Link between the users
		
		if (foundLink != null) //If there is an existing Link
		{
			if (foundLink.tearDown(date)) //Tear down the Link
				return true;
			else
				return false;
		}
		
		return false;
	}
	
	/**
	 * Checks if a link between the given {@code Users} was active on a given date
	 * @param ids The IDs of the {@code Users} to check
	 * @param date The date of the link
	 * @return True if the {@code Link} was active on the given {@code Date}, false if not
	 * @throws UninitializedObjectException if given IDs aren't valid
	 * @throws NullPointerException if any inputs are null
	 */
	public boolean isActive(Set<String> ids, Date date) throws UninitializedObjectException, NullPointerException
	{
		//Check for null inputs
		if (ids == null || date == null)
			throw new NullPointerException();
		
		Link foundLink = findLink(ids); //Finds the link between the Users
		
		if (foundLink != null) //If the link exists
		{
			if (foundLink.isActive(date)) //Check if active
				return true;
		}
		
		return false; //Else return false
			
	}
}
