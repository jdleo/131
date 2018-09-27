package grading;

public class Grade implements Comparable<Grade> {
	
	//data fields for this class
	private String key;
	private Double value;
	
	/**
	 * compareTo() method to conform to Comparable type implementation
	 * @param other
	 * @return int : result of comparing values of two Grades
	 */
	@Override
	public int compareTo(Grade other) {
		if (this.value == null && other.value != null) {
			return -1;
		} else if (this.value == null && other.value == null) {
			return 0;
		} else if (this.value != null && other.value == null) {
			return 1;
		} else {
			//both values are non-null, continue with standard comparison
			return this.value.compareTo(other.value);
		}
	}
	
	/**
	 * Constructor
	 * @param key
	 */
	public Grade(String key) throws IllegalArgumentException {
		//error handling
		if (key == null || key == "") {
			throw new IllegalArgumentException("Key cannot be null or empty.");
		}
		
		this.key = key;
		this.value = 0.0;
	}
	
	/**
	 * Constructor (java.lang.Double)
	 * @param key
	 * @param value
	 */
	public Grade(String key, Double value) throws IllegalArgumentException {
		//error handling
		if (key == null || key == "") {
			throw new IllegalArgumentException("Key cannot be null or empty.");
		}
		
		this.key = key;
		this.value = value;	
	}
	
	/**
	 * Constructor (double)
	 * @param key
	 * @param value
	 */
	public Grade(String key, double value) throws IllegalArgumentException {
		//error handling
		if (key == null || key == "") {
			throw new IllegalArgumentException("Key cannot be null or empty.");
		}
		
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Getter for key
	 * @return String
	 */
	public String getKey() {
		return this.key;
	}
	
	/**
	 * Getter for value
	 * @return Double
	 */
	public Double getValue() {
		return this.value;
	}
	
	/**
	 * toString() implementation
	 * @return String : checks if null, then returns key/value as readable String
	 */
	public String toString() {
		if (value != null) {
			return String.format("%s: %5s", key, this.value);
		} else {
			return String.format("%s: %5.1f", key, "NA");
		}
	}
}
