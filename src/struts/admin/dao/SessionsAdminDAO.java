package struts.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import struts.admin.bean.AvailableSessionByRoom;
import struts.admin.bean.Movie;
import struts.admin.bean.Room;
import struts.admin.bean.Session;
import struts.admin.bean.SessionTime;
import struts.admin.bean.TicketTypeSetting;
import struts.dao.DAO;

public class SessionsAdminDAO extends DAO {

	public ArrayList<Movie> getMovieList(String current_date) {
		
		ArrayList<Movie> list = new ArrayList<Movie>();
		
		current_date = util.convertDate(current_date);
		
		String query = "select MaPhim, TenPhim, [3D], ThoiGianCongChieu, NgonNgu from Phim where TrangThai = '1' and ThoiGianCongChieu <= '"+current_date+"' order by ThoiGianCongChieu desc";
		
		System.out.println(query);
		
		try {
			ResultSet resultSet = connection.createStatement().executeQuery(query);
			while(resultSet.next()){
				Movie m = new Movie();
				m.setMaPhim(resultSet.getString(1));
				m.setTenPhim(resultSet.getString(2));
				//m.setType3d(resultSet.getInt(3));
				//m.setThoiGianCongChieu(util.convertDate(resultSet.getString(4)));
				//m.setNgonNgu(resultSet.getString(5));
				list.add(m);
			}
			
			resultSet.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
		
	}
	
	public Movie getMovieInfo(String idMovie) {
		Movie m = new Movie();
		try {
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM  Phim where MaPhim='"
							+ idMovie + "'");

			while (resultSet.next()) {
				m.setMaPhim(resultSet.getString(1));
				m.setTenPhim(resultSet.getString(2));
				m.setDaoDien(resultSet.getString(3));
				m.setDienVien(resultSet.getString(4));
				m.setThoiLuong(resultSet.getInt(5) + "");
				m.setNgonNgu(resultSet.getString(6));
				m.setTheLoai(resultSet.getString(7));
				m.setDoTuoiChoPhep(resultSet.getString(8));
				m.setNhaSX(resultSet.getString(9));
				m.setThoiGianCongChieu(resultSet.getString(10));
				m.setMoTa(resultSet.getString(11));
				m.setThumbnail(resultSet.getString(12));
				m.setBanner(resultSet.getString(13));
				m.setTrailer(resultSet.getString(14));
				m.setType3d(resultSet.getInt(15));
				m.setTrangThai(resultSet.getInt(16));
			}

			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return m;
	}

	public ArrayList<AvailableSessionByRoom> getSessionTimeAvailable(String current_date, String movieId) {
		
		ArrayList<Room> roomList = getRoomList();
		
		current_date = util.convertDate(current_date);

		ArrayList<AvailableSessionByRoom> list = new ArrayList<AvailableSessionByRoom>();
		
		Movie movie = getMovieInfo(movieId);
		movie.setThoiGianCongChieu(util.convertDate(movie.getThoiGianCongChieu()));
		
		int thoiLuong = Integer.parseInt(movie.getThoiLuong());
		
		if(thoiLuong%5 != 0){
			thoiLuong += 5 - (thoiLuong%5);
		}
		
		System.out.println("soPhong="+roomList.size());
		
		for(int i=0; i < roomList.size(); i++){
			
			AvailableSessionByRoom availableSessionByRoom = new AvailableSessionByRoom();
			
			String roomId = roomList.get(i).getMaPhong();
			
			System.out.println("maPhong:"+roomId);
			
			availableSessionByRoom.setRoom(roomList.get(i));
			availableSessionByRoom.setMovie_select(movie);
			
			
			ArrayList<SessionTime> sessionTimeScheduledList = getSessionTimeScheduledList(roomId, current_date);
			
			
			Date startTime = convertToDate(current_date+" 08:00");
			
			Date limitTime = convertToDate(current_date+" 23:59");
			
			int pointer=0;
			int size = sessionTimeScheduledList.size();
		
			
			while(isBefore(startTime,limitTime)){
				
				//System.out.println("start="+dateToString(startTime));
				//System.out.println("limit="+dateToString(limitTime));
				
				if(pointer == size){
					Date nextTime = getTime(startTime,(thoiLuong+15));
					if(isBefore(nextTime, limitTime)){
						
						SessionTime ns = new SessionTime();
						ns.setMaPhim(movieId);
						
						ns.setGioChieu(dateToHourString(startTime));
						ns.setFullTime(current_date+" "+dateToHourString(startTime));
						ns.setMaPhong(roomId);
						ns.setThoiLuong(thoiLuong);
						sessionTimeScheduledList.add(ns);
						
						//System.out.println("themVao, startTime1="+dateToString(startTime));
						
						startTime = getTime(startTime,5);
					}
					else{
						//System.out.println("vuot qua");
						break;
					}
				}
				
				if(pointer<size){
					
					Date nextTime = getTime(startTime,(thoiLuong+15));
					//System.out.println("nextTime="+dateToString(nextTime));
					
					Date desTime = convertToDate(sessionTimeScheduledList.get(pointer).getFullTime());
					//System.out.println("desTime="+dateToString(desTime));
					
					if(isBefore(nextTime, desTime)){

						SessionTime ns = new SessionTime();
						ns.setMaPhim(movieId);
						ns.setGioChieu(dateToHourString(startTime));
						ns.setMaPhong(roomId);
						ns.setThoiLuong(thoiLuong);
						ns.setFullTime(current_date+" "+dateToHourString(startTime));
						sessionTimeScheduledList.add(ns);
						//System.out.println("themVao, startTime2="+dateToString(startTime));
						
					}
					else 
					{
						
						startTime = getTime(desTime,sessionTimeScheduledList.get(pointer).getThoiLuong()+10);
//						System.out.println("thoi Luong + them ="+sessionTimeScheduledList.get(pointer).getThoiLuong()+10);
//						System.out.println("Vuot qua, new startTime="+dateToString(startTime));
						pointer++;
					}
					
					startTime = getTime(startTime,5);
				}	
			}
			
			Collections.sort(sessionTimeScheduledList, new Comparator<SessionTime>() {
				
		        @Override public int compare(SessionTime s1, SessionTime s2) {
		        			        	
		        	return hourToMinute(s1.getGioChieu()) - hourToMinute(s2.getGioChieu());
					
		        }

		    });

			availableSessionByRoom.setSessionTimeList(sessionTimeScheduledList);
			
			
			list.add(availableSessionByRoom);
			
		}
		
		return list;
	}
	
	private int hourToMinute(String hour){
		
		String[] arr = hour.split(":");
		return Integer.parseInt(arr[0])*60 + Integer.parseInt(arr[1]);
		
	}
	
	public String dateToHourString(Date date){
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		return dateFormat.format(date);
		
	}
	
	public String dateToString(Date date){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return dateFormat.format(date);
		
	}
	
	public Date getTime(Date current, int minutes)
	{		
		Date newDate = new Date(current.getTime() + TimeUnit.MINUTES.toMillis(minutes));
		//System.out.println("getTime="+dateToString(newDate));
		return newDate;
	}
	
	public boolean isBefore(Date finish, Date des)
	{
		if (finish.compareTo(des) <= 0 ) return true; else return false;
	}
	
	public Date convertToDate(String current){
		
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try
        {
             date = simpleDateFormat.parse(current);
        }
        catch (ParseException ex)
        {
            System.out.println("Exception "+ex);
        }
        return date;
	}
		
	public ArrayList<SessionTime> getSessionTimeScheduledList(String roomId, String current_date){
		
		System.out.println("getSessionTimeScheduledList");
		
		ArrayList<SessionTime> list = new ArrayList<SessionTime>();
		String query = "select LichChieu.MaSuat, Phim.MaPhim, Phim.TenPhim,PhongChieu.MaPhong, PhongChieu.TenPhong, Phim.ThoiLuong, CAST(LichChieu.TGBatDau as TIME) T, LichChieu.[3D] , LichChieu.TrangThai from LichChieu "
				+"join Phim on Phim.MaPhim = LichChieu.MaPhim "
				+"join PhongChieu on PhongChieu.MaPhong = LichChieu.MaPhong "
				+"where CAST(LichChieu.TGBatDau as DATE) = '"+current_date+"' and PhongChieu.MaPhong = '"+roomId+"' and LichChieu.TrangThai != '0'"
				+"order by LichChieu.TGBatDau";
		
		try 
		{
			ResultSet resultSet = connection.createStatement().executeQuery(query);
			while(resultSet.next()){
				
				SessionTime s = new SessionTime();
				s.setMaSuat(resultSet.getString(1));
				s.setMaPhim(resultSet.getString(2));
				s.setTenPhim(resultSet.getString(3));
				s.setThoiLuong(resultSet.getInt(6));
				String gioChieu = resultSet.getString(7).substring(0,5);
				s.setFullTime(current_date +" "+gioChieu);
				s.setType3d(resultSet.getBoolean(8));
				s.setTrangThai(resultSet.getInt(9));
				
				System.out.println("GioChieu="+gioChieu+" | MaPhong="+roomId +" | fullTime="+current_date +" "+gioChieu);
				s.setGioChieu(gioChieu);
				
				list.add(s);
			}
			
			resultSet.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public ArrayList<Room> getRoomList(){
		
		ArrayList<Room> list = new ArrayList<Room>();
		
		String query ="select PhongChieu.MaPhong, PhongChieu.TenPhong, PhongChieu.LoaiPhong from PhongChieu where TrangThai = '1'";
		
		try {
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()){
				Room r = new Room();
				r.setMaPhong(resultSet.getString(1));
				r.setTenPhong(resultSet.getString(2));
				r.setLoaiPhong(resultSet.getString(3));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<TicketTypeSetting> getTicketType(int type) {
		
		ArrayList<TicketTypeSetting>  list = new ArrayList<TicketTypeSetting>();
		
		String typeOfSession = null;
		
		if(type == 1) typeOfSession = "3D"; else typeOfSession = "2D";
		
		String query = "select * from LoaiVe where TrangThai = '1' and LoaiSuatApDung = '"+typeOfSession+"'";
		
		
		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			
			while(resultSet.next()){
				
				TicketTypeSetting t = new TicketTypeSetting();
				t.setMaLoaiVe(resultSet.getString(1));
				t.setTenLoaiVe(resultSet.getString(2));
				t.setLoai(resultSet.getString(3));
				t.setMoTa(resultSet.getString(4));
				t.setGiaTien(resultSet.getInt(8));
				
				list.add(t);
				
			}
			resultSet.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

	public boolean addSession(String session_str, String ticket_str) {
		
		session_str = session_str.trim();
		ticket_str = ticket_str.trim();
		
		ArrayList<String> sessionId_Array = insertToLichChieuTable(session_str);
		
		if(sessionId_Array != null){
			//add ticket type
			System.out.println("insertToLichChieuTable DONE");
			
			if(insertToCaiDatLoaiVeTable(ticket_str, sessionId_Array)){
				System.out.println("insertToCaiDatLoaiVeTable DONE");
				return true;
			} else {
				//rollback
				return false;
			}
		}
		else {
			//rollback
			
			return false;
		}
	}
	
	public boolean insertToCaiDatLoaiVeTable(String ticket_str, ArrayList<String> sessionId_Array){
		
		String[] array_ticket_info = ticket_str.split(" ");
		
		try {
			Statement insert_statemnet = connection.createStatement();
			
			for (int i = 0; i < sessionId_Array.size(); i++) {
				
				for(int j=0; j < array_ticket_info.length; j++){
					
					String[] ticket_current_data = array_ticket_info[j].split("-");
					
					String maSuat = sessionId_Array.get(i);
					String maLoaiVe = ticket_current_data[0];
					String giaTien = ticket_current_data[1];
		
					insert_statemnet.addBatch("insert into CaiDatLoaiVe "+ "values('"+(maSuat)+"', '"+maLoaiVe+"', '"+giaTien+"')");
					System.out.println("insert into CaiDatLoaiVe "+ "values('"+(maSuat)+"', '"+maLoaiVe+"', '"+giaTien+"')");
				}
				
			}
			
			insert_statemnet.executeBatch();
			
			insert_statemnet.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public ArrayList<String> insertToLichChieuTable(String session_str){
		//insert session
		
		System.out.println("session_str="+session_str);
		
		String[] array_session_info = session_str.split(" ");
		int sessionId = getNewSessionId()-1;
		
		ArrayList<String> sessionId_Array = new ArrayList<String>();
		
		try {
			Statement insert_statemnet = connection.createStatement();
			
			for (int i = 0; i < array_session_info.length; i++) {
				
				String[] session_current_data = array_session_info[i].split(",");
				
				for(int k=0;k<session_current_data.length;k++){
					System.out.println("--->"+session_current_data[k]);
				}
				
				String maPhim = session_current_data[0];
				String maPhong = session_current_data[1];
				String type = "false";
				if(session_current_data[5].equals("1")) type = "true";
				
				String start_time = util.convertDate(session_current_data[2])+" "+session_current_data[3]+":00.000";
				String end_time = getEndTime(util.convertDate(session_current_data[2])+" "+session_current_data[3]+":00.000", Integer.parseInt(session_current_data[4]));
				sessionId ++;
				
				//insert TrangThai = 2 wait for confirm
				insert_statemnet.addBatch("insert into LichChieu(MaSuat, MaPhong, MaPhim, [3D], TGBatDau, TGKetThuc, SLDaDat, TrangThai) "
						+ "values('S"+(sessionId)+"', '"+maPhong+"', '"+maPhim+"', '"+type+"','"+start_time+"', '"+end_time+"', '0', '2')");
				System.out.println("insert into LichChieu(MaSuat, MaPhong, MaPhim, [3D], TGBatDau, TGKetThuc, SLDaDat, TrangThai) "
						+ "values('S"+(sessionId)+"', '"+maPhong+"', '"+maPhim+"', '"+type+"','"+start_time+"', '"+end_time+"', '0', '2')");
				
				sessionId_Array.add("S"+sessionId);
			}
			
			insert_statemnet.executeBatch();
			
			insert_statemnet.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return sessionId_Array;
	}
	
	private String getEndTime(String start_time, int timeOfMovie){
		Date newDate = convertToDate(start_time);
		return dateToString(getTime(newDate, timeOfMovie));
	}
	
	private int getNewSessionId() {
		String lastId = null;
		try {
			ResultSet resultSet = statement.executeQuery("SELECT MaSuat FROM LichChieu order by MaSuat;");
			int id=0;
			while (resultSet.next()) {
				lastId = resultSet.getString(1);
				int temp = Integer.parseInt(lastId.substring(1,lastId.length()));
				if(temp > id) id=temp;
			}
			//
			if (lastId == null) return 1;
			id++;
			System.out.println("newID="+id);
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}

	}

	public JSONObject getDateSetting() {
		
		JSONObject jo = new JSONObject();
		
		String query = "select * from CaiDatNgay";
		
		
		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			while(resultSet.next()){
				jo.put("suatChieuSom", resultSet.getInt(1));
				jo.put("suatCuoiTuan", resultSet.getInt(2));
				jo.put("thu4VuiVe", resultSet.getInt(3));
				jo.put("ngayLe", resultSet.getString(4));
				jo.put("giaNgayLe", resultSet.getInt(5));
			}
			
			resultSet.close();
			st.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("jo="+jo);
		return jo;
	}

	public ArrayList<AvailableSessionByRoom> getSessionList(String current_date) {
		
		ArrayList<Room> roomList = getRoomList();
		current_date = util.convertDate(current_date);
		
		ArrayList<AvailableSessionByRoom> list = new ArrayList<AvailableSessionByRoom>();
		
		for(int i=0; i < roomList.size(); i++){
			
			AvailableSessionByRoom availableSessionByRoom = new AvailableSessionByRoom();
			
			String roomId = roomList.get(i).getMaPhong();
			
			availableSessionByRoom.setRoom(roomList.get(i));
			
			ArrayList<SessionTime> sessionTimeScheduledList = getSessionTimeScheduledList(roomId, current_date);
			
			availableSessionByRoom.setSessionTimeList(sessionTimeScheduledList);
			
			list.add(availableSessionByRoom);
			
		}
		return list;
	}

	public Session getSessionInfo(String sessionId) {
		
		String query = "select LichChieu.MaSuat, Phim.TenPhim, PhongChieu.TenPhong, LichChieu.TGBatDau, LichChieu.TGKetThuc, LichChieu.TrangThai from LichChieu "
				+"join Phim on Phim.MaPhim = LichChieu.MaPhim "
				+"join PhongChieu on PhongChieu.MaPhong = LichChieu.MaPhong "
				+"where LichChieu.MaSuat = '"+sessionId+"'";
		
		Session s = new Session();
		
		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			
			while(resultSet.next()){
				
				s.setMaSuat(resultSet.getString(1));
				Movie m = new Movie();
				m.setTenPhim(resultSet.getString(2));
				s.setMovieOfSession(m);
				Room r = new Room();
				r.setTenPhong(resultSet.getString(3));
				s.setRoomOfSession(r);
				
				String TGBatDau = resultSet.getString(4);
				String gioChieu = TGBatDau.substring(11, 16);
				String ngayChieu = TGBatDau.substring(0,10);
				String TGKetThuc = resultSet.getString(5);
				String gioKetThuc = TGKetThuc.substring(11, 16);
				
				s.setGioChieu(gioChieu);
				s.setNgayChieu(util.convertDate(ngayChieu));
				s.setGioKetThuc(gioKetThuc);
				s.setTrangThai(resultSet.getInt(6));
				
				//get TicketType Setting
				s.setTicketTypeSettingList(getTicketTypeOfSession(sessionId));
			}
			
			resultSet.close();
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return s;
	}

	private ArrayList<TicketTypeSetting> getTicketTypeOfSession(String sessionId) {
		
		String query = "select CaiDatLoaiVe.MaLoaiVe, LoaiVe.TenLoaiVe, CaiDatLoaiVe.GiaTien from CaiDatLoaiVe "
				+"join LoaiVe on LoaiVe.MaLoaiVe = CaiDatLoaiVe.MaLoaiVe "
				+" where MaSuat = '"+sessionId+"'";
		
		ArrayList<TicketTypeSetting> list = new ArrayList<TicketTypeSetting>();
		
		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			
				while(resultSet.next()){
					TicketTypeSetting t = new TicketTypeSetting();
					t.setMaLoaiVe(resultSet.getString(1));
					t.setTenLoaiVe(resultSet.getString(2));
					t.setGiaTien(resultSet.getInt(3));
					list.add(t);
				}
				
				resultSet.close();
				st.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}

	public boolean confirmSession(String sessionId) {
		
		String query = "update LichChieu SET TrangThai = '1' where MaSuat = '"+sessionId+"'";
		
		Statement st;
		try {
			st = connection.createStatement();
			st.executeUpdate(query);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public boolean deleteSession(String sessionId) {
		String query = "update LichChieu SET TrangThai = '0' where MaSuat = '"+sessionId+"'";
		
		Statement st;
		try {
			st = connection.createStatement();
			st.executeUpdate(query);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
