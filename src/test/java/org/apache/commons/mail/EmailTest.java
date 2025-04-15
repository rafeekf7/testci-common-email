package org.apache.commons.mail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmailTest {
	// Array of strings containing emails for test cases
	private static final String[] TEST_EMAILS = { "ab@bc.cocm", "a.b@c.org", 
			"abcdefghijklmnopqrst@abcdefghijklmnopqrst.com.bd", };
	
	
	private EmailConcrete email;
	
	// Setup for email tests
	// Creating EmailConcrete object
	@Before
	public void setUpEmailTest() throws Exception{
		email = new EmailConcrete();	
	}
	
	// Teardown for email tests
	@After
	public void tearDownEmailTest() throws Exception{
	
	}
	
	// Test case for addBcc(String... emails)
	// Inputs TEST_EMAILS and checks that the stored addresses match expected
	@Test
	public void testAddBcc() throws Exception{
		email.addBcc(TEST_EMAILS);

		assertEquals(3, email.getBccAddresses().size());

		try {
			email.addBcc();
		}catch(EmailException e) {}
	
	}
	
	// Test case for addCc(String email)
	// Inputs TEST_EMAILS and checks that the stored addresses match expected
	@Test
	public void testAddCc() throws Exception{
		email.addCc(TEST_EMAILS);

		assertEquals(3, email.getCcAddresses().size());

	}
	
	// Test case for addHeader(String name, String value)
	// Inputs name and value for header
	// Inputs name and no value
	// Inputs value and no name
	@Test
	public void testAddHeader() throws Exception{

		email.addHeader("name", "value");
			
		try {
			email.addHeader("Name", null);
		}catch(IllegalArgumentException e) {}
		
		try {
			email.addHeader(null,"Value");
		}catch(IllegalArgumentException e) {}
	}

	// Test case for addReplyTo(String email, String name)
	// Inputs email and name
	@Test
	public void testAddReplyTo() throws Exception{
		email.addReplyTo("abc@d.com", "name");
	}
	
	// Test case for buildMimeMessage()
	// Testing with required inputs
	@Test
	public void testBuildMimeMessage() throws Exception{
		email.setHostName("localhost");
		email.setFrom("fromemail@email.com");
		email.setSubject("sub");
		email.updateContentType("contentType");
		email.setContent("test","test");
		email.setCharset("UTF-16");
		
		email.addTo("toEmail@e.com");
		email.addBcc(TEST_EMAILS); 
		email.addCc(TEST_EMAILS); 
		email.addReplyTo("r@g.com");
		email.addHeader("name", "value");
		
		
		try {
			email.buildMimeMessage();
		}catch(IllegalStateException e) {}
			
	}
	
	
	// Test case for buildMimeMessage()
	// Testing for when toaddress and subject are missing.
	@Test
	public void testBuildMimeMessage1() throws Exception{
		email.setHostName("localhost");
		email.setFrom("fromemail@email.com");
		assertTrue(email.getToAddresses() != null);
		try {
			email.buildMimeMessage();
		}catch(EmailException e) {}
	}
	
	// Test case for buildMimeMessage()
	// Testing for when subject is set but toaddress is missing
	@Test
	public void testBuildMimeMessage2() throws Exception{
		email.setHostName("localhost");
		email.setFrom("fromemail@email.com");
		email.setSubject("sub");
		assertTrue(email.getToAddresses() != null);
		try {
			email.buildMimeMessage();
		}catch(EmailException e) {}
	}
	
	
	// Test case for getHostName()
	// Inputs localhost for host name and retrieves host name
	@Test
	public void testGetHostName() throws Exception{
		email.setHostName("localhost");
		email.getHostName();

	}
	// Test case for getHostName()
	// Does not set host name
	@Test
	public void testGetHostName1() throws Exception{

		email.getHostName();
	}
	

	// Test case for getMailSession()
	// Sets host and gets the mail session
	@Test
	public void testGetMailSession() throws Exception{
		email.setHostName("localhost");
		assertTrue(email.getHostName() != null);
		email.getMailSession();
	}
	
	// Test case for getMailSession()
	// Sets host and bounceaddress 
	// sets sockettimeout and sockeconnectiontimeout to 0
	// sets sslonconnect to true
	@Test
	public void testGetMailSession1() throws Exception{
		
		email.setHostName("localhost");
		email.setBounceAddress("b@email.com");
		email.setSocketTimeout(0);
		email.setSocketConnectionTimeout(0);
		email.setSSLOnConnect(true);

		email.getMailSession();
		
	}
	
	// Test case for  getSentDate()
	// Creates a new date using current date
	// sets sent date
	// retrieves sent date
	@Test
	public void testGetSentDate() throws Exception{
		email.setHostName("localhost");
		Date date = new Date();
		email.setSentDate(date);
		email.getSentDate();
	}
	
	// Test case for getSocketConnectionTimeout()
	// Sets host name and retrieves the timeout
	@Test
	public void testGetSocketConnectionTimeout() throws Exception{
		email.setHostName("localhost");
		email.getSocketConnectionTimeout();
	}
	
	// Test case for setFrom(String email)
	// Sets host name and from email
	@Test
	public void testSetFrom() throws Exception{
		email.setHostName("localhost");
		email.setFrom("email@email.com");
	}
}
