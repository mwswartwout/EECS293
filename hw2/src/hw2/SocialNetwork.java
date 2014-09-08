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
	private Set<User> network;
	private Iterator<User> iterator;
	
	/**
	 * Constructor creates an emtpy social network
	 */
	public SocialNetwork()
	{
		//Instantiate empty network
		network = new HashSet<User>();
	}
	
	/**
	 * Adds a user to the network if there is no conflict with the user's ID
	 * 
	 * @param user
	 * @return false if user exists with the same ID and true otherwise.
	 */
	public boolean addUser(User user)
	{
		//Check if member already has same ID
		if (isMember(user.getID()))
			return false;
		else
		{
			//Add user to the network
			network.add(user);
			return true;
		}
	}
	
	/**
	 * Checks if there is a user with the given ID
	 * 
	 * @param id the ID to be checked
	 * @return true if a user exists with the given ID and false otherwise
	 */
	public boolean isMember(String id)
	{
		//Instantiate network iterator
		iterator = network.iterator();
		
		while (iterator.hasNext())
		{
			//Checks for user with the same ID
			if (iterator.next().getID() == id)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Returns the {@code User} with the given ID
	 *  
	 * @param id the ID to be searched for
	 * @return a {@code User} with the same ID and null if no {@code User} with the same ID exists
	 */
	public User getUser(String id)
	{
		//Instantiate the network iterator
		iterator = network.iterator();
		
		while (iterator.hasNext())
		{
			//Checks for user with the same ID
			if (iterator.next().getID() == id)
				return iterator.next();
		}
		
		return null;
	}
	
	public boolean establishLink(Set<String> ids, Date date)
	{
		
	}
	
	public boolean tearDownLink(Set<String> String ids, Date dat)
	{
		
	}
	
	public boolean isActive(Set<String> ids, Date date)
	{
		
	}
}
