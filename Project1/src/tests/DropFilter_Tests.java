package tests;
import grading.DropFilter;
import grading.Grade;
import grading.SizeException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DropFilter_Tests {

	@Test
	void testDropFilter0() {
		//test to make sure the dropHighest and dropLowest default to true
		DropFilter tester = new DropFilter();
		assertTrue(tester.shouldDropHighest);
		assertTrue(tester.shouldDropLowest);
	}

	@Test
	void testDropFilter1() {
		//test to make sure values are initialized as expected
		DropFilter tester = new DropFilter(true, false);
		assertTrue(tester.shouldDropLowest);
		assertTrue(!tester.shouldDropHighest);
	}

	@Test
	void testApply() {
		DropFilter tester = new DropFilter();
		
		//fill grade list with dummy data (50,70,20,80,40,95)
		List<Grade> grades = Arrays.asList(
				new Grade("test", 50.0),
				new Grade("test", 70.0),
				new Grade("test", 20.0),
				new Grade("test", 80.0),
				new Grade("test", 40.0),
				new Grade("test", 95.0)
		);
		
		try {
			//apply filter to dummy data
			List<Grade> filteredList = tester.apply(grades);
			
			//20 should be dropped (lowest) and 95 should be dropped (highest)
			//so if this works, new lowest should be 40, and new highest should be 80
			assertEquals(40.0, filteredList.get(0).getValue(), 0.0001);
			assertEquals(80.0, filteredList.get(3).getValue(), 0.0001);
			
		} catch (SizeException e) {
			fail("SizeException should not be thrown here.");
		}
	}

}
