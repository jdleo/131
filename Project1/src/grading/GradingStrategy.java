package grading;
import java.util.List;

public interface GradingStrategy {
	
	//method header for this interface
	public Grade calculate (String key, List<Grade> grades) throws SizeException;
}
