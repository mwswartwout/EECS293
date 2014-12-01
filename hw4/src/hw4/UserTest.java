package hw4;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest 
{
	
	@Test
	public void testSetID() throws NullPointerException, UninitializedObjectException 
	{
		SocialNetwork network = new SocialNetwork();
		
		//Test invalid user creation
		User invalidUser = new User();
		assertTrue(invalidUser.setID("ID"));
		network.addUser(invalidUser);
				
		//Test for duplicate ID
		User duplicateUser = new User();
		assertFalse(duplicateUser.setID("ID"));
	}

	@Test
	public void testGetID() 
	{
		@SuppressWarnings("unused")
		SocialNetwork network = new SocialNetwork();
		
		//Test invalid User
		User invalidUser = new User();
		assertNull(invalidUser.getID());
		
		//Test valid User
		User validUser = new User();
		validUser.setID("ID");
		assertEquals(validUser.getID(), "ID");
	}

	@Test
	public void testIsValid() 
	{	
		@SuppressWarnings("unused")
		SocialNetwork network = new SocialNetwork();
		
		//Test invalid user
		User invalidUser = new User();
		assertFalse(invalidUser.isValid());
		
		//Test valid user
		User validUser = new User();
		validUser.setID("ID");
		assertTrue(validUser.isValid());
	}

	@Test
	public void testToString() 
	{
		@SuppressWarnings("unused")
		SocialNetwork network = new SocialNetwork();
		
		//Test invalid user
		User invalidUser = new User();
		assertEquals(invalidUser.toString(), "Invalid User: Uninitialized ID");
		
		//Test valid user
		User validUser = new User();
		validUser.setID("ID");
		assertEquals(validUser.toString(), "Valid user: ID = ID.");
	}

}
