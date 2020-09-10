package Homework1;
/**
 * FullPlayListException class that is invoked when a FullPlayListException is thrown.
 * @author Avish Parmar
 * SBUID: 112647892
 * Email: avish.parmar@stonybrook.edu
 * Course: CSE214
 */
public class FullPlayListException extends Exception{

	public FullPlayListException() {
		System.out.println("\nERROR: Playlist is full, cannot add more songs.");
	}
}
