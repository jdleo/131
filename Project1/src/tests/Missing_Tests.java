package tests;

import static org.junit.jupiter.api.Assertions.*;
import grading.Missing;

import org.junit.jupiter.api.Test;

class Missing_Tests {

	@Test
	void testDoubleValue0() {
		//intentionally pass in null
		double weight = Missing.doubleValue(null);
		
		//make sure passing in null results in 0.0 by default
		assertEquals(weight, 0.0);
	}

	@Test
	void testDoubleValue1() {
		//intentionally pass in null, and a default weight
		double weight = Missing.doubleValue(null, 0.5);
		
		//make sure passing in null results in 0.5 as specified
		assertEquals(weight, 0.5);
	}

}
