package HW4;
/**
 * Homework 2A
 * Gaurav Ravichandran, gtr5ew
 *
 * Sources: Lecture Slides, Big Java Book
 */
public class DateLibrary {

	public static void main(String[] args) {
		// Test Variables
		String Date1 = "2020-02-13";
		String Date2 = "2007-02-14";
		String Date3 = "0a00-14-56";
		
		// .isValidDateFormat() test
		System.out.println(DateLibrary.isValidDateFormat(Date1)); // Should return true
		System.out.println(DateLibrary.isValidDateFormat(Date3)); // Should return false
		
		// .getYear() test
		System.out.println(DateLibrary.getYear(Date1)); // Should return 2020
		System.out.println(DateLibrary.getYear(Date3)); // Should return -1
		
		// .getMonth() test
		System.out.println(DateLibrary.getMonth(Date1)); // Should return 2
		System.out.println(DateLibrary.getMonth(Date3)); // Should return -1
		
		// .getDay() test
		System.out.println(DateLibrary.getDay(Date1)); // Should return 13
		System.out.println(DateLibrary.getDay(Date3)); // Should return -1
		
		// .isLeapYear() test
		System.out.println(DateLibrary.isLeapYear(DateLibrary.getYear(Date1))); // Should return true
		System.out.println(DateLibrary.isLeapYear(DateLibrary.getYear(Date2))); // Should return false
		
		// .isValidDate() test
		System.out.println(DateLibrary.isValidDate(Date1)); // Should return true
		System.out.println(DateLibrary.isValidDate(Date3)); // Should return false
		
		// .compare() test
		System.out.println(DateLibrary.compare(Date1, Date2)); // Should return a positive number
		System.out.println(DateLibrary.compare(Date2, Date3)); // Should return 0 (invalid date)
		System.out.println(DateLibrary.compare(Date2, Date1)); // Should return a negative number
	}

	/**
	 * Takes a string representation of a date and returns whether the date is in a
	 * valid format (10 chars long, two hyphens in specific indexes; year, month, and
	 * day are digits)
	 * @param date - String representation of a date (YYYY-MM-DD)
	 * @return true or false based on whether the argument is properly formatted
	 */
	public static boolean isValidDateFormat(String date) {
		if (date.length() > 10 || date.length() < 10) {
			return false;
		}
		else if (!date.substring(4, 5).equals("-") || !date.substring(7, 8).equals("-")) {
			return false;
		}
		String digits = date.replace("-", "");
		for (int i = 0; i < digits.length(); i++) {
			if (digits.charAt(i) < 48 || digits.charAt(i) > 57) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns the year given a string representation of a date, if format or year
	 * are invalid, -1 is returned
	 * @param date - String representation of a date (YYYY-MM-DD)
	 * @return -1 or int year, depending on whether the format and year number are valid
	 */
	public static int getYear(String date) {
		if (DateLibrary.isValidDateFormat(date) == false)  {
			return -1;
		}
		int year = Integer.parseInt(date.substring(0, 4));
		if ( (year < 1) || (year > 9999) ) {
			return -1;
		}
		else {
			return year;
		}
	}
	
	/**
	 * Returns the month given a string representation of a date, if format or month
	 * are invalid, -1 is returned
	 * @param date - String representation of a date (YYYY-MM-DD)
	 * @return -1 or int month, depending on whether the format and month are valid
	 */
	public static int getMonth(String date) {
		if (DateLibrary.isValidDateFormat(date) == false) {
			return -1;
		}
		int month = Integer.parseInt(date.substring(5, 7));
		if (month < 1 || month > 12) {
			return -1;
		}
		return month;
	}
	
	/**
	 * Returns the day given a string representation of a date, if format or day are
	 * invalid, -1 is returned
	 * @param date - String representation of a date (YYYY-MM-DD)
	 * @return -1 or int day, depending on whether the format and day number are valid
	 */
	public static int getDay(String date) {
		if (!DateLibrary.isValidDateFormat(date)) {
			return -1;
		}
		int day = Integer.parseInt(date.substring(8));
		if (day < 1 || day > 31) {
			return -1;
		}
		return day;
	}
	
	/**
	 * Returns true or false based on whether the given year is a leap year (is 
	 * divisible by just 4 or 4, 100, and 400). If divisible by 4 and 100, the year is
	 * not a leap year
	 * @param year - a year given in integer form
	 * @return - true or false based on whether the year meets the given conditions
	 * to be a leap year
	 */
	public static boolean isLeapYear(int year) {
		if ((year % 4 == 0) && (year % 100 == 0) && (year % 400 == 0)) {
			return true;
		}
		else if (year % 4 == 0 && year % 100 == 0) {
			return false;
		}
		else if (year % 4 == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Returns true if the date is valid (format is valid, year is valid, 
	 * month is valid, day is valid, day is valid given the month, 
	 * day is valid given leap year)
	 * @param date - String representation of a date (YYYY-MM-DD)
	 * @return true or false based on whether the given date is valid
	 */
	public static boolean isValidDate(String date) {
		if (!DateLibrary.isValidDateFormat(date)) {
			return false;
		}
		else if (DateLibrary.getYear(date) == -1) {
			return false;
		}
		else if (DateLibrary.getMonth(date) == -1) {
			return false;
		}
		else if (DateLibrary.getDay(date) == -1) {
			return false;
		}
		
		if ((DateLibrary.getMonth(date) == 2) && (DateLibrary.isLeapYear(DateLibrary.getYear(date))) && (DateLibrary.getDay(date) > 29)) {
			return false;
		}
		else if ((DateLibrary.getMonth(date) == 2) && (DateLibrary.getDay(date) > 28) && (DateLibrary.isLeapYear(DateLibrary.getYear(date)) == false)) {
			return false;
		}
		
		if (DateLibrary.getMonth(date) == 4 || DateLibrary.getMonth(date) == 6 || DateLibrary.getMonth(date) == 9 || DateLibrary.getMonth(date) == 11) {
			if (DateLibrary.getDay(date) > 30) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns a negative number if date1 comes before date2, a positive number if
	 * date1 comes after date2, and 0 if either of the dates are valid or if they are
	 * equivalent.
	 * @param date1 - A String representation of a date (YYYY-MM-DD)
	 * @param date2 - A String representation of a date (YYYY-MM-DD)
	 * @return a positive number, negative number, or zero, comparing on the conditions
	 * that the dates meet
	 */
	public static int compare (String date1, String date2) {
		if (!DateLibrary.isValidDate(date1) || !DateLibrary.isValidDate(date2)) {
			return 0;
		}
		return date1.compareTo(date2);
	}
}
