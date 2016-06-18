package struts.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class Validator {

	public static boolean validate(final String s) {
		String PATTERN = "^[P][C]\\d{2,}$";
		return Pattern.compile(PATTERN).matcher(s).matches();
	}

	public boolean isEmpty(String a) {
		if (a.trim().equals("")) {
			return true;
		} else
			return false;
	}

	public boolean isAfter(String a, String b) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			java.util.Date date1 = sdf.parse(a);
			java.util.Date date2 = sdf.parse(b);
			if (date1.before(date2) || date1.equals(date2)) {
				return true;
			} else
				return false;
		} catch (ParseException e) {
			return false;
		}
	}

	public boolean isEmail(final String s) {
		String PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		return Pattern.compile(PATTERN).matcher(s).matches();
	}
	
	public String chuyenDoi(String first, int i){
		//return (i<10)?(first+"0"+i):(first+i);
		return first+i;
	}
	
	

	public boolean isNumber(final String s) {
		String PATTERN = "^\\d*[.]?\\d*$";
		return Pattern.compile(PATTERN).matcher(s).matches();
	}

}