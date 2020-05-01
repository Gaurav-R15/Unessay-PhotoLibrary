package HW4;
/**
 * Homework 1B / Homework 2B
 * Gaurav Ravichandran, gtr5ew
 *
 * Sources: Lecture Slides, Big Java Book 
 */

import java.util.ArrayList;  
import java.util.HashSet;

public class Library extends PhotoContainer{
	// fields
	/**
	 * Contains the Library's unique ID
	 */
	private final int id;
	/**
	 * HashSet that stores all Albums within the Library
	 */
	private HashSet<Album> albums;
	
	// constructor
	public Library(String name, int id) {
		super(name);
		this.id = id;
		albums = new HashSet<Album>();
	}
	
	public static void main(String[] args) {
		Photo picture = new Photo("landscape.txt", "It's raining");
		
		Library a = new Library("a", 111);
		Library b = new Library("b", 112);
		Library c = new Library("c", 113);
		Library d = new Library("d", 111);
		
		// .addPhoto method test
		System.out.println(a.addPhoto(picture)); // Should return true
		System.out.println(b.addPhoto(picture)); // Should return true
		System.out.println(b.addPhoto(picture)); // Should return false
		
		// .hasPhoto method test
		System.out.println(a.hasPhoto(picture)); // Should return true
		System.out.println(c.hasPhoto(picture)); // Should return false
		
		// .deletePhoto method test
		System.out.println(b.removePhoto(picture)); // Should return true
		System.out.println(c.removePhoto(picture)); // Should return false
		
		// .numPhotos method test
		System.out.println(a.numPhotos()); // Should return 1
		System.out.println(b.numPhotos()); // Should return 0
		
		// .equals method tests
		System.out.println(a.equals(b)); // Should return false
		System.out.println(d.equals(a)); // Should return true
		
		// .toString method tests
		System.out.println(a.toString()); // Should return a String representation of a Library
		System.out.println(b.toString()); // Should return a String representation of a Library
		
		b.addPhoto(picture);
		
		// .commonPhotos method tests
		System.out.println(Library.commonPhotos(a, b)); // Should return an ArrayList of one Photo
		System.out.println(Library.commonPhotos(b, c)); // Should return an empty ArrayList
		
		// .similarity method tests
		System.out.println(Library.similarity(a, b)); // Should return 1.0
		System.out.println(Library.similarity(b, c)); // Should return 0.0
		
 	}
	
	// Getters
	/**
	 * Returns the unique ID of the Library
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the Albums stored in the Library
	 */
	public HashSet<Album> getAlbums() {
		return albums;
	}
	
	// Methods		
	/**
	 * Deletes a Photo from a Library
	 * @param p - the Photo that the user wishes to delete
	 * @return true or false based on whether the Photo was successfully deleted
	 */
	public boolean removePhoto(Photo p) {
		if (photos.contains(p)) {
			photos.remove(p);
			for (Album a : albums) {
				if (a.hasPhoto(p)) {
					a.removePhoto(p);
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Checks to see if two Libraries are equivalent based on their unique IDs
	 * @param o - An object given by the user
	 * @return true or false depending on whether the given Libraries are equal
	 */
	public boolean equals(Object o) {
		if (o instanceof Library) {
			Library obj = (Library) o;
			return id == obj.id;
		}
		return false;
	}
	
	/**
	 * Returns a String representation of the Library, including the name, id, and photos
	 */
	public String toString() {
		return "Name: " + name + "\n" +"ID: " + id + "\n" + "Photos: " + photos + "\n" + "Albums: " + albums;
	}
	
	/**
	 * Determines which Photos the two parameter Libraries have in common
	 * @param a - The first Library the user would like to use to compare
	 * @param b - the second Library the user would like to use to compare
	 * @return common - an ArrayList of all the Photos the two given Libraries have in common
	 */
	public static ArrayList<Photo> commonPhotos(Library a, Library b) {
		ArrayList<Photo> common = new ArrayList<Photo>();
		for (Photo a_photo : a.photos) {
			for (Photo b_photo: b.photos) {
				if (a_photo.equals(b_photo)) {
					common.add(a_photo);
				}
			}
		}
		return common;
	}
	
	/**
	 * Returns an index of how similar the two given Libraries are,
	 * if either Libraries house 0 Photos, the default return is 0.0
	 * @param a - The first Library the user would like to use to compare
	 * @param b - the second Library the user would like to use to compare
	 * @return The number of common photos the two Libraries 
	 * 		   share divided by the smaller number of Photos between Libraries
	 * 		   a and b (the index)
	 */
	public static double similarity(Library a, Library b) {
		if (a.photos.size() == 0 || b.photos.size() == 0) {
			return 0.0;
		}
		double common = commonPhotos(a, b).size();
		double smallest = 0;
		if (a.photos.size() > b.photos.size()) {
			smallest = b.photos.size();
		}
		else {
			smallest = a.photos.size();
		}
		return common / smallest;
	}
	
	/**
	 * Creates an Album with the name specified by the user, if an Album with the
	 * specified name is already in the HashSet albums, the Album will not be created
	 * @param albumName - the name the user would like to give the album
	 * @return true or false based on whether the album was successfully added
	 */
	public boolean createAlbum(String albumName) {
		Album newAlbum = new Album(albumName);
		if (albums.add(newAlbum) == true) {
			albums.add(newAlbum);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes an album with the specified name from the HashSet albums
	 * @param albumName - the specified name of the Album that the user would like to
	 * 					  remove
	 * @return true or false based on whether the album was successfully removed
	 */
	public boolean removeAlbum(String albumName) {
		for (Album a : albums) {
			if (a.getName().equals(albumName)) {
				albums.remove(a);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds a specified Photo to an Album in the HashSet albums with name albumName
	 * if and only if the Photo is already in the Library's ArrayList photos and not
	 * already in the Album
	 * @param p, albumName - The specified Photo that the user would like to add as well
	 * 						 as the albumName of the Album they would like to add it to
	 * @return true or false based on whether the adding of the Photo was successful
	 */
	public boolean addPhotoToAlbum(Photo p, String albumName) {
		if (photos.contains(p) == false) {
			return false;
		}
		for (Album a : albums) {
			if (a.getName().equals(albumName)) {
				if (a.hasPhoto(p) == false) {
					a.addPhoto(p);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Removes a specified Photo from an Album with a specified albumName
	 * @param p, albumName - The specified Photo that the user would like to remove from
	 * 						 an Album with the specified albumName
	 * @return true or false based on whether the given Photo was successfully removed
	 * 		   from the Album
	 */
	public boolean removePhotoFromAlbum(Photo p, String albumName) {
		for (Album a : albums) {
			if (a.getName().equals(albumName)) {
				if (a.hasPhoto(p) == true) {
					a.removePhoto(p);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns an Album within albums with the specified albumName
	 * @param albumName - the user specified Album name to look for
	 * @return if no such Album with the given name is found, null is returned
	 * 		   else, the found Album is returned
	 */
	private Album getAlbumByName(String albumName) {
		for (Album a : albums) {
			if (a.getName().equals(albumName)) {
				return a;
			}
		}
		return null;
	}
}
