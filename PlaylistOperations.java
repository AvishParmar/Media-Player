package Homework1;

import java.io.File;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

/**
 * Main method class that runs the entire program
 * @author Avish Parmar
 * SBUID: 112647892
 * Email: avish.parmar@stonybrook.edu
 * Course: CSE214
 */
public class PlaylistOperations {

	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		
		System.out.println("MENU:\n"
				+"__________________________\r\n"
				+ "| A) Add Song \r\n" + 
				"| B) Print Songs by Artist \r\n" + 
				"| G) Get Song \r\n" + 
				"| R) Remove Song \r\n" + 
				"| P) Print All Songs \r\n" + 
				"| S) Size \r\n" + 
				"| N) Create New Playlist\r\n" + 
				"| V) Change Current Playlist \r\n" + 
				"| C) Copy Playlist \r\n" + 
				"| E) Compare Playlist \r\n" + 
				"| D) Print All Playlists \r\n" +
				"| PS) Play Song \r\n" +
				"| Q) Quit\n");
		
	
		Playlist[] playlist = new Playlist[50];
		playlist[0] = new Playlist("Default");
		int count = 1;
		int cursor = 0;
		System.out.println("\nOperating on Playlist: "+playlist[cursor].getName());
		System.out.print("\nSelect a menu option: ");
		do {
			
			
			String selection = stdin.nextLine();
			
			if(selection.equalsIgnoreCase("A")) {
				System.out.print("Enter the song title: ");
				String title = stdin.nextLine();
				
				System.out.print("Enter the song artist: ");
				String artist = stdin.nextLine();
				
				System.out.print("Enter the song length (minutes): ");
				int minutes = stdin.nextInt();
				
				System.out.print("Enter the song length (seconds): ");
				int seconds = stdin.nextInt();
				
				System.out.print("Enter the position: ");
				int position = stdin.nextInt();
				
				try {
					playlist[cursor].addSong(new SongRecord(title, artist, minutes, seconds), position);
					System.out.println("Song Added: "+title+" By "+artist );
		
				} catch (IllegalArgumentException e) {
					System.out.println("Position is not within the valid range.");
				}
				catch (FullPlayListException e) {}
				catch (ImproperMinutesException e) {} 
				catch (ImproperSecondsException e) {} 
				catch (InvalidInputPositionException e) {}
				
				System.out.print("\nSelect a menu option: ");
			}
			
			else if(selection.equalsIgnoreCase("B")) {
				
				System.out.print("Enter the artist: ");
				String artist = stdin.nextLine();
				
				try {
					Playlist.getSongsByArtist(playlist[cursor], artist).printAllSongs();
					
				} catch (IllegalArgumentException e) {
					System.out.println("Artist not found.");
				} catch (FullPlayListException e) {}
				
				System.out.print("\nSelect a menu option: ");
			}
			
			else if(selection.equalsIgnoreCase("G")) {
				System.out.print("Enter the position: ");
				int position = stdin.nextInt();
				try {
					System.out.println("Song#     Title           Artist          Length\r\n" + 
							"------------------------------------------------");
					
					System.out.print(position);
					System.out.print(playlist[cursor].getSong(position).toString());
				}catch(IllegalArgumentException ex) {
					System.out.println("Invalid Position inputted.");
				}catch(NullPointerException ex) {}
				
				System.out.print("\nSelect a menu option: ");
			}
			
			else if(selection.equalsIgnoreCase("R")) {
				
				System.out.print("Enter the position: ");
				int position = stdin.nextInt();
				
				try {
					playlist[cursor].removeSong(position);
				}catch(IllegalArgumentException ex) {
					System.out.println("\nInvalid Position inputted.");
				}
			
				System.out.print("\nSelect a menu option: ");
			}
			
			else if(selection.equalsIgnoreCase("P")) {
				
				playlist[cursor].printAllSongs();
				System.out.print("\nSelect a menu option: ");
			}
			
			else if(selection.equalsIgnoreCase("S")) {
				System.out.println("\nThere are "+playlist[cursor].size()+" song(s) in the current playlist.");
				System.out.print("\nSelect a menu option: ");
			}
			
			else if(selection.equalsIgnoreCase("N")) {
				
				if(count == playlist.length) {
					System.out.println("Playlist storage is full, cannot add more playlists.");
				}
			
				else {
					System.out.print("Enter a name for the Playlist: ");
					String name = stdin.nextLine();
					for(int i = 0; i < count; i++) {
						if(playlist[i].getName().equals(name)) {
							System.out.print("\nPlaylist of this name already exists, choose another name: ");
							name = stdin.nextLine();
							break;
						}
					}
					playlist[count] = new Playlist(name);
					
					count++;
					cursor = count - 1;
					System.out.println("\nPlaylist added! Now operating on Playlist "+playlist[cursor].getName());
				}
				
				System.out.print("\nSelect a menu option: ");
			}
			else if(selection.equalsIgnoreCase("V")) {
				
				System.out.print("Input name of playlist: ");
				String name = stdin.nextLine();
				
				for(int i = 0; i < playlist.length; i++) {
					if(playlist[i].getName().equals(name)) {
						cursor = i;
						System.out.println("Switched to Playlist "+playlist[cursor].getName());
						break;
					}
				}
				System.out.print("\nSelect a menu option: ");
			}
			else if(selection.equalsIgnoreCase("C")) {
				if(count == playlist.length) {
					System.out.println("Playlist storage is full, cannot copy playlists.");
				}else {
					System.out.print("Input name of playlist: ");
					String name = stdin.nextLine();
					
					try {
						playlist[count] = (Playlist) playlist[cursor].clone();
						playlist[count].setName(name);
					} catch (CloneNotSupportedException e) {
						System.out.println("ERROR: CloneNotSupportException thrown");
					}
					
					count++;
					cursor = count-1;
					System.out.println("Operating on Playlist "+playlist[cursor].getName());
				}
					
				System.out.print("\nSelect a menu option: ");
			}
			else if(selection.equalsIgnoreCase("E")) { 
				System.out.print("Input name of playlist to compare: ");
				String name = stdin.nextLine();
				int cursor2, i = 0;
				
				while(i < count) {
					if(playlist[i].getName().equals(name)) {
						cursor2 = i;
						if(playlist[cursor].size() != playlist[cursor2].size()) {
							System.out.println(playlist[cursor].getName()+" and "+playlist[cursor2].getName()+" are of different sizes"
									+ ", therefore they are not the same.");
							break;
						}
						else if(playlist[cursor].equals(playlist[cursor2]) == true) {
							System.out.println(playlist[cursor].getName()+" and "+playlist[cursor2].getName()+" both have the same songs.");
						}
						else {
							System.out.println(playlist[cursor].getName()+" and "+playlist[cursor2].getName()+" have different songs.");
						}
						break;
					}
					else
						i++;
				}
				
				if(i == count) {
					System.out.println("Playlist of that name does not exist.");
				}
				
				System.out.print("\nSelect a menu option: ");
			}
			else if(selection.equalsIgnoreCase("D")) {
				
				System.out.println("Playlist#     Playlist Name\r\n" + 
						"---------------------------");
				
				for(int i = 0; i < count; i++) {
					String playlistInfo = String.format("%-14d%s", i+1,playlist[i].getName());
					System.out.println(playlistInfo);
				}
				System.out.print("\nSelect a menu option: ");
			}
			
			else if(selection.equalsIgnoreCase("PS")) {
				System.out.println("CAUTION: The song's .wav file must be in the project folder for the song to be played!");
				
				System.out.println("\nUSAGE NOTE: You don't need to include .wav at the end of song name, just make sure the file name matches. ");
				System.out.print("\nInput the name of the song you wish to play: ");
				String songName = stdin.nextLine();
				System.out.print("Input the artist of the song you inputted: ");
				String songArtist = stdin.nextLine();
				int i = 0;

				while(i < playlist[cursor].size()) {
					if(songName.equals(playlist[cursor].getSong(i+1).getTitle()) && 
							songArtist.equals(playlist[cursor].getSong(i+1).getArtist())) {
						songName = songName + ".wav";
						File song = new File(songName);
						PlaySong(song);
						break;
					}
					else
						i++;
				}
				
				if(i == playlist[cursor].size()) {
					System.out.println("\nThe song title with the given artist was not found in this playlist!\n");
				}
				
				System.out.print("Select a menu option: ");
				
			}
			
			else if(selection.equalsIgnoreCase("Q")) {
				System.out.println("\nProgram terminating normally...");
				stdin.close();
				System.exit(1);
			}
			
		}while(true);
	}
	
	/**
	 * Plays the song inputed by the user
	 * @param songName
	 * Name of the song
	 */
	public static void PlaySong(File songName) {
		Scanner stdin = new Scanner(System.in);
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(songName));
			clip.start();
			System.out.print("\nPress E to stop playing and return to menu: ");
			
			String s = stdin.nextLine();
			if(s.equalsIgnoreCase("E")) {
				
				System.out.println("\nExiting to main menu!\n");
				clip.stop();
			}
			
		}
		catch(Exception e) {
			System.out.println("File was not found, make sure the file is in the "
					+ "java project file or that the song with the given name exists in memory!");
		}
	}
	

}
