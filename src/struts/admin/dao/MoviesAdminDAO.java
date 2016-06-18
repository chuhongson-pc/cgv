package struts.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import struts.admin.bean.Movie;
import struts.dao.DAO;
import struts.util.Utilities;

public class MoviesAdminDAO extends DAO{
	
	Utilities util = new Utilities();

	public ArrayList<Movie> getMovieList(int type, int pagesize, int pagenumber) {

		ArrayList<Movie> movies = new ArrayList<Movie>();
		String current_date = util.getTimeNow();
		String query = "";
		ResultSet resultSet = null;

		if (type == 1) { // showing

			query = "select * from  Phim where TrangThai = '1' "
					+ " and MaPhim in (select MaPhim from LichChieu where LichChieu.TGBatDau >= '"
					+ current_date+"')";
			
			System.out.println("query now-showing:" + query);

		} else if (type == 2) { // coming soon

			query = "select * from  Phim where TrangThai = '1' and ThoiGianCongChieu >='"
					+ current_date
					+ "' and MaPhim not in (select MaPhim from LichChieu)";
			System.out.println("coming soon:" + query);

		}
		else query = "SELECT * FROM( SELECT Phim.*,  ROW_NUMBER() OVER(ORDER BY Phim.ThoiGianCongChieu desc) RN FROM Phim WHERE TrangThai = '1') a "
				+"WHERE RN BETWEEN "+((pagenumber-1)*pagesize+1)+" AND "+(pagenumber*pagesize)+" "
				+"ORDER BY a.ThoiGianCongChieu desc";
				
			System.out.println(query);
		try {
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Movie m = new Movie();
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
				movies.add(m);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return movies;

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
				
				String ngonNgu = resultSet.getString(6);
				if(ngonNgu.equals("voice")) m.setNgonNgu("Lồng tiếng");
				else m.setNgonNgu("Phụ đề");
				
				m.setTheLoai(resultSet.getString(7));
				m.setDoTuoiChoPhep(resultSet.getString(8));
				m.setNhaSX(resultSet.getString(9));
				m.setThoiGianCongChieu(util.convertDate(resultSet.getString(10)));
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


	public boolean updateMovie(Movie movie) {
		
		String query = "update Phim SET TenPhim = ?, DaoDien = ?, DienVien = ?, ThoiLuong = ?, NgonNgu = ?, TheLoai = ?, "
				+ "DoTuoiChoPhep =?, NhaSX = ?, ThoiGianCongChieu = ?, MoTa = ?, Thumbnail = ?, Banner = ?, Trailer = ?, [3D] = ?, TrangThai = ? where MaPhim = ?";
		
		int row = 0;
		try {
			
			PreparedStatement pre = connection.prepareStatement(query);
			
			pre.setString(1, movie.getTenPhim());
			//System.out.println(movie.getTenPhim());
			
			pre.setString(2, movie.getDaoDien());
			//System.out.println(movie.getDaoDien());
			
			pre.setString(3, movie.getDienVien());
			//System.out.println(movie.getDienVien());
			
			pre.setString(4, movie.getThoiLuong());
			//System.out.println(movie.getThoiLuong());
			
			pre.setString(5, movie.getNgonNgu());
			//System.out.println(movie.getNgonNgu());
			
			pre.setString(6, movie.getTheLoai());
			pre.setString(7, movie.getDoTuoiChoPhep());
			pre.setString(8, movie.getNhaSX());
			pre.setString(9, util.convertDate(movie.getThoiGianCongChieu()));
			pre.setString(10, movie.getMoTa());
			pre.setString(11, movie.getThumbnail());
			pre.setString(12, movie.getBanner());
			pre.setString(13, movie.getTrailer());
			pre.setInt(14, movie.getType3d());
			pre.setInt(15, 1); //status
			pre.setString(16, movie.getMaPhim());

			row = pre.executeUpdate();
			
			
			pre.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		if(row != 0) return true; else return false;
		
	}


	public ArrayList<Movie> searchMovie(String content) {
		ArrayList<Movie> list = new ArrayList<Movie>();
		try {
			
//			String query = "SELECT * FROM( SELECT Phim.*,  ROW_NUMBER() OVER(ORDER BY Phim.ThoiGianCongChieu) RN FROM Phim where TenPhim LIKE '%"+ content + "%' and TrangThai = '1' ) a "
//					+"WHERE RN BETWEEN "+((pagenumber-1)*pagesize+1)+" AND "+(pagenumber*pagesize)+" "
//					+"ORDER BY a.ThoiGianCongChieu";
			String query = "SELECT Phim.*FROM Phim where TenPhim LIKE '%"+ content + "%' and TrangThai = '1'";
			
			ResultSet resultSet = statement.executeQuery(query);
			System.out.println(query);
			
			
			while (resultSet.next()) {
				Movie m = new Movie();
				m.setMaPhim(resultSet.getString(1));
				m.setTenPhim(resultSet.getString(2));
				m.setDaoDien(resultSet.getString(3));
				m.setDienVien(resultSet.getString(4));
				m.setThoiLuong(resultSet.getInt(5) + "");
				m.setNgonNgu(resultSet.getString(6));
				m.setTheLoai(resultSet.getString(7));
				m.setDoTuoiChoPhep(resultSet.getString(8));
				m.setNhaSX(resultSet.getString(9));
				m.setThoiGianCongChieu(util.convertDate(resultSet.getString(10)));
				m.setMoTa(resultSet.getString(11));
				m.setThumbnail(resultSet.getString(12));
				m.setBanner(resultSet.getString(13));
				m.setTrailer(resultSet.getString(14));
				m.setType3d(resultSet.getInt(15));
				m.setTrangThai(resultSet.getInt(16));
				list.add(m);
			}

			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}


	public int getNumberRowOfMovie() {
		int numberOfRow = 0;
		try {
			ResultSet resultSet = statement.executeQuery("SELECT COUNT(MaPhim) FROM Phim where TrangThai='1';");
			while(resultSet.next()){
				numberOfRow = Integer.parseInt(resultSet.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		return numberOfRow;
	}


	public String addMovie(Movie movie) {
		
		String query = "insert Phim VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String newId = getNewMovieId();
		try {
			
			PreparedStatement pre = connection.prepareStatement(query);
			
			pre.setString(1, newId);
			
			pre.setString(2, movie.getTenPhim());
			//System.out.println(movie.getTenPhim());
			
			pre.setString(3, movie.getDaoDien());
			//System.out.println(movie.getDaoDien());
			
			pre.setString(4, movie.getDienVien());
			//System.out.println(movie.getDienVien());
			
			pre.setString(5, movie.getThoiLuong());
			//System.out.println(movie.getThoiLuong());
			
			pre.setString(6, movie.getNgonNgu());
			//System.out.println(movie.getNgonNgu());
			
			pre.setString(7, movie.getTheLoai());
			pre.setString(8, movie.getDoTuoiChoPhep());
			pre.setString(9, movie.getNhaSX());
			pre.setString(10, util.convertDate(movie.getThoiGianCongChieu()));
			pre.setString(11, movie.getMoTa());
			pre.setString(12, movie.getThumbnail());
			pre.setString(13, movie.getBanner());
			pre.setString(14, movie.getTrailer());
			pre.setInt(15, movie.getType3d());
			pre.setInt(16, 1);
			
			pre.executeUpdate();
			
			pre.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return newId;
	}
	
	public String getNewMovieId() {
		String idDo = null;
		try {
			ResultSet resultSet = statement.executeQuery("SELECT MaPhim FROM Phim order by MaPhim;");
			int id=0;
			while (resultSet.next()) {
				idDo = resultSet.getString(1);
				int temp = Integer.parseInt(idDo.substring(1,idDo.length()));
				if(temp > id) id=temp;
			}
			//
			if (idDo == null) return "P1";
			id++;
			System.out.println("newID="+id);
			return "P" + id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	public boolean deleteMovie(String movieId) {
		String query = "update Phim SET TrangThai = '0' where MaPhim = '"+movieId+"'";
		
		int result = 0;
		try {
			Statement st = connection.createStatement();
			result = st.executeUpdate(query);
			
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		if(result == 0) return false;
		else {
			if(deleteSession(movieId)) return true;
			else return false;
		}
		
		
	}
	public boolean deleteSession(String movieId){
		
		String query = "update LichChieu set TrangThai = '0' where MaSuat = ?";
		ArrayList<String> list = getListSessionToDelete(movieId);
		try {
			PreparedStatement prsm = connection.prepareStatement(query);
			for(int i=0; i< list.size(); i++){
				prsm.setString(1, list.get(i));
				System.out.println("delete Session="+list.get(i));
				prsm.execute();
			}
			prsm.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	public ArrayList<String> getListSessionToDelete(String movieId){
		ArrayList<String> list = new ArrayList<String>();
		String query = "select MaSuat from LichChieu "
				+ "where LichChieu.MaPhim = '"+movieId+"' and (LichChieu.TrangThai = '1' or LichChieu.TrangThai ='2')";
		
		
		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			while(resultSet.next()){
				list.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
		
	}
	
}
