package HW4;
/**
 * Homework 3
 * Gaurav Ravichandran, gtr5ew
 *
 * Sources: Lecture, Big Java Book
 */
import java.util.ArrayList;

public abstract class PhotoContainer {
	
	// fields
	/**
	 * The name of the PhotoContainer
	 */
	protected String name;
	/**
	 * An ArrayList of Photo objects stored in the PhotoContainer
	 */
	protected ArrayList<Photo> photos;
	
	// constructor
	public PhotoContainer(String name) {
		this.name = name;
		photos = new ArrayList<Photo>();
	}
	
	//accessor methods
	/**
	 * Returns the name of the given PhotoContainer
	 * @param no parameters needed
	 * @return the name of the given PhotoContainer
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns a list of Photos in the PhotoContainer
	 * @param no parameters needed
	 * @return the ArrayList photos
	 */
	public ArrayList<Photo> getPhotos() {
		return photos;
	}
	
	//mutator methods
	/**
	 * Sets the name of the given PhotoContainer to the passed name
	 * @param name - the new name of the given PhotoContainer
	 * @return nothing; void method
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	//methods
	/**
	 * Adds a Photo to your current Album based on whether the Photo was already in the
	 * Album or not
	 * @param p - an instance of a Photo to be added
	 * @return true or false based on whether the photo was successfully added
	 */
	public boolean addPhoto(Photo p) {
		if (p == null) {
			return false;
		}
		else if (photos.contains(p) == false) {
			photos.add(p);
			return true;
		}
		return false;
	}
	
	/**
	 * Checks to see whether the passed Photo is already in the PhotoContainer
	 * @param p - an instance of a Photo that is being checked for
	 * @return true or false based on whether the given Photo was found in the Album
	 */
	public boolean hasPhoto(Photo p) {
		return photos.contains(p);
	}
	
	/**
	 * Removes a given Photo from a PhotoContainer
	 * @param p - an instance of a Photo that will be removed from the Album
	 * @return true or false based on whether the Photo was removed successfully
	 */
	public boolean removePhoto(Photo p) {
		if (photos.contains(p) == true) {
			photos.remove(p);
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the number of Photos in the PhotoContainer
	 * @param no parameters needed
	 * @return the size of photos
	 */
	public int numPhotos() {
		return photos.size();
	}
	
	/**
	 * Checks to see if the passed object is a PhotoContainer, and whether that PhotoContainer's name
	 * is equivalent to the current PhotoContainer's name
	 * @param o - an Object that could be an PhotoContainer
	 * @return true or false based on whether the the PhotoContainer and Object are equivalent
	 */
	public boolean equals(Object o) {
		if (o instanceof PhotoContainer) {
			PhotoContainer obj = (PhotoContainer) o;
			return obj.name.equals(name);
		}
		return false;
	}
	
	/**
	 * Returns a String representation of a PhotoContainer
	 * @param no parameters needed
	 * @return a String representation of an PhotoContainer (PhotoContainer name and Photo filenames)
	 */
	public String toString() {
		ArrayList<String> filenames = new ArrayList<String>();
		for (Photo p : photos) {
			filenames.add(p.getFilename());
		}
		
		return "PhotoContainer Name: " + name + "\n" + "Photo File Names: " + filenames;
	}
	
	/**
	 * Returns the hash code for the name of the Album
	 * @param no parameters needed
	 * @return unique hash code for the name of the Album
	 */
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * Returns an ArrayList of all Photos with a rating greater than or equal to
	 * the passed one
	 * @param rating - the rating benchmark that the user would like to set
	 * @return if one of the Photo's ratings is invalid, return null
	 * 		   else, return an ArrayList of all the Photos equal to or above the
	 * 		   given rating
	 */
	public ArrayList<Photo> getPhotos(int rating) {
		ArrayList<Photo> getPhoto = new ArrayList<Photo>();
		for (Photo p : photos) {
			if (rating < 1 || rating > 5) {
				return null;
			}
			else if (p.getRating() >= rating) {
				getPhoto.add(p);
			}
		}
		return getPhoto;
	}
	
	/**
	 * Returns an ArrayList of all the Photos taken in a specified year
	 * @param year - the user specified year from which Photos will be collected
	 * @return if one of the Photos' year is invalid, return null
	 * 		   else, return an ArrayList of Photos taken within the given year
	 */
	public ArrayList<Photo> getPhotosInYear(int year) {
		if (year < 1 || year > 9999) {
			return null;
		}
		ArrayList<Photo> photosInYear = new ArrayList<Photo>();
		for (Photo p : photos) {
			if ( (DateLibrary.isValidDate(p.getDateTaken())) == false) {
				return null;
			}
			else if ( (DateLibrary.getYear(p.getDateTaken())) == year) {
				photosInYear.add(p);
			}
		}
		return photosInYear;
	}
	
	/**
	 * Returns an ArrayList of all the Photos taken in the specified year and month
	 * @param year, month - the year and the number of the month that the user specifies
	 * @return if one of the Photos' year or month is invalid, return null
	 * 		   else, return an ArrayList consisting of all the Photos that were taken
	 * 		   in the user specified year and month
	 */
	public ArrayList<Photo> getPhotosInMonth(int month, int year) {
		if (month < 1 || month > 12) {
			return null;
		}
		else if (year < 1 || year > 9999) {
			return null;
		}
		ArrayList<Photo> photosInMonth = new ArrayList<Photo>();
		for (Photo p : photos) {
			if ( (DateLibrary.isValidDate(p.getDateTaken()) == false)) {
				return null;
			}
			else if ( (DateLibrary.getYear(p.getDateTaken()) == year) && (DateLibrary.getMonth(p.getDateTaken()) == month)) {
				photosInMonth.add(p);
			}
		}
		return photosInMonth;
	}
	
	/**
	 * Returns an ArrayList of the Photos taken between the two given dates
	 * @param beginDate, endDate - the two extreme dates that the user specifies
	 * @return if either date's format is invalid, or end date is before begin date,
	 * 		   return null
	 * 		   else, return an ArrayList of the Photos taken between the specified
	 * 		   dates
	 */
	public ArrayList<Photo> getPhotosBetween(String beginDate, String endDate) {
		ArrayList<Photo> photosBetween = new ArrayList<Photo>();
		if (DateLibrary.isValidDate(beginDate) == false || DateLibrary.isValidDate(endDate) == false) {
			return null;
		}
		else if (DateLibrary.compare(beginDate, endDate) > 0) {
			return null;
		}
		for (Photo p : photos) {
			if ( (DateLibrary.compare(p.getDateTaken(), beginDate) > 0) && (DateLibrary.compare(p.getDateTaken(), endDate) < 0) ) {
				photosBetween.add(p);
			}
		}
		return photosBetween;
	}
	
	
}

