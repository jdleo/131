package grading;

public class Missing {
	//default missing value constant
	public final double DEFAULT_MISSING_VALUE = 0;
	
	/**
	 * Method to check for missing value
	 * @param number
	 * @return double
	 */
	public double doubleValue(Double number) {
		if (number == null) {
			return DEFAULT_MISSING_VALUE;
		} else {
			return (double)number;
		}
	}
	
	/**
	 * Method to check for missing value
	 * @param number
	 * @param missingValue
	 * @return double
	 */
	public double doubleValue(Double number, double missingValue) {
		if (number == null) {
			return missingValue;
		} else {
			return (double)number;
		}
	}
}
