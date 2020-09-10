package Homework1;
/**
 * ImproperMinutesException class that is invoked when an ImproperMinutesException is thrown.
 * @author Avish Parmar
 * SBUID: 112647892
 * Email: avish.parmar@stonybrook.edu
 * Course: CSE214
 */
public class ImproperMinutesException extends Exception {
	
	public ImproperMinutesException() {
		System.out.println("\nERROR: Minute cannot be negative.");
	}
}
