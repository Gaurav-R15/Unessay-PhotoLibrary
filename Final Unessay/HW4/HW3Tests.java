package HW4;
/**
 * Homework 2B
 * Gaurav Ravichandran, gtr5ew
 * 
 * Sources: Lecture, Big Java Book
 */


import static org.junit.Assert.*;  

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class HW3Tests {

	Library test = new Library("First", 001);
	Photo one = new Photo("one", "first photo");
	Photo two = new Photo ("two", "second photo", "2001-03-20", 4);
	Photo three = new Photo("three", "third photo", "2001-03-06", 5);
	Photo four = new Photo("four", "fourth photo", "1999-01-01", 7);
	Photo five = new Photo("five", "fifth photo", "-382-01-01", 3);
	Photo six = new Photo("six", "sixth photo", "2003-05-06", 4);
	
	// getPhotos test
	@Test
	public void testGetPhotosOne() {
		ArrayList<Photo> expected = new ArrayList<Photo>();
		
		test.addPhoto(one);
		test.addPhoto(two);
		test.addPhoto(three);
		
		expected.add(two);
		expected.add(three);
		
		assertEquals(expected, test.getPhotos(3));
	}
	
	@Test
	public void testGetPhotosTwo() {
		test.addPhoto(four);
		
		assertNull(test.getPhotos(7));
	}
	
	// getPhotosInMonth test
	@Test
	public void testGetPhotosInMonthOne() {
		ArrayList<Photo> expected = new ArrayList<Photo>();
		
		test.addPhoto(two);
		test.addPhoto(three);
		
		expected.add(two);
		expected.add(three);
		
		assertEquals(expected, test.getPhotosInMonth(3, 2001));
	}
	
	@Test
	public void testGetPhotosInMonthTwo() {
		ArrayList<Photo> expected = new ArrayList<Photo>();
		
		test.addPhoto(one);
		test.addPhoto(three);
		
		assertEquals(expected, test.getPhotosInMonth(4, 1998));
	}
	
	// getPhotosBetween test
	@Test
	public void testGetPhotosBetweenOne() {
		ArrayList<Photo> expected = new ArrayList<Photo>();
		
		expected.add(two);
		expected.add(three);
		
		test.addPhoto(one);
		test.addPhoto(two);
		test.addPhoto(three);
		
		ArrayList<Photo> actual = test.getPhotosBetween("1990-01-01", "2020-05-15");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetPhotosBetweenTwo() {
		assertNull(test.getPhotosBetween("1990-74-68", "200-5-43"));
	}
	
	// deletePhoto test
	@Test
	public void testDeletePhotoOne() {
		test.createAlbum("one");
		test.addPhoto(one);
		test.addPhotoToAlbum(one, "one");
		
		assertTrue(test.removePhoto(one));
	}
	
	@Test
	public void testDeletePhotoTwo()
	{
		assertFalse(test.removePhoto(one));
	}
	
	// similarity test
	@Test
	public void testSimilarityOne() {
		Library testTwo = new Library("Second", 002);
		testTwo.addPhoto(one);
		
		assertEquals(0.0, Library.similarity(test, testTwo), 0.1);
	}
	
	@Test
	public void testSimilarityTwo() {
		Library testTwo = new Library("Second", 002);
		
		test.addPhoto(one);
		test.addPhoto(two);
		test.addPhoto(three);
		
		testTwo.addPhoto(one);
		testTwo.addPhoto(two);
		
		double common = Library.commonPhotos(test, testTwo).size();
		double smallest = testTwo.getPhotos().size();
		double similarity = common / smallest;
		
		assertEquals(similarity, Library.similarity(test, testTwo), 0.1);
	}
	
	//removePhoto tests
	@Test
	public void testRemovePhotoOne() {
		test.addPhoto(one);
		test.addPhoto(two);
		test.addPhoto(three);
		test.addPhoto(four);
		test.addPhoto(five);
		
		test.createAlbum("aOne");
		
		test.addPhotoToAlbum(one, "aOne");
		test.addPhotoToAlbum(two, "aOne");
		
		assertTrue(test.removePhoto(one));
	}
	
	@Test
	public void testRemovePhotoTwo() {
		test.addPhoto(one);
		test.addPhoto(two);
		
		test.createAlbum("aOne");
		
		test.addPhotoToAlbum(one, "aOne");
		test.addPhotoToAlbum(two, "aOne");
		test.addPhotoToAlbum(three, "aOne");
		
		assertFalse(test.removePhoto(three));
	}
	
	// compareTo tests
	@Test
	public void testCompareToOne() {
		int actual = one.compareTo(four);
		assertTrue(actual < 0);
	}
	
	@Test
	public void testCompareToTwo() {
		test.addPhoto(one);
		test.addPhoto(two);
		test.addPhoto(three);
		
		Collections.sort(test.getPhotos());
		
		assertTrue(test.getPhotos().get(1).compareTo(test.getPhotos().get(2)) < 0);
	}
	
	// PhotoCaptionComparator tests
	@Test
	public void testPhotoCaptionComparatorOne() {
		ArrayList<Photo> test = new ArrayList<>();
		test.add(one);
		test.add(two);
		test.add(three);
		test.add(four);
		test.add(five);
		
		Collections.sort(test, new PhotoCaptionComparator());
		
		int actual = test.get(1).compareTo(test.get(3));
		assertTrue(actual < 0);
	}
	
	@Test
	public void testPhotoCaptionComparatorTwo() {
		ArrayList<Photo> test = new ArrayList<Photo>();
		test.add(one);
		test.add(two);
		test.add(six);
		
		Collections.sort(test, new PhotoCaptionComparator());
		
		int actual = test.get(1).compareTo(test.get(0));
		assertTrue(actual > 0);
	}
	
	// PhotoRatingComparator tests
	@Test
	public void testPhotoRatingComparatorOne() {
		ArrayList<Photo> test = new ArrayList<Photo>();
		test.add(two);
		test.add(three);
		test.add(five);
		
		Collections.sort(test, new PhotoRatingComparator());
		
		assertTrue(test.get(0).getRating() == 5);
	}
	
	@Test
	public void testPhotoRatingComparatorTwo() {
		ArrayList<Photo> test = new ArrayList<Photo>();
		test.add(one);
		test.add(two);
		test.add(six);

		Collections.sort(test, new PhotoRatingComparator());

		assertTrue(test.get(0).compareTo(test.get(1)) < 0);
	}
}
