package struts.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import struts.bean.Movie;
import struts.bean.Room;
import struts.bean.Session;
import struts.bean.ShowTimesRow;
import struts.util.Utilities;

public class MovieDAO extends DAO {

	static Utilities util = new Utilities();

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

	public ArrayList<String> getScheduledDatesByMovie(String idMovie) {
		ArrayList<String> arr_dates = new ArrayList<String>();
		String current_date = util.getCurrentDate();
		try {
			String query = "select CAST(LichChieu.TGBatDau AS DATE) from LichChieu "
					+ "inner join Phim on Phim.MaPhim = LichChieu.MaPhim "
					+ "where Phim.TrangThai = '1' and LichChieu.TrangThai = '1' and Phim.MaPhim = '"
					+ idMovie
					+ "' and CAST(LichChieu.TGBatDau AS DATE) >= '"
					+ current_date + "';";

			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {

				arr_dates.add(resultSet.getString(1));
			}
			resultSet.close();

		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return arr_dates;
	}

	public ArrayList<ShowTimesRow> getShowtimes(String idMovie,
			String date_current) {

		ArrayList<ShowTimesRow> showtimes = new ArrayList<ShowTimesRow>();

		String query = "select distinct Phim.MaPhim, Phim.TenPhim, Phim.TheLoai, PhongChieu.MaPhong, PhongChieu.TenPhong "
				+"from LichChieu join Phim on Phim.MaPhim = LichChieu.MaPhim "
				+"join PhongChieu on LichChieu.MaPhong = PhongChieu.MaPhong "
				+"where LichChieu.TrangThai = '1' and PhongChieu.TrangThai = '1' and Phim.TrangThai = '1' "
				+"and CAST(LichChieu.TGBatDau AS DATE) = '"+date_current+"' and Phim.MaPhim = '"+idMovie+"';";

		System.out.println("Get list movies/room of Showtime:" + query);

		try {
			ResultSet resultSet = connection.createStatement().executeQuery(query);

			while (resultSet.next()) 
			{
				ShowTimesRow showtimesRow = new ShowTimesRow();
				String maPhim = resultSet.getString(1);
				String tenPhim = resultSet.getString(2);
				String theLoai = resultSet.getString(3);
				String maPhong = resultSet.getString(4);
				String tenPhong = resultSet.getString(5);
				
				Movie movie = new Movie();
				movie.setTenPhim(tenPhim);
				movie.setMaPhim(maPhim);
				
				Room room = new Room();
				room.setMaPhong(maPhong);
				room.setTenPhong(tenPhong);
				
				showtimesRow.setMovieOfRow(movie);
				showtimesRow.setRoomOfRow(room);
				
				getMoreInfoMovie(showtimesRow, idMovie, maPhong, date_current);
				
				showtimes.add(showtimesRow);
			}
			
			resultSet.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return showtimes;

	}
	
	private void getMoreInfoMovie(ShowTimesRow row, String idMovie, String maPhong, String date_current) {
		
		String query = "select LichChieu.MaSuat, CAST(LichChieu.TGBatDau AS TIME) AS SUAT , LichChieu.SLDaDat, PhongChieu.SoGhe, LichChieu.[3D] "
				+"from LichChieu inner join PhongChieu on PhongChieu.MaPhong = LichChieu.MaPhong "
				+"inner join Phim on Phim.MaPhim = LichChieu.MaPhim "
				+"where LichChieu.MaPhim = '"+idMovie+"' and PhongChieu.TrangThai = '1' and Phim.TrangThai = '1' and LichChieu.TrangThai = '1' "
				+"and CAST(LichChieu.TGBatDau AS DATE) = '"+date_current+"' and LichChieu.MaPhong = '"+maPhong+"' "
				+"order by SUAT";
		
		System.out.println("query Session array:"+query);
		
		ArrayList<Session> sessionList = new ArrayList<Session>();
		
		try {
			ResultSet getInfoResultSet = connection.createStatement().executeQuery(query);
			while(getInfoResultSet.next()){
				
				Session session = new Session();
				
				String maSuat = getInfoResultSet.getString(1);
				
				String gioChieu = getInfoResultSet.getString(2).substring(0,5);

				int soGheDaDat = getInfoResultSet.getInt(3);
				
				int soGhe = getInfoResultSet.getInt(4);
				
				boolean type3d = getInfoResultSet.getBoolean(5);
				
				if(type3d) session.setType3d(true);
				else session.setType3d(false);
				
				if(soGheDaDat == soGhe) session.setStatusFull(true);
				else session.setStatusFull(false);

				session.setMaSuat(maSuat);
				session.setSLDaDat(soGheDaDat);
				session.setGioChieu(gioChieu);
				
				sessionList.add(session);
							
			}
			getInfoResultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		row.setSessionList(sessionList);
	}

}
