package hw4;

import static org.junit.Assert.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FriendTest 
{
	@Rule 
	public ExpectedException thrown = ExpectedException.none();
	
	User user1, user2, user3;
	SocialNetwork network;
	SocialNetworkStatus status;
	Set<String> link1, link2, link3;
	Set<User> set1, set2, set3;
	Link Link1, Link2, Link3;
	Date date1, date2, date3, date4, date5;
	Set<Friend> friendSet;
	Friend friend1, friend2, friend3;
	
	@Before
	public void testSetup() throws NullPointerException, UninitializedObjectException
	{
		network = new SocialNetwork();
		status = new SocialNetworkStatus(Status.UNINITIALIZED);
		
		user1 = new User();
		user2 = new User();
		user3 = new User();
		
		friend1 = new Friend();
		friend2 = new Friend();
		friend3 = new Friend();
		
		link1 = new HashSet<String>();
		link2 = new HashSet<String>();
		link3 = new HashSet<String>();
		
		set1 = new HashSet<User>();
		set2 = new HashSet<User>();
		set3 = new HashSet<User>();
		
		Link1 = new Link();
		Link2 = new Link();
		Link3 = new Link();
		
		user1.setID("User1");
		user2.setID("User2");
		user3.setID("User3");
		
		network.addUser(user1);
		network.addUser(user2);
		network.addUser(user3);
		
		link1.add("User1");
		link1.add("User2");
		set1.add(user1);
		set1.add(user2);
		Link1.setUsers(set1, status);
		
		link2.add("User1");
		link2.add("User3");
		set2.add(user1);
		set2.add(user3);
		Link2.setUsers(set2, status);
		
		link3.add("User2");
		link3.add("User3");
		set3.add(user2);
		set3.add(user3);
		Link3.setUsers(set3, status);
		
		date1 = new Date(1);
		date2 = new Date(2);
		date3 = new Date(3);
		date4 = new Date(4);
		date5 = new Date(5);
		
		network.establishLink(link1, date1, status);
		network.establishLink(link2, date2, status);
	}
	
	@Test
	public void testNeighborhood() throws UninitializedObjectException
	{
		friendSet = new HashSet<Friend>();
		
		friend1.set(user1, 0);
		friend2.set(user2, 1);
		friend3.set(user3, 1);
		
		friendSet.add(friend1);
		friendSet.add(friend2);
		friendSet.add(friend3);
		
		assertEquals(network.neighborhood("User1", date2, status), friendSet);
	}
}
