package tests;
import grading.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Gradient_Tests {
	
	/**
	 * This is a full system JUnit test
	 * Each test method corresponds to a test case provided 
	 * in Gradient System Tests document.
	 */

	@Test
	void testComplete01() {
		String resultString = this.GradientTestSystem(
			new String[]{"20","18","5","15","20","20","20","0","5","10","15","80","75"}
		);
		
		assertEquals("Course Grade:  80.7", resultString);
	}
	
	@Test
	void testComplete02() {
		String resultString = this.GradientTestSystem(
			new String[]{"20","20","20","20","20","20","20","20","20","20","20","100","100"}
		);
			
		assertEquals("Course Grade: 100.0", resultString);
	}
	
	@Test
	void testComplete03() {
		String resultString = this.GradientTestSystem(
			new String[]{"10","10","5","15","5","20","20","0","0","10","15","60","45"}
		);
				
		assertEquals("Course Grade:  54.0", resultString);
	}
	
	@Test
	void testMissingAll() {
		String resultString = this.GradientTestSystem(
			new String[]{"NA","NA","NA","NA","NA","NA","0","0","0","0","0","NA","NA"}
		);
				
		assertEquals("Course Grade:   0.0", resultString);
	}

	@Test
	void testMissingOneOneCategory() {
		String resultString = this.GradientTestSystem(
			new String[]{"20","18","5","15","20","20","20","NA","5","10","15","80","75"}
		);
				
		assertEquals("Course Grade:  80.7", resultString);
	}
	
	@Test
	void testMissingOneEachCategory() {
		String resultString = this.GradientTestSystem(
			new String[]{"20","18","5","15","NA","20","20","5","0","NA","15","NA","NA"}
		);
				
		assertEquals("Course Grade:  35.2", resultString);
	}
	
	@Test
	void testMissingMultipleEachCategory() {
		String resultString = this.GradientTestSystem(
			new String[]{"NA","20","5","15","NA","NA","20","NA","0","NA","15","NA","NA"}
		);
				
		assertEquals("Course Grade:  19.5", resultString);
	}
	
	/**
	 * This is an exact copy of the code in Gradient.java
	 * For testing the system as a whole
	 * @param args
	 * @return String : final grade string
	 */
	public String GradientTestSystem(String[] args) {
		Filter                   paFilter;        
		Grade                    courseGrade, hwGrade, paGrade;
		GradingStrategy          courseStrategy, hwStrategy, paStrategy;
		List<Grade>              grades, hws, pas;
		Map<String, Double>      courseWeights;

		// Early exit
		if ((args == null) || (args.length != 13))
		{
			System.err.println("You must enter all 13 grades. (Use NA for missing.)");
			System.exit(1);
		}

		// Create the filter and strategy for PAs
		paFilter   = new DropFilter(true, false);
		paStrategy = new TotalStrategy();

		// Create the strategy for HWs
		hwStrategy = new TotalStrategy();

		// Create the weights and strategy for the course grade
		courseWeights = new HashMap<String, Double>();
		courseWeights.put("PAs",     0.4);
		courseWeights.put("HWs",     0.1);
		courseWeights.put("Midterm", 0.2);
		courseWeights.put("Final",   0.3);
		courseStrategy = new WeightedTotalStrategy(courseWeights);

		try
		{
			// Put the PA grades in a List
			pas = new ArrayList<Grade>();
			for (int i=0; i<6; i++) 
			{
				pas.add(parseGrade("PA"+(i+1), args[i]));
			}

			// Calculate the PA grade (after filtering)
			paGrade = paStrategy.calculate("PAs", paFilter.apply(pas));

			// Put the HW grades in a List
			hws = new ArrayList<Grade>();
			for (int i=0; i<5; i++)
			{
				hws.add(parseGrade("HW"+(i+1), args[i+6]));
			}

			// Calculate the HW grade
			hwGrade = hwStrategy.calculate("HWs", hws);

			// Put all of the grades in a List
			grades = new ArrayList<Grade>();
			grades.add(paGrade);
			grades.add(hwGrade);
			grades.add(parseGrade("Midterm", args[11]));
			grades.add(parseGrade("Final",   args[12]));

			// Calculate the final grade
			courseGrade = courseStrategy.calculate("Course Grade", grades);

			// return the final grade
			return courseGrade.toString();        
		}
		catch (SizeException se)
		{
			return "You entered too few valid grades.";
		}
		catch (IllegalArgumentException iae)
		{
			// Should never get here since all keys should be valid
			return "";
		}
	}
	
	private static Grade parseGrade(String key, String value) throws IllegalArgumentException
	{
		Grade  result;
		
		try
		{
			Double v;
			if (value == null) v = null;
			else v = new Double(Double.parseDouble(value));
			
			result = new Grade(key, v);
		}
		catch (NumberFormatException nfe)
		{
			result = new Grade(key, null);
		}
		
		return result;
	}
	
	
	
}
