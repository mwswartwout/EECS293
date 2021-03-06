package hw2;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LinkTest 
{
	@Rule public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testSetUsers() 
	{
		@SuppressWarnings("unused")
		SocialNetwork network = new SocialNetwork();
		Link testLink = new Link();
		
		//Invalid user test
		Set<User> invalidUsers = new HashSet<User>();
		User invalidUser1 = new User();
		User invalidUser2 = new User();
		invalidUsers.add(invalidUser1);
		invalidUsers.add(invalidUser2);
		assertFalse(testLink.setUsers(invalidUsers));
		
		//Valid user test
		Set<User> validUsers = new HashSet<User>();
		User validUser1 = new User();
		User validUser2 = new User();
		validUser1.setID("ID1");
		validUser2.setID("ID2");
		validUsers.add(validUser1);
		validUsers.add(validUser2);
		assertTrue(testLink.setUsers(validUsers));
		
		//Already valid test
		assertFalse(testLink.setUsers(validUsers));
		
		//Too many users test
		User validUser3 = new User();
		validUser3.setID("ID3");
		validUsers.add(validUser3);
		assertFalse(testLink.setUsers(validUsers));
	}

	@Test
	public void testIsValid()
	{
		@SuppressWarnings("unused")
		SocialNetwork network = new SocialNetwork();
		Link testLink = new Link();
		
		//Invalid link test
		assertFalse(testLink.isValid());
		
		//Valid link test
		Set<User> validUsers = new HashSet<User>();
		User validUser1 = new User();
		User validUser2 = new User();
		validUser1.setID("ID1");
		validUser2.setID("ID2");
		validUsers.add(validUser1);
		validUsers.add(validUser2);
		testLink.setUsers(validUsers);
		assertTrue(testLink.isValid());
	}

	@Test
	public void testGetUsers() throws UninitializedObjectException
	{		
		@SuppressWarnings("unused")
		SocialNetwork network = new SocialNetwork();
		Link testLink = new Link();

		//Valid users test
		Set<User> validUsers = new HashSet<User>();
		User validUser1 = new User();
		User validUser2 = new User();
		validUser1.setID("ID1");
		validUser2.setID("ID2");
		validUsers.add(validUser1);
		validUsers.add(validUser2);
		testLink.setUsers(validUsers);
		assertEquals(testLink.getUsers(), validUsers);
		
		//Invalid users test
		thrown.expect(UninitializedObjectException.class);
		Link invalidLink = new Link();
		assertEquals(invalidLink.getUsers(), validUsers);
	}

	@Test
	public void testEstablish() throws UninitializedObjectException, NullPointerException
	{
		Link testLink = new Link();
		Set<User> validUsers = new HashSet<User>();
		User validUser1 = new User();
		User validUser2 = new User();
		validUser1.setID("ID1");
		validUser2.setID("ID2");
		validUsers.add(validUser1);
		validUsers.add(validUser2);
		testLink.setUsers(validUsers);
		
		//Valid date test
		Date validDate = new Date(Month.JANUARY, Day.FIRST, 2014);
		assertTrue(testLink.establish(validDate));
		
		//Active date test
		assertFalse(testLink.establish(validDate));
		
		//Previous date test
		Date previousDate = new Date(Month.JANUARY, Day.FIRST, 2013);
		assertFalse(testLink.establish(previousDate));
		
		//UninitializedObjectException test
		Link invalidLink = new Link();
		thrown.expect(UninitializedObjectException.class);
		assertTrue(invalidLink.establish(validDate));
		
		//NullPointerException test
		thrown.expect(NullPointerException.class);
		assertTrue(testLink.establish(null));
	}

	@Test
	public void testTearDown() throws UninitializedObjectException, NullPointerException
	{
		Link testLink = new Link();
		Set<User> validUsers = new HashSet<User>();
		User validUser1 = new User();
		User validUser2 = new User();
		validUser1.setID("ID1");
		validUser2.setID("ID2");
		validUsers.add(validUser1);
		validUsers.add(validUser2);
		testLink.setUsers(validUsers);
		Date establishDate = new Date(Month.JANUARY, Day.FIRST, 2014);
		testLink.establish(establishDate);
		
		//Valid date test
		Date validDate = new Date(Month.JANUARY, Day. FIRST, 2015);
		assertTrue(testLink.tearDown(validDate));
		
		//Inactive date test
		assertFalse(testLink.establish(validDate));
		
		//Previous date test
		Date previousDate = new Date(Month.JANUARY, Day.FIRST, 2013);
		assertFalse(testLink.establish(previousDate));
		
		//UninitializedObjectException test
		Link invalidLink = new Link();
		thrown.expect(UninitializedObjectException.class);
		assertTrue(invalidLink.establish(validDate));
				
		//NullPointerException test
		thrown.expect(NullPointerException.class);
		assertTrue(testLink.establish(null));
	}

	@Test
	public void testIsActive() throws UninitializedObjectException, NullPointerException
	{
		Link testLink = new Link();
		Date validDate = new Date(Month.JANUARY, Day.FIRST, 2014);
		
		//NullPointerException test
		thrown.expect(NullPointerException.class);
		assertFalse(testLink.isActive(null));
		
		//UninitializedObjectException test
		thrown.expect(UninitializedObjectException.class);
		assertFalse(testLink.isActive(validDate));
		
		thrown = ExpectedException.none();
		
		Set<User> validUsers = new HashSet<User>();
		User validUser1 = new User();
		User validUser2 = new User();
		validUser1.setID("ID1");
		validUser2.setID("ID2");
		validUsers.add(validUser1);
		validUsers.add(validUser2);
		testLink.setUsers(validUsers);
		
		//No dates test
		assertFalse(testLink.isActive(validDate));
		
		//Active at date test
		testLink.establish(validDate);
		assertTrue(testLink.isActive(validDate));
		
		//Inactive at date test
		Date tearDownDate = new Date(Month.JANUARY, Day.FIRST, 2015);
		testLink.tearDown(tearDownDate);
		Date inactiveDate = new Date(Month.JANUARY, Day.FIRST, 2016);
		assertFalse(testLink.isActive(inactiveDate));
	}

	@Test
	public void testFirstEvent() throws UninitializedObjectException 
	{
		Link testLink = new Link();
		Set<User> validUsers = new HashSet<User>();
		User validUser1 = new User();
		User validUser2 = new User();
		validUser1.setID("ID1");
		validUser2.setID("ID2");
		validUsers.add(validUser1);
		validUsers.add(validUser2);
		testLink.setUsers(validUsers);
		
		//No dates test
		assertNull(testLink.firstEvent());
		
		//Has dates test
		Date validDate = new Date(Month.JANUARY, Day.FIRST, 2014);
		testLink.establish(validDate);
		assertSame(testLink.firstEvent(), validDate);
		
		//UninitializedObjectExceptionTest
		thrown.expect(UninitializedObjectException.class);
		Link invalidLink = new Link();
		assertNull(invalidLink.firstEvent());
	}

	@Test
	public void testNextEvent() throws NullPointerException, UninitializedObjectException 
	{
		Link testLink = new Link();
		Date validDate = new Date(Month.JANUARY, Day.FIRST, 2014);
		Set<User> validUsers = new HashSet<User>();
		User validUser1 = new User();
		User validUser2 = new User();
		validUser1.setID("ID1");
		validUser2.setID("ID2");
		validUsers.add(validUser1);
		validUsers.add(validUser2);
		testLink.setUsers(validUsers);
		
		//No dates test
		assertNull(testLink.nextEvent(validDate));
		
		//Has dates test
		testLink.establish(validDate);
		Date tearDownDate = new Date(Month.JANUARY, Day.FIRST, 2015);
		testLink.tearDown(tearDownDate);
		assertNotNull(testLink.nextEvent(validDate));
		
		//NullPointerException test
		thrown.expect(NullPointerException.class);
		assertNull(testLink.nextEvent(null));
		
		//UninitializedObjectExceptionTest
		thrown.expect(UninitializedObjectException.class);
		Link invalidLink = new Link();
		assertNotNull(invalidLink.nextEvent(validDate));
	}

	@Test
	public void testToString() throws NullPointerException, UninitializedObjectException 
	{
		Link testLink = new Link();
		
		//Invalid link test
		assertEquals(testLink.toString(), "Invalid Link: Uninitialized IDs");
		
		Date validDate = new Date(Month.JANUARY, Day.FIRST, 2014);
		Set<User> validUsers = new HashSet<User>();
		User validUser1 = new User();
		User validUser2 = new User();
		validUser1.setID("ID1");
		validUser2.setID("ID2");
		validUsers.add(validUser1);
		validUsers.add(validUser2);
		testLink.setUsers(validUsers);
		testLink.establish(validDate);
		
		//Valid link test
		assertEquals(testLink.toString(), "User: ID1. User: ID2. 1 1 2014 - ");
	}

}
