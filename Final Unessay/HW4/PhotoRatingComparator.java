package HW4;
import java.util.Comparator;

public class PhotoRatingComparator implements Comparator<Photo> {
	
	public int compare(Photo a, Photo b) {
		int retVal = a.getRating() - b.getRating();
		if (retVal == 0) {
			retVal = a.getCaption().compareTo(b.getCaption());
			return retVal;
		}
		return -retVal;
	}

}
