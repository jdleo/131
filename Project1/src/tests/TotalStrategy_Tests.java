package tests;

import static org.junit.jupiter.api.Assertions.*;
import grading.Grade;
import grading.TotalStrategy;
import grading.WeightedTotalStrategy;
import grading.SizeException;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class TotalStrategy_Tests {

	@Test
	void testCalculate() {
		//dummy data for list of grades
		List<Grade> list = Arrays.asList(
				new Grade("test", 80.0),
				new Grade("test", 70.0),
				new Grade("test", 77.0),
				new Grade("test", 92.0),
				new Grade("test", 80.0),
				new Grade("test", 70.0),
				new Grade("test", 77.0),
				new Grade("test", 92.0)
		);
		
		//calculate grade
		TotalStrategy strat = new TotalStrategy();
		try {
			Grade tester = strat.calculate("test", list);
			double testGrade = tester.getValue();
			//final grade with given list should be 638 total points
			//allow 0.0001 epsilon due to lack of precision with double values
			assertEquals(638, testGrade, 0.0001);
			
		} catch (SizeException e) {
			fail("SizeException should not be thrown here.");
		}
	}
	
	@Test
	void testException() {
		//initialize TS object
		TotalStrategy tester = new TotalStrategy();
		
		//initialize empty list
		List<Grade> emptyList = Arrays.asList();
		
		//try to calculate with empty list of grades
		try {
			Grade testValue = tester.calculate("test", emptyList);
			fail("Exception should have been caught.");
		} catch (SizeException e) {
			assertTrue(e.getMessage().equals("List of grades cannot be empty."));
		}
	}

}
