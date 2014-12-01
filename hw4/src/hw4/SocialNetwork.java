package hw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Queue;

/**
 * SocialNetwork implements adding, finding, and linking {@code Users} in a network
 * @author Matthew Swartwout, mws85
 *
 */
public class SocialNetwork 
{
	private Set<User> networkUsers; //The network Users
	private Set<Link> networkLinks; //The networks Links
	
	/**
	 * Constructor creates an empty social network
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
		HelperMethods.checkForNullInput(user);
			
		//Check if member already has same ID
		if (isMember(user.getID()))
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
	public boolean isMember(String ID) throws NullPointerException
	{
		HelperMethods.checkForNullInput(ID);
		
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
		HelperMethods.checkForNullInput(id);
		
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
		HelperMethods.checkForNullInput(ids);
		
		Iterator<Link> linkIterator = networkLinks.iterator(); //Set iterator
		Link iteratorHold = null;
		
		if (networkLinks.size() > 0)
			iteratorHold = linkIterator.next();
		
		String[] idArray = ids.toArray(new String[0]); //Holds the ID set
		Arrays.sort(idArray);
		
		while (iteratorHold != null)
		{
			User[] userArray = iteratorHold.getUsers().toArray(new User[0]); //Get Users from current Link
			Arrays.sort(userArray);
			
			if (userArray.equals(idArray)) //Checks if Users are identical
				return iteratorHold;
			else
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
	public boolean establishLink(Set<String> ids, Date date, SocialNetworkStatus status) throws UninitializedObjectException, NullPointerException
	{
		HelperMethods.checkForNullInput(ids, date);
		
		String[] idArray = ids.toArray(new String[0]); //Array to hold IDs
		
		if (!distinctUserCheck(idArray)) //Check if there are only two IDs, and if they are distinct
		{
			status.setStatus(Status.INVALID_USERS);
			return false;
		}
			
		if (!validMemberCheck(idArray)) //Check that both IDs are valid members of the network
			return false;
		
		Link foundLink = findLink(ids); //Find if there is an existing Link between the users
		
		if (foundLink != null) //If there is an existing Link
		{
			return foundLink.establish(date, status);
		}
		
		else //If the two users have never been linked
		{
			Link newLink = new Link(); //Create new Link
			networkLinks.add(newLink); //Add new Link to the networkLinks
			
			Set<User> userSet = new HashSet<User>(); //Create a new User Set
			userSet.add(getUser(idArray[0])); //Add user1 to the User Set
			userSet.add(getUser(idArray[1])); //Add user2 to the User Set
			
			newLink.setUsers(userSet, status); //Set the two users into the new LInk
			
			return newLink.establish(date, status);
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
	public boolean tearDownLink(Set<String> ids, Date date, SocialNetworkStatus status) throws UninitializedObjectException, NullPointerException
	{
		HelperMethods.checkForNullInput(ids, date);
		
		String[] idArray = ids.toArray(new String[0]); //Array to hold IDs
		
		if (!distinctUserCheck(idArray)) //Check if there are only two IDs, and if they are distinct
		{
			status.setStatus(Status.INVALID_USERS);
			return false;
		}
		
		if (!validMemberCheck(idArray)) //Check that both IDs are valid members of the network
			return false;
		
		Link foundLink = findLink(ids); //Find if there is an existing Link between the users
		
		if (foundLink != null) //If there is an existing Link
		{
			return foundLink.tearDown(date, status);
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
		HelperMethods.checkForNullInput(ids, date);
		
		Link foundLink = findLink(ids); //Finds the link between the Users
		
		if (foundLink != null) //If the link exists
		{
			if (foundLink.isActive(date)) //Check if active
				return true;
		}
		
		return false; //Else return false
			
	}
	
	/**
	 * Returns a set of all Friends at any distance
	 * 
	 * @param id The ID of the root user
	 * @param date The date to generate the neighborhood for
	 * @param status The status to be updated
	 * @return The set of users that are in the neighborhood
	 * @throws UninitializedObjectException
	 */
	public Set<Friend> neighborhood(String id, Date date, SocialNetworkStatus status) throws UninitializedObjectException
	{
		HelperMethods.checkForNullInput(id, date, status);
		
		return neighborhood(id, date, -1, status);
	}
	
	/**
	 * Gets the neighborhood of a user within a certain distance
	 * @param id The ID of the root user
	 * @param date The date to generate the neighborhood for
	 * @param distance_max The maximum distance for the neighborhood
	 * @param status The status variable to be updated
	 * @return The set of users that are in the neighborhood below the given distance
	 * @throws UninitializedObjectException
	 */
	public Set<Friend> neighborhood(String id, Date date, int distance_max, SocialNetworkStatus status) throws UninitializedObjectException
	{
		checkForID(id, status);
		HelperMethods.checkForNullInput(id, date, status);
		
		Set<Friend> neighborhood = new HashSet<Friend>(); //The set of friends to be returned
		
		if (distance_max < 0)
			distance_max = networkLinks.size() + 1; //Maximum neighborhood size is networkLink.size(), so this condition means the whole neighborhood will be created
		
		Queue<User> currentQueue = new LinkedList<User>(); //Holds the Users at the current distance
		Queue<User> nextQueue = new LinkedList<User>(); //Holds the Users at the next distance
		currentQueue.add(getUser(id)); //Adds the root user to the queue
		int distance = 0; //Records the distance for the friend
		
		while(hasUsersInQueues(currentQueue, nextQueue) && distance < distance_max)
		{
			//Instantiates the new Friend, sets its' user and distance and adds it to the neighborhood
			Friend newFriend = new Friend();
			newFriend.set(currentQueue.peek(), distance);
			neighborhood.add(newFriend);
			
			nextQueue.addAll(getLinkedUsers(currentQueue.peek(), date, neighborhood)); //Gets all of the Users that are linked to the current User in the queue
			currentQueue.remove();
			
			if (currentQueue.isEmpty()) //If the current distance of Users has no more to add
			{
				currentQueue.addAll(nextQueue); //Adds all of the Users from the next level to the current queue
				distance++;
			}
		}
		
		status.setStatus(Status.SUCCESS);
		return neighborhood;
	}
	
	/**
	 * Creates a trend map of the when the neighborhood changed size
	 * 
	 * @param id The id of the root user
	 * @param status The status variable to be updated
	 * @return A map where the key is the date of the change, and the value is the size of the neighborhood
	 * @throws UninitializedObjectException
	 */
	public Map<Date, Integer> neighborhoodTrend(String id, SocialNetworkStatus status) throws UninitializedObjectException
	{
		checkForID(id, status);
		HelperMethods.checkForNullInput(id, status);
		
		Map<Date, Integer> neighborhoodTrendMap = new HashMap<Date, Integer>();
		ArrayList<Date> linkDates = getDateArray(getUser(id));
		neighborhoodTrendMap.put(linkDates.get(0), neighborhood(id, linkDates.get(0), status).size()); //Puts the root user and their initial neighborhood size into the map
		int previousNeighborhoodSize = neighborhood(id, linkDates.get(0), status).size();
		
		for (Date currentDate : linkDates) //Goes through every date in the list
		{
			if (neighborhood(id, currentDate, status).size() != previousNeighborhoodSize) //If the neighborhood size has changed at that date
			{
				neighborhoodTrendMap.put(currentDate, neighborhood(id, linkDates.get(0), status).size()); //Add that value/key to the map
				previousNeighborhoodSize = neighborhood(id, linkDates.get(0), status).size(); //Update the previous neigbhorhood size
			}
		}
		
		return neighborhoodTrendMap;
	}
	
	/**
	 * Gets the list of every date a user's links have changed
	 * 
	 * @param user The user to be the root of the search
	 * @return A sorted array list of every date where a link involving the user was changed
	 * @throws UninitializedObjectException 
	 */
	private ArrayList<Date> getDateArray(User user) throws UninitializedObjectException
	{
		HelperMethods.checkForNullInput(user);
		
		ArrayList<Date> dateArray = new ArrayList<Date>();
		Iterator<Link> networkLinksIterator = networkLinks.iterator();
		Link currentLink;
		
		while(networkLinksIterator.hasNext())
		{
			currentLink = networkLinksIterator.next();
			
			if (currentLink.getUsers().contains(user))
			{
				dateArray.addAll(currentLink.getDates());
			}
		}
		
		Collections.sort(dateArray);
		
		return dateArray;
	}
	
	/**
	 * Gets all of the Users linked directly to a specific user
	 * 
	 * @param user The root user of the search
	 * @param date The date to check for the search
	 * @param neighborhood The neighborhood to check for the user
	 * @return Returns the set of Users that are directly linked to the root User
	 * @throws UninitializedObjectException
	 */
	private Set<User> getLinkedUsers(User user, Date date, Set<Friend> neighborhood) throws UninitializedObjectException
	{
		Set<User> users = new HashSet<User>(); //The set of Users to be returned
		User[] currentLinkUsers; //The set of Users for the link currently being examined
		Link currentLink; //Link for holding the current link of the iterator
		
		Iterator<Link> networkLinksIterator = networkLinks.iterator();
		
		while (networkLinksIterator.hasNext())
		{
			currentLink = networkLinksIterator.next(); //Saves the current link
			currentLinkUsers = currentLink.getUsers().toArray(new User[0]); //Gets the users of the current link
			
			if (currentLink.isActive(date))
			{
				if (linkIsNewToNeighborhood(currentLink, user, date, neighborhood)) //Checks if the link 
					users.add(currentLinkUsers[getOtherUserIfLinkContainsUser(currentLink, user)]);
			}
		}
		
		return users;
	}
	
	/**
	 * Checks if a link is new to a neighborhood
	 * 
	 * @param link The link to be checked
	 * @param user The user to check for
	 * @param date The date at which to check the link
	 * @param neighborhood The neighborhood to check for the user
	 * @return True if the other user in the link does not exist in the neighborhood, false otherwise
	 * @throws UninitializedObjectException
	 */
	private boolean linkIsNewToNeighborhood(Link link, User user, Date date, Set<Friend> neighborhood) throws UninitializedObjectException
	{
		User[] linkUsers = link.getUsers().toArray(new User[0]);
		int linkUserLocation = getOtherUserIfLinkContainsUser(link, user);
				
		if (link.isActive(date) && linkUserLocation != 2) //If the link is active and the user is in the link
		{
			if (!neighborhoodContainsUser(neighborhood, linkUsers[linkUserLocation])) //If the neighborhood does not contain the other user, return true
				return true;
		}
		
		return false;
	}
	
	/**
	 * Checks if a link contains a specific user
	 * 
	 * @param link The link to be checked
	 * @param user The user to looked for
	 * @return True if that User is included in the link, false otherwise
	 * @throws UninitializedObjectException
	 */
	private int getOtherUserIfLinkContainsUser(Link link, User user) throws UninitializedObjectException
	{
		User[] linkUsers = link.getUsers().toArray(new User[0]);
		
		if (linkUsers[0] == user)
			return 1; //Returns the index of the other user
		else if (linkUsers[1] == user)
			return 0;
		else
			return 2;
	}
	
	/**
	 * Checks if a neighborhood contains a user
	 * 
	 * @param neighborhood The neighborhood to check
	 * @param user The user to be looked for 
	 * @return True if the User is in the neighborhood, false otherwise
	 * @throws UninitializedObjectException
	 */
	private boolean neighborhoodContainsUser(Set<Friend> neighborhood, User user) throws UninitializedObjectException
	{
		Iterator<Friend> neighborhoodIterator = neighborhood.iterator();
		
		while (neighborhoodIterator.hasNext())
		{
			if (neighborhoodIterator.next().getUser() == user)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Checks whether two queues are empty
	 * 
	 * @param queue1 The first queue to be checked
	 * @param queue2 The second queue to be checked
	 * @return False if both queues are empty, true otherwise
	 */
	private boolean hasUsersInQueues(Queue<User> queue1, Queue<User> queue2)
	{
		if (queue1.isEmpty() && queue2.isEmpty())
			return false;
		else
			return true;
	}
	
	/**
	 * Checks if an ID is null and changes a status if so
	 * 
	 * @param id The ID to be checked
	 * @param status The status to be changed if the ID is null
	 */
	private void checkForID(String id, SocialNetworkStatus status)
	{
		if (id == null)
			status.setStatus(Status.INVALID_USERS);
	}
	
	/**
	 * Checks if an idArray has two distinct users
	 * 
	 * @param idArray The array to be checked
	 * @return True if the array contains only two, distinct users. False otherwise.
	 */
	private boolean distinctUserCheck(String[] idArray)
	{		
		if (idArray.length != 2 || idArray[0] == idArray[1])
			return false;
		else
			return true;
	}
	
	/**
	 * Checks if an array contains two valid members
	 * 
	 * @param idArray The array to be checked 
	 * @return True if the array contains only two, valid users. False otherwise.
	 */
	private boolean validMemberCheck(String[] idArray)
	{	
		if ((isMember(idArray[0]) && isMember(idArray[1])) && idArray.length == 2)
			return true;
		else
			return false;
	}
}
