package struts.util;

import java.sql.Time;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import struts.bean.ScheduledDate;

//import struts.bean.Lich;

public class Utilities {
	final static String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";

	public boolean isThisDateValid(String dateToValidate, String dateFromat) {

		if (dateToValidate == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {

			// if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			System.out.println(date);

		} catch (ParseException e) {

			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean isThisDateHour(String time) {
		Pattern pattern = Pattern.compile(TIME24HOURS_PATTERN);
		Matcher matcher = pattern.matcher(time);
		return matcher.matches();
	}

	public String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public String getTimeNow() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));
		return dateFormat.format(cal.getTime());
	}

	public String getNextDate(String curDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(curDate));
		c.add(Calendar.DATE, 1);
		return sdf.format(c.getTime());
	}

	public String getWeekDay(String curDate) throws ParseException {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		c.setTime(sdf.parse(curDate));
		int day = c.get(Calendar.DAY_OF_WEEK);
		switch (day) {
		case 1:
			return "CN";
		case 2:
			return "T2";
		case 3:
			return "T3";
		case 4:
			return "T4";
		case 5:
			return "T5";
		case 6:
			return "T6";
		}
		return "T7";
	}



	public String convertDate(String date) {
		String[] arr = date.split("-");
		return arr[2] + "-" + arr[1] + "-" + arr[0];
	}

	public int tinhGiaVe(int giaGoc, int chietKhau, int loaiGhe) {
		if (loaiGhe == 2) {
			giaGoc += 20000;
		} else if (loaiGhe == 1) {
			giaGoc += 50000;
		}

		return (int) (giaGoc / 100) * (100 - chietKhau);
	}

	public int tinhTongTien(String[] seats, int giaGoc, int chietKhau) {
		int sum = 0;
		for (int i = 0; i < seats.length; i++) {
			if (getLoaiGhe(seats[i]) == 1) {
				System.out.println("Ghe:" + seats[i] + " loai "
						+ getLoaiGhe(seats[i]));
				sum += tinhGiaVe(giaGoc, chietKhau, 1);
			} else if (getLoaiGhe(seats[i]) == 2) {
				System.out.println("Ghe:" + seats[i] + " loai "
						+ getLoaiGhe(seats[i]));
				sum += tinhGiaVe(giaGoc, chietKhau, 2);
			} else {
				System.out.println("Ghe:"+seats[i]+" loai "+getLoaiGhe(seats[i]));
				sum += tinhGiaVe(giaGoc, chietKhau, 3);
			}
		}
		return sum;
	}

	public int getLoaiGhe(String seat) {
		String key = seat.substring(0,1);
		
		if(key.equals("A") || key.equals("B") || key.equals("C") || key.equals("D")) return 3;
		else if(key.equals("E") || key.equals("F") || key.equals("G") || key.equals("I")) return 2;
		else return 1;
		
	}
	//
	public static Date toEpochDate(Calendar calendar) {
        return new Date(Time.valueOf(new SimpleDateFormat("HH:mm:ss").format(calendar.getTime())).getTime());
    }
	//
	public boolean isDateBefore(String time)
	{
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date = new Date();
		try {
			Date time_movie = dateFormat.parse(time);
			return time_movie.before(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		
	}
	public String convertAMPM(String time){
		int hour = Integer.parseInt(time.substring(0,2));
		String min = time.substring(3,5);
		String AMPM =" AM";
		if(hour>12){
			AMPM = " PM";
			hour = hour - 12;
		}
		return hour+":"+min+AMPM;
	}
	


	/**
	 * @param date_arr mảng những ngày có lịch chiếu
	 * @return
	 */
	public ArrayList<ScheduledDate> getScheduledDates(ArrayList<String> date_arr) {
		
		ArrayList<ScheduledDate> scheduledDates = new ArrayList<ScheduledDate>();
		
		String date = getCurrentDate();
		try {
		for (int i = 0; i < 10; i++) {
			ScheduledDate d = new ScheduledDate();

			d.setNgayThang(date);

			if(date_arr.contains(date)) d.setCoPhim("1");
			else d.setCoPhim("0");

			d.setThu(getWeekDay(date));

			d.setNgay(date.substring(8));

			date = getNextDate(date);

			scheduledDates.add(d);
		}
	} catch (ParseException e) {
		e.printStackTrace();
	}
		return scheduledDates;	
	}
	
	
	//check exist in string array
	public boolean checkExistStr(String one, String two){
		
		String[] one_arr = one.split(" ");		
		String[] two_arr = two.split(" ");
		
		for(int i=0; i< one_arr.length; i++){			
			for(int j=0; j<two_arr.length; j++){
				if(one_arr[i].equals(two_arr[j])) return false;
			}
		}
		return true;
	}
	
	public String removeComma(String str){
		
		return str = str.replace(",", "");
		
	}
	
	//insertcomma
	public String insertComma(int number){
		return NumberFormat.getNumberInstance(Locale.US).format(number);
	}

}
