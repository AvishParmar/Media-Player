/**
 * ImproperMinutesException class that is invoked when an ImproperMinutesException is thrown.
 * @author Avish Parmar
 */
public class ImproperMinutesException extends Exception {
	
	public ImproperMinutesException() {
		System.out.println("\nERROR: Minute cannot be negative.");
	}
}
