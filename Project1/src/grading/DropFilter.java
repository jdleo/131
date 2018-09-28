package grading;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class DropFilter implements Filter {
	
	public boolean shouldDropLowest;
	public boolean shouldDropHighest;
	public int toBeDropped = 0;
	
	/**
	 * Constructor
	 */
	public DropFilter() {
		//default both to true
		this.shouldDropHighest = true;
		this.shouldDropLowest = true;
		this.toBeDropped = 2;
	}
	
	/**
	 * Constructor
	 * @param shouldDropLowest
	 * @param shouldDropHighest
	 */
	public DropFilter(boolean shouldDropLowest, boolean shouldDropHighest) {
		this.shouldDropLowest = shouldDropLowest;
		this.shouldDropHighest = shouldDropHighest;
		
		if (shouldDropLowest && !shouldDropHighest) {
			toBeDropped = 1;
		} else if (!shouldDropLowest && shouldDropHighest) {
			toBeDropped = 1;
		} else if (shouldDropLowest && shouldDropHighest) {
			toBeDropped = 2;
		} else {
			toBeDropped = 0;
		}
	}
	
	/**
	 * apply() method to conform to Filter interface
	 * @param grades : list of grades to filter
	 * @return List<Grade> : filtered list
	 */
	@Override
	public List<Grade> apply(List<Grade> grades) throws SizeException {
		if (grades == null) {
			throw new SizeException("List of grades cannot be null.");
		} else if (grades.size() < this.toBeDropped) {
			throw new SizeException("List of grades cannot be less than number to be dropped.");
		} else if (grades.size() == this.toBeDropped) {
			throw new SizeException("List of grades cannot be equal to number to be dropped.");
		} else {
			
			//result list
			List<Grade> filteredGrades = new ArrayList<Grade>();
			
			//sort grades descending
			List<Grade> sortedGrades = grades;
			Collections.sort(sortedGrades);
			
			//iterate through sorted grades
			for (int i = 0; i < sortedGrades.size(); i++) {
				if (shouldDropHighest && shouldDropLowest) {
					//skip the highest grade and lowest grade, "drop" them
					if (i != 0 && i != sortedGrades.size() - 1) {
						filteredGrades.add(sortedGrades.get(i));
					}
				} else if (shouldDropHighest) {
					//skip the highest grade, "drop" it
					if (i != 0) {
						filteredGrades.add(sortedGrades.get(i));
					}
				} else if (shouldDropLowest) {
					//skip the lowest grade, "drop" it
					if (i != sortedGrades.size() - 1) {
						filteredGrades.add(sortedGrades.get(i));
					}
				} else {
					//copy everything over
					filteredGrades.add(sortedGrades.get(i));
				}
			}
			
			//return result
			return filteredGrades;
			
		}
	}

}
