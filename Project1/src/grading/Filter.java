package grading;
import java.util.List;

public interface Filter {
	
	//method header for this interface
	public List<Grade> apply(List<Grade> grades) throws SizeException;
}
