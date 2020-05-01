package HW4;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 * Homework 1A / Homework 2B
 * Gaurav Ravichandran, gtr5ew
 * 
 * Sources: Lectures, Big Java Book
 */

public class Photo implements Comparable<Photo> {
	//fields
	/**
	 * caption of the given photo
	 */
	private final String caption;
	/**
	 * file name of the given photo
	 */
	private final String fileName;
	/**
	 * rating of the given photo
	 */
	private int rating;
	/**
	 * date that the photo was taken
	 */
	private String dateTaken;
	/**
	 * Where the image affiliated with the given Photo is stored
	 */
	protected BufferedImage imageData;
	
	//constructors
	public Photo (String fileName, String caption) {
		this.fileName = fileName;
		this.caption = caption;
		rating = 1;
		dateTaken = "1901-01-01";
	}
	
	public Photo (String fileName, String caption, String dateTaken, int rating) {
		this.fileName = fileName;
		this.caption = caption;
		
		if (DateLibrary.isValidDate(dateTaken) == false) {
			this.dateTaken = "1901-01-01";
		}
		else {
			this.dateTaken = dateTaken;
		}
		
		if (rating < 6 && rating >  0) {
			this.rating = rating;
		}
		else {
			this.rating = 1;
		}
	}
	
	public static void main(String[] args) {
		Photo one = new Photo("zebra.png", "A zebra");
		Photo two = new Photo("elephant.png", "A elephant");
		Photo three = new Photo("zebra.png", "A zebra", "1901-01-01", 3);
		
		//setRating tests
		one.setRating(4); //Should set rating of Photo one to 4
		two.setRating(5); //Should set rating of Photo two to 5
		
		System.out.println(one.toString()); //Returns the file name, caption, and rating of Photo one
		System.out.println(two.toString()); //Returns the file name, caption, and rating of Photo two
		
		//.equals() tests
		System.out.println(one.equals(two)); //Should return false
		System.out.println(one.equals(three)); //Should return true
	}
	
	//accessor methods
	
	/**
	 * Returns the caption of the given Photo
	 * @param no parameters needed
	 * @return caption; returns the caption of the photo
	 */
	public String getCaption() {
		return caption;
	}
	
	/**
	 * Returns file name of the given Photo
	 * @param no parameters needed
	 * @return file name; returns the file name of the photo
	 */
	public String getFilename() {
		return fileName;
	}
	
	/**
	 * Returns the rating of the given Photo
	 * @param no parameters needed
	 * @return rating; returns the rating of the photo
	 */
	public int getRating() {
		return rating;
	}
	
	/**
	 * Returns the date that the photo was taken
	 * @param no parameters needed
	 * @return date taken; returns the date that the photo was taken
	 */
	public String getDateTaken() {
		return dateTaken;
	}
	
	/**
	 * Returns the image associated with the Photo
	 * @param no parameters needed
	 * @return image associated with the Photo
	 */
	public BufferedImage getImageData() {
		return imageData;
	}
	
	//mutators
	
	/**
	 * Sets the imageData for the current Photo to the new specified imageData
	 * @param imageData - the new image the user would like to set the photo to
	 * @return nothing returned; void method
	 */
	public void setImageData(BufferedImage imageData) {
		this.imageData = imageData;
	}
	
	//methods
	
	/**
	 * This method allows the user to set a new rating for a specified photo
	 * @param newRating; the new rating that the user would like to set for the photo
	 * @return true or false; if the new rating is valid (not the same as the old rating
	 * or not out of bounds of the accepted rating values) returns true, else, returns
	 * false
	 */
	public boolean setRating(int newRating) {
		if ((newRating != rating) && (newRating > 0 && newRating < 6)) {
			rating = newRating;
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * This method returns the hash code for the filename
	 * @param no parameters needed
	 * @return hash code for filename  
	 */
	public int hashCode() {
		return fileName.hashCode();
	}
		
	/**
	 * This method checks to see if two Photo objects are equivalent (have the same
	 * file name and caption)
	 * @param obj; takes in an object of any kind
	 * @return true or false; returns true if the corresponding fields of the Photo
	 * objects are equivalent
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Photo) { //Checks to see if the object is an instance of the Photo class
			Photo two = (Photo) obj;
			return this.caption.equals(two.caption) && 
					this.fileName.equals(two.fileName);
		}
		return false;
	}
	
	/**
	 * This method is designed to return a String representation of the Photo (the file
	 * name, caption, and rating of the photo)
	 * @param no parameters needed
	 * @return file name, caption, and rating of the photo in String format
	 */
	public String toString() {
		return "File Name: " + fileName + "\n" + 
				"Caption: " + caption + "\n" +
				"Rating: " + rating;
	}
	
	/**
	 * Compares first the date that two photos were taken, if they are equal,
	 * Will return a numerical comparison of both Photo's captions
	 * @param p - An instance of a Photo that the user would like to compare to
	 * @return an integer representation of the comparison, if the
	 * current Photo's date comes before the passed Photo's number, a negative
	 * number is returned, if it is after, a positive number, and if they are equal,
	 * 0 is returned
	 */
	public int compareTo(Photo p) {
		int retVal = DateLibrary.compare(dateTaken, p.getDateTaken());
		if (retVal == 0) {
			retVal = caption.compareTo(p.getCaption());
			return retVal;
		}
		return retVal;
	}
	
	/**
	 * Loads an image into the current Photo
	 * @param filename - the name of the file containing the photo that the user would like to load
	 * @return true or false based on whether the image was successfully loaded
	 */
	public boolean loadImageData(String filename) {
		try {
			imageData = ImageIO.read(new File(filename));
		}
		catch (IOException e) {
			return false;
		}
		return true;
	}
}