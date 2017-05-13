
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main class that holds the main method that starts things off.
 * @author Nick Sprinkle
 * @version 4/26/2016
 *
 */
public class myProgram {

	/**
	 * This method starts off the program.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Cache cache = new Cache(args);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
