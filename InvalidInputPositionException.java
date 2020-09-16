/**
 * InvalidInputPositionException class which is invoked when user tries to implement a song
 * at a position that has its previous position unfilled.
 * @author Avish Parmar
 */
public class InvalidInputPositionException extends Exception{

	
	public InvalidInputPositionException() {
		System.out.println("Invalid position for adding the new song");
	}
}
