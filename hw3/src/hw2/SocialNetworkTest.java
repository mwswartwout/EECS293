package hw2;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SocialNetworkTest 
{
	@Rule public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testAddUser() throws NullPointerException
	{
		SocialNetwork network = new SocialNetwork();
		
		//Valid entry test
		User newUser = new User();
		newUser.setID("ID");
		assertTrue(network.addUser(newUser));
		
		//Duplicate entry test
		assertFalse(network.addUser(newUser));
		
		//NullPointerException test
		thrown.expect(NullPointerException.class);
		assertNull(network.addUser(null));
	}

	@Test
	public void testIsMember()  throws NullPointerException
	{
		SocialNetwork network = new SocialNetwork();
		
		//Does not exist test
		assertFalse(SocialNetwork.isMember("dne"));
		
		User newUser = new User();
		newUser.setID("ID");
		network.addUser(newUser);
		
		//Does exist test
		assertTrue(SocialNetwork.isMember("ID"));
		
		//NullPointerException test
		thrown.expect(NullPointerException.class);
		assertNull(SocialNetwork.isMember(null));
	}

	@Test
	public void getUser()  throws NullPointerException 
	{
		SocialNetwork network = new SocialNetwork();
		
		//Does not exist test
		assertNull(network.getUser("dne"));
		
		User newUser = new User();
		newUser.setID("ID");
		network.addUser(newUser);
		
		//Does exist test
		assertSame(network.getUser("ID"), newUser);
		
		//NullPointerException test
		thrown.expect(NullPointerException.class);
		assertNull(network.getUser(null));		
	}

	@Test
	public void testFindLink() throws NullPointerException, UninitializedObjectException 
	{
		SocialNetwork network = new SocialNetwork();
		Link testLink = new Link();
		Set<User> validUsers = new HashSet<User>();
		Set<String> ids = new HashSet<String>();
		
		User validUser1 = new User();
		User validUser2 = new User();
		
		validUser1.setID("ID1");
		validUser2.setID("ID2");
		
		validUsers.add(validUser1);
		validUsers.add(validUser2);
		
		network.addUser(validUser1);
		network.addUser(validUser2);
		
		testLink.setUsers(validUsers);

		//Does not exist test
		ids.add("ID3");
		ids.add("ID4");
		assertNull(network.findLink(ids));
		
		//Does exist test
		ids.clear();
		ids.add("ID1");
		ids.add("ID2");
		Date validDate = new Date(Month.JANUARY, Day.FIRST, 2014);
		network.establishLink(ids, validDate);
		testLink.establish(validDate);
		assertEquals(network.findLink(ids).toString(), "User: ID1. User: ID2. 1 1 2014 - ");
	}

	@Test
	public void testEstablishLink() throws NullPointerException, UninitializedObjectException 
	{
		SocialNetwork network = new SocialNetwork();
		Link testLink = new Link();
		Set<User> validUsers = new HashSet<User>();
		Set<String> ids = new HashSet<String>();
		
		Date validDate = new Date(Month.JANUARY, Day.FIRST, 2014);
		
		User validUser1 = new User();
		User validUser2 = new User();
		
		validUser1.setID("ID1");
		validUser2.setID("ID2");
		
		//ID is not in network test
		ids.add("ID1");
		ids.add("ID2");
		assertFalse(network.establishLink(ids, validDate));
		
		network.addUser(validUser1);
		network.addUser(validUser2);
		validUsers.add(validUser1);
		validUsers.add(validUser2);
		testLink.setUsers(validUsers);
		
		//No existing link test
		assertTrue(network.establishLink(ids, validDate));
		
		//More than two ID test
		ids.add("ID1");
		ids.add("ID2");
		ids.add("ID3");
		assertFalse(network.establishLink(ids, validDate));
		
		//Duplicate ID test
		ids.clear();
		ids.add("ID1");
		ids.add("ID1");
		assertFalse(network.establishLink(ids, validDate));
		
		//Link already exists
		ids.clear();
		ids.add("ID1");
		ids.add("ID2");
		Date newDate = new Date(Month.JANUARY, Day.FIRST, 2016);
		assertFalse(network.establishLink(ids, newDate));
		
		//Link has been establish and torn down
		Date tearDown = new Date(Month.JANUARY, Day.FIRST, 2015);
		network.tearDownLink(ids, tearDown);
		assertTrue(network.establishLink(ids, newDate));
	}

	@Test
	public void testTearDownLink() throws NullPointerException, UninitializedObjectException 
	{
		SocialNetwork network = new SocialNetwork();
		Link testLink = new Link();
		Set<User> validUsers = new HashSet<User>();
		Set<String> ids = new HashSet<String>();
		Date validDate = new Date(Month.JANUARY, Day.FIRST, 2014);
		User validUser1 = new User();
		User validUser2 = new User();
		validUser1.setID("ID1");
		validUser2.setID("ID2");
		
		//ID is not in network test
		ids.add("ID1");
		ids.add("ID2");
		assertFalse(network.tearDownLink(ids, validDate));
		
		network.addUser(validUser1);
		network.addUser(validUser2);
		validUsers.add(validUser1);
		validUsers.add(validUser2);
		testLink.setUsers(validUsers);
		
		//More than two ID test
		ids.add("ID1");
		ids.add("ID2");
		ids.add("ID3");
		assertFalse(network.tearDownLink(ids, validDate));
		
		//Duplicate ID test
		ids.clear();
		ids.add("ID1");
		ids.add("ID1");
		assertFalse(network.tearDownLink(ids, validDate));
		
		//Link already exists
		ids.clear();
		ids.add("ID1");
		ids.add("ID2");
		Date establish = new Date(Month.JANUARY, Day.FIRST, 2015);
		network.establishLink(ids, establish);
		Date newDate = new Date(Month.JANUARY, Day.FIRST, 2016);
		assertTrue(network.tearDownLink(ids, newDate));
	}

	@Test
	public void testIsActive() throws NullPointerException, UninitializedObjectException 
	{
		SocialNetwork network = new SocialNetwork();
		Link testLink = new Link();
		Set<User> validUsers = new HashSet<User>();
		Set<String> ids = new HashSet<String>();
		Date validDate = new Date(Month.JANUARY, Day.FIRST, 2014);
		
		User validUser1 = new User();
		User validUser2 = new User();
		
		validUser1.setID("ID1");
		validUser2.setID("ID2");
		
		network.addUser(validUser1);
		network.addUser(validUser2);
		
		validUsers.add(validUser1);
		validUsers.add(validUser2);
		
		testLink.setUsers(validUsers);
		
		ids.add(validUser1.getID());
		ids.add(validUser2.getID());
		
		network.establishLink(ids, validDate);
		
		//Active test
		assertTrue(network.isActive(ids, validDate));
		
		//Invalid test
		Date invalidDate = new Date(Month.JANUARY, Day.FIRST, 2013);
		assertFalse(network.isActive(ids, invalidDate));
	}

}
