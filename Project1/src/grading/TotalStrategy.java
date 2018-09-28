package grading;
import java.util.List;

public class TotalStrategy implements GradingStrategy {
	
	public TotalStrategy() {}

	/**
	 * calculate() method to conform to GradingStrategy type
	 * @param key
	 * @param grades
	 * @return Grade : result grade
	 */
	public Grade calculate(String key, List<Grade> grades) throws SizeException {
		if (grades == null) {
			throw new SizeException("List of grades cannot be null.");
		} else if (grades.isEmpty()) {
			throw new SizeException("List of grades cannot be empty.");
		} else {
			//to keep track of running total
			double runningTotal = 0.0;
			
			//iterate over grades list
			for (Grade grade : grades) {
				//add to running total
				runningTotal += Missing.doubleValue(grade.getValue());
			}
			
			//return result grade
			return new Grade(key, runningTotal);
		}
	}
}
