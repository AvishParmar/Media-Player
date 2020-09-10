package Homework1;
/**
 * SongRecord class that creates a SongRecord object
 * @author Avish Parmar
 * SBUID: 112647892
 * Email: avish.parmar@stonybrook.edu
 * Course: CSE214
 */
public class SongRecord implements Cloneable {
	
	private String title;
	private String artist;
	private int minutes;
	private int seconds;
	
	/**
	 * Default constructor for SongRecord class
	 */
	public SongRecord() {
		
	}
	/**
	 * Constructor for SongRecord class that initializes the data fields.
	 * @param title
	 * Title of the Song
	 * @param artist
	 * Artist of the Song
	 * @param minutes
	 * Minute length of the Song
	 * @param seconds
	 * Second length of the Song
	 * @throws ImproperMinutesException
	 * When minute length of the song is less than 0
	 * @throws ImproperSecondsException
	 * When the second length of the song is less than 0 or more than 59
	 */
	public SongRecord(String title, String artist, int minutes, int seconds)
			throws ImproperMinutesException, ImproperSecondsException {
		setTitle(title);
		setArtist(artist);
		setMinutes(minutes);
		setSeconds(seconds);
	}
	
	/**
	 * Accessor method for title data field
	 * @return Title of the song
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Mutator method for title data field
	 * Sets the title of the song
	 * @param title
	 * Title of the song
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Accessor method for artist data field
	 * @return Name of artist
	 */
	public String getArtist() {
		return this.artist;
	}
	
	/**
	 * Mutator method for artist data field
	 * Sets the name of the artist of the song
	 * @param artist
	 * Name of artist
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	/**
	 * Accesor method for minutes data field
	 * @return Minute length of the song
	 */
	public int getMinutes() {
		return this.minutes;
	}
	
	/**
	 * Mutator method for minutes data field
	 * Sets the minute length of the song
	 * @param minutes
	 * Minute length of the song
	 * @throws ImproperMinutesException 
	 * When the minute input is less than 0
	 */
	public void setMinutes(int minutes) throws ImproperMinutesException {
		if(minutes < 0)
			throw new ImproperMinutesException();
		else
			this.minutes = minutes;
	}
	
	/**
	 * Accessor method for seconds data field
	 * @return Seconds length of the song
	 */
	public int getSeconds() {
		return this.seconds;
	}
	
	/**
	 * Mutator method for the seconds data field
	 * @param seconds
	 * Seconds length of the song
	 * @throws ImproperSecondsException 
	 * When the seconds input is less than 0 or greater than 59
	 */
	public void setSeconds(int seconds) throws ImproperSecondsException {
		if(seconds < 0) {
			throw new ImproperSecondsException(0);
		}
		else if(seconds > 59) {
			throw new ImproperSecondsException(1);
		}
		else
			this.seconds = seconds;
	}
	/**
	 * Equals method that checks whether two SongRecord objects are the same
	 */
	public boolean equals(Object obj) {
		if(obj instanceof SongRecord) {
			SongRecord this2 = (SongRecord) obj;
			if(this.getArtist().equals(this2.getArtist()) && this.getTitle().equals(this2.getTitle())
					&& this.getMinutes() == this2.getMinutes() && this.getSeconds() == this2.getSeconds())
				return true;
				
			else
				return false;
		}
		return false;
	}
	/**
	 * Clone method that clones a SongRecord object.
	 */
	public Object clone() throws CloneNotSupportedException {
		
		return super.clone();
	}
	
	/**
	 * Neatly prints the information about the SongRecord object.
	 */
	public String toString() {
		
		String recordInfo = String.format("%9s%-16s%2s%10s%d:%02d","        ", getTitle(), getArtist()," ", getMinutes(), getSeconds());
		return recordInfo;
	}
	
	
}
