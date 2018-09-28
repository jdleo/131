package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import grading.WeightedTotalStrategy;
import grading.Grade;
import grading.SizeException;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

class WeightedTotalStrategy_Tests {

	@Test
	void testConstructor0() {
		//initialize WTS object with NO parameters
		WeightedTotalStrategy tester = new WeightedTotalStrategy();
		
		//ensure weights is null
		assertEquals(tester.weights, null);
	}

	@Test
	void testConstructor1() {
		//dummy data for weights
		Map<String, Double> weights = new HashMap<String, Double>();
		weights.put("hw", 0.2);
		weights.put("quiz", 0.2);
		weights.put("exam", 0.3);
		weights.put("final", 0.3);
		
		//initialize WTS object with map of weights (dummy data)
		WeightedTotalStrategy tester = new WeightedTotalStrategy(weights);
		
		//ensure the value for "quiz" key is indeed 0.2
		double testValue = tester.weights.get("quiz");
		assertEquals(testValue, 0.2);
	}
	
	@Test
	void testCalculate() {
		//dummy data for weights
		Map<String, Double> weights = new HashMap<String, Double>();
		weights.put("hw", 0.2);
		weights.put("quiz", 0.2);
		weights.put("exam", 0.3);
		weights.put("final", 0.3);
		
		//initialize WTS object with map of weights (dummy data)
		WeightedTotalStrategy tester = new WeightedTotalStrategy(weights);
		
		//dummy data for list of grades
		List<Grade> list = Arrays.asList(
				new Grade("hw", 80.0),
				new Grade("quiz", 70.0),
				new Grade("exam", 77.0),
				new Grade("final", 92.0)
		);
		
		//with these weights and grades, final grade is expected to be 80.7
		try {
			Grade testGrade = tester.calculate("final", list);
			double testValue = testGrade.getValue();
			
			//doubles have precision issues, so within 0.001 is OK (epsilon)
			assertEquals(testValue, 80.7, 0.001);
		} catch (SizeException e) {
			fail("Exception caught, but not expected.");
		}
		
	}
	
	@Test
	void testException() {
		//initialize WTS object
		WeightedTotalStrategy tester = new WeightedTotalStrategy();
		
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
