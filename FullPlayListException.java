/**
 * FullPlayListException class that is invoked when a FullPlayListException is thrown.
 * @author Avish Parmar
 */
public class FullPlayListException extends Exception{

	public FullPlayListException() {
		System.out.println("\nERROR: Playlist is full, cannot add more songs.");
	}
}
