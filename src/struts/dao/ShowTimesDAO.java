package struts.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import struts.bean.Movie;
import struts.bean.Room;
import struts.bean.Session;
import struts.bean.ShowTimesRow;

public class ShowTimesDAO extends DAO {

	public ArrayList<String> getAllScheduledDates() {

		ArrayList<String> arr_dates = new ArrayList<String>();

		String current_date = util.getCurrentDate();
		try {
			String query = "select CAST(LichChieu.TGBatDau AS DATE) from LichChieu "
					+ "inner join Phim on Phim.MaPhim = LichChieu.MaPhim "
					+ "where Phim.TrangThai = '1' and LichChieu.TrangThai = '1' "
					+ " and CAST(LichChieu.TGBatDau AS DATE) >= '"
					+ current_date + "';";

			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {

				arr_dates.add(resultSet.getString(1));
			}
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr_dates;
	}

	public ArrayList<ShowTimesRow> getShowTimes(String current_date) {
		
		ArrayList<ShowTimesRow> showtimes = new ArrayList<ShowTimesRow>();

		String query = "select distinct Phim.MaPhim, Phim.TenPhim, Phim.TheLoai, Phim.ThoiLuong , PhongChieu.MaPhong, PhongChieu.TenPhong "
				+ "from LichChieu join Phim on Phim.MaPhim = LichChieu.MaPhim join PhongChieu on PhongChieu.MaPhong = LichChieu.MaPhong "
				+ "where LichChieu.TrangThai = '1' and PhongChieu.TrangThai = '1' and Phim.TrangThai = '1' "
				+ "and CAST(LichChieu.TGBatDau AS DATE) = '"+current_date+"' order by Phim.MaPhim desc";


		System.out.println("Get list of Showtimes:" + query);

		try {
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				ShowTimesRow showtimesRow = new ShowTimesRow();
				String maPhim = resultSet.getString(1);
				String tenPhim = resultSet.getString(2);
				String theLoai = resultSet.getString(3);
				String thoiLuong = resultSet.getInt(4)+"";
				String maPhong = resultSet.getString(5);
				String tenPhong = resultSet.getString(6);

				Movie movie = new Movie();
				movie.setTenPhim(tenPhim);
				movie.setMaPhim(maPhim);
				movie.setTheLoai(theLoai);
				movie.setThoiLuong(thoiLuong);

				Room room = new Room();
				room.setMaPhong(maPhong);
				room.setTenPhong(tenPhong);

				showtimesRow.setMovieOfRow(movie);
				showtimesRow.setRoomOfRow(room);

				getSessionOfRow(showtimesRow, maPhim, maPhong, current_date);

				showtimes.add(showtimesRow);
			}

			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return showtimes;

	}

	private void getSessionOfRow(ShowTimesRow row, String maPhim, String maPhong, String date_current) {

		String query = "select LichChieu.MaSuat, CAST(LichChieu.TGBatDau AS TIME) AS SUAT , LichChieu.SLDaDat, PhongChieu.SoGhe, LichChieu.[3D] "
				+ "from LichChieu inner join PhongChieu on PhongChieu.MaPhong = LichChieu.MaPhong "
				+"join Phim on Phim.MaPhim = LichChieu.MaPhim "
				+ "where LichChieu.MaPhim = '" + maPhim+ "' "
				+ "and CAST(LichChieu.TGBatDau AS DATE) = '"+ date_current+ "' and LichChieu.MaPhong = '"+ maPhong + "' order by SUAT";

		System.out.println("query Session array:" + query);

		ArrayList<Session> sessionList = new ArrayList<Session>();

		try {
			
			ResultSet getInfoResultSet = connection.createStatement().executeQuery(query);
			
			while (getInfoResultSet.next()) {

				Session session = new Session();

				String maSuat = getInfoResultSet.getString(1);

				String gioChieu = getInfoResultSet.getString(2).substring(0, 5);

				int soGheDaDat = getInfoResultSet.getInt(3);

				int soGhe = getInfoResultSet.getInt(4);

				boolean type3d = getInfoResultSet.getBoolean(5);

				if (type3d)
					session.setType3d(true);
				else
					session.setType3d(false);

				if (soGheDaDat == soGhe)
					session.setStatusFull(true);
				else
					session.setStatusFull(false);

				session.setMaSuat(maSuat);
				session.setSLDaDat(soGheDaDat);
				session.setGioChieu(gioChieu);

				sessionList.add(session);

			}
			getInfoResultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		row.setSessionList(sessionList);
	}

}
