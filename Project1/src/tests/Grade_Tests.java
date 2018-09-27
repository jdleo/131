package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import grading.Grade;

class Grade_Tests {

	@Test
	void illegalArgumentTest0() {
		try {
			Grade tester = new Grade("");
			fail("Test should fail since Grade should not allow empty string, or null");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("Key cannot be null or empty."));
		}
	}
	
	@Test
	void illegalArgumentTest1() {
		try {
			Grade tester = new Grade(null, new Double(50));
			fail("Test should fail since Grade should not allow empty string, or null");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("Key cannot be null or empty."));
		}	
	}
	
	@Test
	void illegalArgumentTest2() {
		try {
			Grade tester = new Grade("", 90.1);
			fail("Test should fail since Grade should not allow empty string, or null");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("Key cannot be null or empty."));
		}
	}
	
	@Test
	void constructorTest0() {
		Grade tester = new Grade("key");
		assertTrue(tester.getKey().equals("key"));
		assertTrue(tester.getValue() == 0.0);
	}
	
	@Test
	void constructorTest1() {
		Grade tester = new Grade("key", 90.1);
		assertTrue(tester.getKey().equals("key"));
		assertTrue(tester.getValue() == 90.1);
	}

}
