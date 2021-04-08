package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Date dateFromString(String s) {
		try {
			return DATE_FORMAT.parse(s);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String dateToString(Date d) {
		return DATE_FORMAT.format(d);
	}
}
