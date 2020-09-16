/**
 * Playlist class that stores all the song records
 * @author Avish Parmar
 */
public class Playlist implements Cloneable{
	
	private static final int MAX_VALUE = 50;
	
	private SongRecord[] records;
	private String name;
	private int count;
	/**
	 * Default constructor for Playlist class
	 */
	public Playlist() {
		this.records = new SongRecord[MAX_VALUE];
	}
	
	public Playlist(String name) {
		this.records = new SongRecord[MAX_VALUE];
		setName(name);
	}
	/**
	 * Constructor for Playlist class
	 * @param records
	 * Name of array that will hold song records
	 */
	public Playlist(SongRecord[] records) {
		this.records = records.clone();
	}
	/**
	 * Sets the name of the playlist
	 * @param name
	 * Name of Playlist
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets the name of the playlist
	 * @return name
	 * Name of Playlist
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Clone method that clones the playlist.
	 */
	public Object clone() throws CloneNotSupportedException {
		Playlist clone = new Playlist();
		for(int i = 0; i < count; i++) {
			clone.records[i] = (SongRecord)records[i].clone();
		}
		clone.setSize(count);
		return (Playlist) clone;
	}
	/**
	 * Equals method that checks whether two playlists are the same
	 */
	public boolean equals(Object obj) {
		
		
		if(obj instanceof Playlist) {
			Playlist records2 = (Playlist) obj;
			if(records2.size() != this.size()) {
				return false;
			}
			for(int i = 0; i < count; i++) {
				if(records[i].equals(records2.records[i]) == true) {
					continue;
				}
				else
					return false;
			}
			return true;
		}
		return false;
	}
	/**
	 * Sets the size of the playlist object
	 * @param count
	 * Number of song records in the playlist
	 */
	public void setSize(int count) {
		this.count = count;
	}
	/**
	 * Size method that outputs the size of the playlist
	 * @return count
	 * Number of songs in the playlist
	 */
	public int size() {
		return this.count;	
	}
	/**
	 * Adds song to the playlist
	 * @param song
	 * SongRecord object
	 * @param position
	 * Position in the playlist where the song would be inserted
	 * @throws FullPlayListException
	 * When playlist is full, i.e. consists of 50 songs
	 * @throws IllegalArgumentException
	 * When the position is less than or equal to 0 or greater than 50
	 * @throws InvalidInputPositionException 
	 */
	public void addSong(SongRecord song, int position) throws FullPlayListException, IllegalArgumentException, InvalidInputPositionException{
		if(this.size() == MAX_VALUE) {
			throw new FullPlayListException();
		}
		else if(position <= 0 || position > MAX_VALUE) {
			throw new IllegalArgumentException();
		}
		else if(position - count > 1) {
			throw new InvalidInputPositionException();
		}
		SongRecord[] temp = new SongRecord[MAX_VALUE];
		
		for(int i = 0; i < position - 1; i++) {
			temp[i] = records[i];
		}
		temp[position-1] = song;
		for(int i = position; i < MAX_VALUE; i++) {
			temp[i] = records[i-1];
		}
		count++;
		records = temp;
	}
	/**
	 * Removes song at specified position from the playlist
	 * @param position
	 * Index of the song in the playlist
	 * @throws IllegalArgumentException
	 * When the index is less than or equal to 0 or greater than 50
	 */
	public void removeSong(int position) throws IllegalArgumentException{
		if(position <= 0 || position > MAX_VALUE) {
			throw new IllegalArgumentException();
		}
		if(records[position - 1] == null) {
			System.out.println("No Song found at Position "+position);
		}
		else {
			for(int i = position - 1; i < MAX_VALUE - 1; i++) {
				records[i] = records[i+1];	
				
			}
			count--;
			System.out.println("Song Removed at Position "+position);
		}
		
	}
	/**
	 * Prints the song at specified position in the playlist
	 * @param position
	 * Index of the song
	 * @return SongRecord Object
	 * 
	 * @throws IllegalArgumentException
	 * When index provided is less than or equal to 0 or greater than 50
	 */
	public SongRecord getSong(int position) throws IllegalArgumentException {
		if(position <= 0 || position > 50) {
			throw new IllegalArgumentException();
		}
		
		return records[position-1];
	}
	
	/**
	 * Prints all songs in the playlist
	 */
	public void printAllSongs() {
		System.out.println("Song#     Title           Artist          Length\r\n" + 
				"------------------------------------------------");
		for(int i = 0; i < MAX_VALUE; i++) {
			if(records[i] != null){
				System.out.print(i+1);
				System.out.print(records[i].toString());
				System.out.println();
			}	
		}
	}
	/**
	 * Filters songs according to artist
	 * @param originalList
	 * Original playlist that consists of the song records
	 * @param artist
	 * Name of the artist
	 * @return clonedList
	 * A new playlist that only consists of songs by the inputted author
	 * @throws FullPlayListException
	 * When playlist is full, i.e. has 50 songs.
	 */
	public static Playlist getSongsByArtist(Playlist originalList, String artist) throws FullPlayListException {
		if(originalList.equals(null) || artist.equals(null)) {
			return null;
		
		}
		Playlist clonedList = new Playlist();
		try {
			for(int i = 0; i < MAX_VALUE; i++) {
				if(originalList.records[i].getArtist().equals(artist)) {
					clonedList.addSong(originalList.records[i], clonedList.size()+1);
				}	
			}
		}catch(NullPointerException ex) {
			return clonedList;
		} 
		catch (IllegalArgumentException e) {} 
		catch (InvalidInputPositionException e) {}
		
		return clonedList;
	}
	/**
	 * Calls printAllSongs() method to print the information of the playlist
	 */
	public String toString() {
		printAllSongs();
		return "";
	}
	
}
