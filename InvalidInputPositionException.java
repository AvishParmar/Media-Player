package Homework1;
/**
 * InvalidInputPositionException class which is invoked when user tries to implement a song
 * at a position that has its previous position unfilled.
 * @author Avish Parmar
 * SBUID: 112647892
 * Email: avish.parmar@stonybrook.edu
 * Course: CSE214
 */
public class InvalidInputPositionException extends Exception{

	
	public InvalidInputPositionException() {
		System.out.println("Invalid position for adding the new song");
	}
}
