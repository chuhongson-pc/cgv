package struts.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import struts.bean.Customer;
import struts.bean.Movie;
import struts.bean.Poster;

public class HomeDAO extends DAO {

	public ArrayList<Movie> getMovies(int type) {

		ArrayList<Movie> movies = new ArrayList<Movie>();
		String current_date = util.getTimeNow();
		String query = "";
		ResultSet resultSet = null;

		if (type == 1) { // showing

			query = "select * from  Phim where TrangThai = '1' "
					+ " and MaPhim in (select MaPhim from LichChieu where LichChieu.TGBatDau >= '"
					+ current_date + "');";
			System.out.println("query now-showing:" + query);

		} else if (type == 2) { // coming soon

			query = "select * from  Phim where TrangThai = '1' and ThoiGianCongChieu >='"
					+ current_date
					+ "' and MaPhim not in (select MaPhim from LichChieu);";
			System.out.println("coming soon:" + query);

		}

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

	public boolean checkLogin(String username, String password) {
		ResultSet resultSet = null;

		try {
			resultSet = statement
					.executeQuery("SELECT * FROM  TaiKhoan WHERE Username='"
							+ username + "' and Password='" + password + "' and TrangThai = '1' and Role ='1'");
			if (resultSet.next())
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}
	}

	public boolean checkExistUser(String username) {
		ResultSet resultSet = null;
		try {
			resultSet = statement
					.executeQuery("SELECT Username FROM  TaiKhoan WHERE Username='"
							+ username + "'");

			if (resultSet.next()) { // neu co

				resultSet.close();

				return true;
			} else {
				resultSet.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean addNewUser(String fullname, String dob, String gender,
			String address, String phonenumber, String email, String cmnd,
			String username, String password) {
		
		if(addTaiKhoan(fullname, dob, gender,
			address, phonenumber, email, cmnd,
			username, password)){
			
			if(insert_TTKhachHang(username)){
				return true;
			} else {
				return false;
			}
			
		}
		else {
			return false;
		}
	}

	public boolean addTaiKhoan(String fullname, String dob, String gender,
			String address, String phonenumber, String email, String cmnd,
			String username, String password) {
		
		System.out.println("insert to KhachHang");

		
		String query_insert = "INSERT INTO TaiKhoan(Username, HoTen, NgaySinh, GioiTinh, DiaChi, SoDienThoai, Email, CMND, Password, NgayKhoiTao, Role, TrangThai) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstatement = connection.prepareStatement(query_insert);
			pstatement.setString(1, username);
			pstatement.setString(2, fullname);
			pstatement.setString(3, dob);
			pstatement.setString(4, gender);

			pstatement.setString(5, address);
			pstatement.setString(6, phonenumber);
			pstatement.setString(7, email);
			pstatement.setString(8, cmnd);
			pstatement.setString(9, password);
			pstatement.setString(10, util.getTimeNow());
			pstatement.setInt(11,1);
			pstatement.setInt(12, 1);

			pstatement.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean insert_TTKhachHang(String username){
		
		String query = "insert into TTKhachHang VALUES(?, ?, ?)";
		
		try {
			PreparedStatement pstatement = connection.prepareStatement(query);
			
			pstatement.setString(1, username);
			pstatement.setInt(2,0);
			pstatement.setInt(3, 0);
			
			pstatement.executeUpdate();
			
			pstatement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	

	public ArrayList<Movie> getTopMovies() {
		
		String now_date = util.getCurrentDate();
		
		String query = "select top 5 Phim.MaPhim, Phim.TenPhim, Phim.TheLoai, Phim.ThoiLuong, Phim.Thumbnail, SUM(LoaiVeGiaoDich.SL) AS SL "
				+"from Phim join LichChieu on LichChieu.MaPhim = Phim.MaPhim "
				+"join GiaoDich on GiaoDich.MaSuat = LichChieu.MaSuat " 
				+"join LoaiVeGiaoDich on LoaiVeGiaoDich.MaGD = GiaoDich.MaGD "
				+"where LichChieu.TGBatDau >= '"+now_date+"' "
				+"group by Phim.MaPhim, Phim.TenPhim, Phim.TheLoai, Phim.ThoiLuong, Phim.Thumbnail";
		
		System.out.println("query top 5:"+ query);
		
		ArrayList<Movie> topMovies = new ArrayList<Movie>();
		
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				Movie p = new Movie();
				
				String maPhim = resultSet.getString(1);
				String tenPhim = resultSet.getString(2);
				String theLoai = resultSet.getString(3);
				String thoiLuong = resultSet.getInt(4)+"";
				String thumbnail = resultSet.getString(5);

				p.setMaPhim(maPhim);
				p.setTenPhim(tenPhim);
				p.setTheLoai(theLoai);
				p.setThoiLuong(thoiLuong);
				p.setThumbnail(thumbnail);
				
				topMovies.add(p);

			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return topMovies;
	}

	public boolean updatePassword(String username, String password) {
		System.out.println("Update to KhachHang");
		
		String query_update = "UPDATE TaiKhoan SET Password=? WHERE Username=?";
		try {
			pstatement = connection.prepareStatement(query_update);
			pstatement.setString(1, password);
			pstatement.setString(2, username);
			//
			pstatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Customer getUserInfo(String username) {
		System.out.println("get info KhachHang");
		Customer khachHang = new Customer();
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM  TaiKhoan join TTKhachHang on TTKhachHang.Username = TaiKhoan.Username"
					+ " WHERE TaiKhoan.Username='"+ username + "' and TrangThai = '1'");

			while (resultSet.next()) 
			{ 
				khachHang.setUsername(resultSet.getString(1));
				khachHang.setFullname(resultSet.getString(2));
				khachHang.setDob(resultSet.getString(3));
				khachHang.setGender(resultSet.getString(4));
				
				khachHang.setAddress(resultSet.getString(5));
				khachHang.setPhonenumber(resultSet.getString(6));
				khachHang.setEmail(resultSet.getString(7));
				
				khachHang.setCmnd(resultSet.getString(8));
				

				khachHang.setPassword(resultSet.getString(9));
				
				khachHang.setSoDuTaiKhoan(util.insertComma(resultSet.getInt(14)));
				khachHang.setDiemTichLuy(resultSet.getInt(15)+"");
				
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return khachHang;
	}

	public boolean updateProfile(String username, String fullname, String cmnd,
			String dob, String gender, String email, String phonenumber,
			String address) {
		// TODO Auto-generated method stub
		System.out.println("update to KhachHang");
		
		String query = "update TaiKhoan SET  HoTen = ?, NgaySinh = ?, GioiTinh = ?, DiaChi =?, "
				+ "SoDienThoai = ?, Email = ?, CMND = ? where Username = ?";
		

		try {
			PreparedStatement pr = connection.prepareStatement(query);
			
			pr.setString(1, fullname);
			pr.setString(2, dob);
			pr.setString(3, gender);
			pr.setString(4, address);
			pr.setString(5, phonenumber);
			pr.setString(6, email);
			pr.setString(7, cmnd);
			pr.setString(8, username);
			
			pr.executeUpdate();
			
			pr.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public JSONObject getOrderList(String username) {
		
		String query = "select GiaoDich.MaGD, Phim.TenPhim, "
				+"CAST(LichChieu.TGBatDau as DATE) N  , CAST(LichChieu.TGBatDau as TIME) G, "
				+"PhongChieu.TenPhong, GiaoDich.ThoiGianGD, "
				+"GiaoDich.TongTien "
				+"from GiaoDich join LichChieu on GiaoDich.MaSuat = LichChieu.MaSuat "
				+"join PhongChieu on PhongChieu.MaPhong = LichChieu.MaPhong "
				+"join Phim on Phim.MaPhim = LichChieu.MaPhim  "
				+"where  GiaoDich.Username = '"+username+"' and GiaoDich.TrangThai ='1' order by GiaoDich.ThoiGianGD desc";
		
		
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		 
		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			
			while(resultSet.next()){
				JSONArray ja = new JSONArray();
				
				ja.put(resultSet.getString(1)); //maGD
				
				String gioChieu = resultSet.getString(4);
				gioChieu = gioChieu.substring(0,5);
				
				String phim_str = "<div class=\"ticket-name\" data-id=\""+resultSet.getString(1)+"\">"+resultSet.getString(2)+"</div>"
								+"<div class=\"description-ticket\" data-id=\""+resultSet.getString(1)+"\">"+util.convertDate(resultSet.getString(3)) +" | "+gioChieu+" | "+resultSet.getString(5)+"</div>";
				
				ja.put(phim_str); //thongTinSuat
				
				
				//put danh sach ghe
				
				ja.put(getSeatsBooked(resultSet.getString(1)));
				
				//put danh sach fastfood
				ja.put(getFastFoodBooked(resultSet.getString(1)));
				
				String ngayGD = resultSet.getString(6);
				ngayGD = util.convertDate(ngayGD.substring(0,10));
				
				String gioGD = resultSet.getString(6);
				gioGD = gioGD.substring(11,19);
				
				ja.put(ngayGD + " " + gioGD);
				
				
				//kiem tra da chieu hay chua 
				String trangThai = "";
				String sessionTime = resultSet.getString(3) +" "+resultSet.getString(4);
				
				Date dateNow = new Date();
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date dateSession = null;
				try {
					dateSession = dateFormat.parse(sessionTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(dateNow.before(dateSession)) trangThai = "Chưa chiếu"; else trangThai = "Đã chiếu";
				
				ja.put(trangThai);
				
				array.put(ja);
				
				
			}
			
			resultSet.close();
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			result.put("aaData", array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(result);
		return result;
		
	}

	private String getFastFoodBooked(String maGD) {
		
		String query = "select FastFood.TenFF, ChiTietFF.SL from ChiTietFF"
				+ " join FastFood on FastFood.MaFF = ChiTietFF.MaFF where ChiTietFF.MaGD = '"+maGD+"'";
		
		String ff="";
		
		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			
			while(resultSet.next()){
				ff +=  resultSet.getString(1) + "("+resultSet.getInt(2)+"), ";
			}
			
			resultSet.close();
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!ff.equals("")) ff = ff.substring(0,ff.length()-2);
		
		return ff;
	}

	private String getSeatsBooked(String maGD) {
		
		String query = "select MaGhe from ChiTietViTri where MaGD = '"+maGD+"'";
		String seats = "";
		
		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			
			while(resultSet.next()){
				seats +=  resultSet.getString(1) + ", ";
			}
			
			resultSet.close();
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!seats.equals("")) seats = seats.substring(0, seats.length()-2);
		
		return seats;
		
	}

	public ArrayList<Poster> getPosterList() {
		ArrayList<Poster> list = new ArrayList<Poster>();
		
		String query = "select * from Poster";
		
		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			
			while(resultSet.next()){
				Poster p = new Poster();
				
				p.setMaPoster(resultSet.getString(1));
				p.setPoster(resultSet.getString(2));
				p.setMoTa(resultSet.getString(3));
				p.setLink(resultSet.getString(4));
				
				list.add(p);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
