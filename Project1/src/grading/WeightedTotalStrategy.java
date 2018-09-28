package grading;
import java.util.Map;
import java.util.List;
import grading.SizeException;

public class WeightedTotalStrategy implements GradingStrategy {
	
	public Map<String, Double> weights;
	
	/**
	 * calculate() method to conform to GradingStrategy type
	 * @param key
	 * @param grades
	 * @return Grade : weighted total of the list of grades
	 * @throws SizeException 
	 */
	public Grade calculate(String key, List<Grade> grades) throws SizeException {
		if (grades == null) {
			throw new SizeException("List of grades cannot be null.");
		} else if (grades.isEmpty()) {
			throw new SizeException("List of grades cannot be empty.");
		} else {
			//to keep track of running total
			double runningTotal = 0;
			
			//iterate over grades list
			for (Grade grade : grades) {
				double weight = 1.0;
				
				//if non-nulls, get the weight
				if (weights != null && weights.get(grade.getKey()) != null) {
					weight = weights.get(grade.getKey());
				}
				
				//if weight is less than 0, set to 0
				if (weight < 0.0) {
					weight = 0.0;
				}
				
				//handle missing values with Missing utility
				double gradeValue = Missing.doubleValue(grade.getValue());
				
				//add to running totals
				runningTotal += (gradeValue * weight);
			}
			
			return new Grade(key, runningTotal);
		}
	}

	/**
	 * Constructor (default)
	 */
	public WeightedTotalStrategy() {
		this.weights = null;
	}
	
	/**
	 * Constructor
	 * @param weights
	 */
	public WeightedTotalStrategy(Map<String, Double> weights) {
		this.weights = weights;
	}
}
