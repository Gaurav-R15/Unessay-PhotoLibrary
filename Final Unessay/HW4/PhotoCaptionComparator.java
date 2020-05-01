package HW4;
import java.util.Comparator;

public class PhotoCaptionComparator implements Comparator<Photo> {
	
	public int compare (Photo a, Photo b) {
		int retVal = a.getCaption().compareTo(b.getCaption());
		if (retVal == 0) {
			retVal = a.getRating() - b.getRating();
			return -retVal;
		}
		return retVal;
	}
}
