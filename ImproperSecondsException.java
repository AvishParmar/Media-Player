/**
 * ImproperSecondsException class that is invoked when an ImproperSecondsException is thrown.
 * @author Avish Parmar
 */
public class ImproperSecondsException extends Exception {

	public ImproperSecondsException(int index) {
		if(index == 0) 
			System.out.println("\nERROR: Seconds cannot be less than 0");
		else
			System.out.println("\nERROR: Seconds cannot be more than 59");
	}
}
