package struts.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import struts.admin.bean.FastFood;
import struts.admin.bean.Movie;
import struts.admin.bean.Room;
import struts.admin.bean.Seat;
import struts.admin.bean.Session;
import struts.admin.bean.TicketTypeSetting;
import struts.dao.DAO;
import struts.util.Utilities;

public class TicketSalesAdminDAO extends DAO {
	int totalTicketPrice = 0;
	int totalFastfoodPrice = 0;
	Utilities util = new Utilities();

	public ArrayList<Movie> getNowShowingMovie() {
		// TODO Auto-generated method stub
		String query = "select distinct Phim.MaPhim, Phim.TenPhim from Phim "
				+ "join LichChieu on LichChieu.MaPhim = Phim.MaPhim " + "where CAST(LichChieu.TGBatDau as DATE) >= '"
				+ util.getCurrentDate() + "' and LichChieu.TrangThai = '1' ;";

		System.out.println("query get phimDangChieu:" + query);

		ArrayList<Movie> list = new ArrayList<Movie>();

		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);

			while (resultSet.next()) {

				Movie m = new Movie();

				m.setMaPhim(resultSet.getString(1));
				m.setTenPhim(resultSet.getString(2));

				list.add(m);
			}

			resultSet.close();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Session> getDatesOfMovie(String movieId) {

		ArrayList<Session> list = new ArrayList<Session>();

		String current_date = util.getCurrentDate();

		String query = "select distinct CAST(TGBatDau as DATE) from LichChieu where TrangThai = '1' "
				+ "and LichChieu.TGBatDau >= '" + current_date + "' and LichChieu.MaPhim = '" + movieId + "';";

		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);

			while (resultSet.next()) {

				Session s = new Session();
				s.setNgayChieu(util.convertDate(resultSet.getString(1)));

				list.add(s);

			}

			resultSet.close();
			st.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public ArrayList<Room> getRoomsOfMovie(String movieId, String current_date) {

		ArrayList<Room> list = new ArrayList<Room>();

		String query = "select distinct PhongChieu.MaPhong, PhongChieu.TenPhong from LichChieu join PhongChieu"
				+ " on PhongChieu.MaPhong = LichChieu.MaPhong where LichChieu.TrangThai = '1' and  "
				+ "LichChieu.MaPhim = '" + movieId + "' and CAST(LichChieu.TGBatDau as DATE) = '"
				+ util.convertDate(current_date) + "'";

		Statement st;
		try {
			st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			while (resultSet.next()) {

				Room r = new Room();
				r.setMaPhong(resultSet.getString(1));
				r.setTenPhong(resultSet.getString(2));

				list.add(r);

			}

			resultSet.close();
			st.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public ArrayList<Session> getSessionOfMovie(String movieId, String current_date, String roomId) {

		ArrayList<Session> list = new ArrayList<Session>();

		String query = "select LichChieu.MaSuat, LichChieu.TGBatDau from LichChieu join Phim on Phim.MaPhim = LichChieu.MaPhim "
				+ " join PhongChieu on PhongChieu.MaPhong = LichChieu.MaPhong where LichChieu.TrangThai = '1' and "
				+ " CAST(LichChieu.TGBatDau as DATE) = '" + util.convertDate(current_date) + "' "
				+ " and PhongChieu.MaPhong = '" + roomId + "' and Phim.MaPhim = '" + movieId + "'";

		String current_date_local = util.getCurrentDate();
		String selected = util.convertDate(current_date);

		System.out.println("current=" + current_date + ", local=" + current_date_local);

		if (current_date_local.equals(selected)) {

			String time_now = util.getTimeNow();

			query = "select LichChieu.MaSuat, LichChieu.TGBatDau from LichChieu join Phim on Phim.MaPhim = LichChieu.MaPhim "
					+ " join PhongChieu on PhongChieu.MaPhong = LichChieu.MaPhong where LichChieu.TrangThai = '1' and "
					+ " CAST(LichChieu.TGBatDau as DATE) = '" + util.convertDate(current_date) + "' "
					+ " and PhongChieu.MaPhong = '" + roomId + "' and Phim.MaPhim = '" + movieId
					+ "' and LichChieu.TGBatDau > '" + time_now + "'";

		}

		System.out.println("query sessionlist:" + query);

		Statement st;
		try {
			st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			while (resultSet.next()) {

				Session s = new Session();
				s.setMaSuat(resultSet.getString(1));

				String gioChieu = resultSet.getString(2);
				gioChieu = gioChieu.substring(11, 16);
				s.setGioChieu(gioChieu);

				list.add(s);

			}

			resultSet.close();
			st.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public ArrayList<TicketTypeSetting> getTicketTypeOfSession(String sessionId) {

		String query = "select CaiDatLoaiVe.MaLoaiVe, LoaiVe.TenLoaiVe, LoaiVe.MoTa, SoGheThuong, SoGheVip, CaiDatLoaiVe.GiaTien "
				+ "from CaiDatLoaiVe join LoaiVe on LoaiVe.MaLoaiVe = CaiDatLoaiVe.MaLoaiVe "
				+ "where CaiDatLoaiVe.MaSuat = '" + sessionId + "' and LoaiVe.TrangThai = '1'";

		ArrayList<TicketTypeSetting> list = new ArrayList<TicketTypeSetting>();

		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);

			while (resultSet.next()) {

				TicketTypeSetting t = new TicketTypeSetting();

				t.setMaLoaiVe(resultSet.getString(1));
				t.setTenLoaiVe(resultSet.getString(2));
				t.setMoTa(resultSet.getString(3));
				t.setSoGheThuong(resultSet.getInt(4));
				t.setSoGheVip(resultSet.getInt(5));
				t.setGiaTien(resultSet.getInt(6));

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

	// UPDATETRANSACTION
	private boolean updateTransaction(String maGD, String username, String sessionId) {

		String query = "Update GiaoDich Set TongTien = ? where MaGD = ?;";

		try {
			PreparedStatement pstatement = connection.prepareStatement(query);
			System.out.println("tong" + this.totalTicketPrice + this.totalFastfoodPrice);
			pstatement.setInt(1, this.totalTicketPrice + this.totalFastfoodPrice);
			pstatement.setString(2, maGD);
			pstatement.executeUpdate();
			pstatement.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	// insertticket
	private boolean insertTicketTypeSelected(String maSuat, String maGD, String ticketTypeSelected) {

		String[] ticketTypeSelected_Array = ticketTypeSelected.split(" ");
		totalTicketPrice = 0;
		try {

			Statement insert_statemnet = connection.createStatement();
			for (int i = 0; i < ticketTypeSelected_Array.length; i++) {

				String[] info = ticketTypeSelected_Array[i].split("-");
				this.totalTicketPrice = this.totalTicketPrice + getPriceTicket(maSuat, info[0], info[1]);
				System.out.println("ve" + this.totalTicketPrice);
				System.out.println(getPriceTicket(maSuat, info[0], info[1]));
				insert_statemnet.addBatch(
						"insert into LoaiVeGiaoDich(MaGD, MaLoaiVe, SL, ThanhTien) values('" + maGD + "', '" + info[0]
								+ "', '" + info[1] + "', '" + getPriceTicket(maSuat, info[0], info[1]) + "');");

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

	private void priceTicketTypeSelectedCalculation(String maSuat, String maGD, String ticketTypeSelected) {

		String[] ticketTypeSelected_Array = ticketTypeSelected.split(" ");
		for (int i = 0; i < ticketTypeSelected_Array.length; i++) {
			String[] info = ticketTypeSelected_Array[i].split("-");
			this.totalTicketPrice = this.totalTicketPrice + getPriceTicket(maSuat, info[0], info[1]);
			System.out.println("ve" + this.totalTicketPrice);
			System.out.println(getPriceTicket(maSuat, info[0], info[1]));
		}
	}

	private int getPriceTicket(String maSuat, String maLoaiVe, String soLuong) {

		String query = "select GiaTien from CaiDatLoaiVe where MaLoaiVe = '" + maLoaiVe + "' and MaSuat = '" + maSuat
				+ "';";
		int giaLoaiVe = 0;
		try {
			ResultSet resultSet = connection.createStatement().executeQuery(query);
			while (resultSet.next()) {
				giaLoaiVe = resultSet.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return giaLoaiVe * Integer.parseInt(soLuong);

	}

	private void priceFastfoodTypeSelectedCalculation(String maGD, String fastFoodTypeSelected) {

		String[] fastFoodTypeSelected_Array = fastFoodTypeSelected.split(" ");
		for (int i = 0; i < fastFoodTypeSelected_Array.length; i++) {
			String[] info = fastFoodTypeSelected_Array[i].split("-");
			this.totalFastfoodPrice = this.totalFastfoodPrice + getPriceFastFood(info[0], info[1]);
			System.out.println(this.totalFastfoodPrice);

		}
	}

	private int getPriceFastFood(String maFF, String soLuong) {
		String query = "select GiaTien from FastFood where MaFF = '" + maFF + "';";
		int giaLoaiFF = 0;
		try {
			ResultSet resultSet = connection.createStatement().executeQuery(query);
			while (resultSet.next()) {
				giaLoaiFF = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return giaLoaiFF * Integer.parseInt(soLuong);
	}

	public String getSeatsBooked(String sessionId) {
		String booked_str = "";
		String query = "select ChiTietViTri.MaGhe from ChiTietViTri "
				+ "join GiaoDich on GiaoDich.MaGD = ChiTietViTri.MaGD " + "where MaSuat = '" + sessionId + "'";

		try {
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {

				booked_str += resultSet.getString(1) + " ";

			}

			resultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return booked_str.trim();
	}

	public String getSeatsMap(String sessionId) {

		String query = "select PhongChieu.SoDoGhe from PhongChieu "
				+ "join LichChieu on LichChieu.MaPhong = PhongChieu.MaPhong "
				+ "where LichChieu.TrangThai = '1' and LichChieu.MaSuat = '" + sessionId + "'";
		String map = null;
		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			while (resultSet.next()) {
				map = resultSet.getString(1);
			}

			resultSet.close();
			st.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;

	}

	public ArrayList<FastFood> getFastFoodList() {

		String query = "select FastFood.MaFF, FastFood.TenFF, FastFood.MoTa, FastFood.GiaTien from FastFood where TrangThai = '1'";

		ArrayList<FastFood> list = new ArrayList<FastFood>();

		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);

			while (resultSet.next()) {

				FastFood ff = new FastFood();

				ff.setMaFF(resultSet.getString(1));
				ff.setTenFF(resultSet.getString(2));
				ff.setMoTa(resultSet.getString(3));
				ff.setGiaTien(resultSet.getInt(4) + "");

				list.add(ff);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	// ----------------------SAVE TRANSACTION--------------------//
	public boolean checkVaildType(String sessionId, String ticketTypeSelected, String fastFoodTypeSelected,
			String seatsSelected) {

		int soGheVipDuocChon = 0, soGheThuongDuocChon = 0, soGheVip = 0, soGheThuong = 0;

		String[] ticketTypeSelected_Array = ticketTypeSelected.split(" ");
		String[] fastFoodTypeSelected_Array = fastFoodTypeSelected.split(" ");
		String[] seatsSelected_Array = seatsSelected.split(" ");

		ArrayList<Seat> seatList = getSeatsOfRoom(sessionId);
		Session session = getSession(sessionId);

		ArrayList<TicketTypeSetting> listTypeTicketSetting = session.getTicketTypeSettingList();
		ArrayList<FastFood> listFastFood = getFastFoodList();

		for (int i = 0; i < ticketTypeSelected_Array.length; i++) { // duyệt qua
																	// các mã
																	// loại vé

			int index = checkExistTicketType(ticketTypeSelected_Array[i], listTypeTicketSetting);

			if (index != -1) { // mã loại vé hợp lệ

				String[] data = ticketTypeSelected_Array[i].split("-");
				int soLuong = Integer.parseInt(data[1]);

				soGheVipDuocChon += listTypeTicketSetting.get(index).getSoGheVip() * soLuong;
				soGheThuongDuocChon += listTypeTicketSetting.get(index).getSoGheThuong() * soLuong;

			} else {
				System.out.println("ERROR: Ma loai ve khong duoc cai dat cho suat!");
				return false;
			}

		}
		// kiểm tra danh sách FF

		// kiểm tra danh sách ghế chọn
		for (int i = 0; i < seatsSelected_Array.length; i++) {
			int index = checkValidSeat(sessionId, seatList, seatsSelected_Array[i]);

			if (index != -1) {// mã ghế hợp lệ
				// cập nhật số lượng
				if (seatList.get(index).getLoaiGhe() == 1) {
					soGheThuong++;
				} else
					soGheVip++;
			} else {
				System.out.println("ERROR: Loi danh sach ghe chon (ghe da duoc chon hoac ma ghe khong hop le)!");
				return false;
			}
		}
		// so sánh số lượng
		if (soGheVipDuocChon == soGheVip && soGheThuongDuocChon == soGheThuong) {
			return true;
		} else {

			System.out.println("ERROR: Vuot qua sl ghe cho phep!");
			System.out.println("soGheVipDuocChon=" + soGheVipDuocChon);
			System.out.println("soGheVip=" + soGheVip);
			System.out.println("soGheThuongDuocChon=" + soGheThuongDuocChon);
			System.out.println("soGheThuong=" + soGheThuong);

			return false;
		}

	}

	public Session getSession(String sessionId) {

		Session session = new Session();

		// query thông tin cơ bản của suất
		String query = "select LichChieu.MaSuat, LichChieu.TGBatDau, LichChieu.[3D], Phim.TenPhim, Phim.DoTuoiChoPhep, Phim.Thumbnail, PhongChieu.TenPhong, PhongChieu.SoDoGhe "
				+ "from LichChieu join Phim on Phim.MaPhim = LichChieu.MaPhim join PhongChieu on PhongChieu.MaPhong = LichChieu.MaPhong "
				+ "where LichChieu.MaSuat = '" + sessionId + "'";

		try {
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {

				String maSuat = resultSet.getString(1);
				String TGBatDau = resultSet.getString(2);

				String gioChieu = TGBatDau.substring(11, 16);
				String ngayChieu = TGBatDau.substring(0, 10);

				boolean type3d = resultSet.getBoolean(3);

				String tenPhim = resultSet.getString(4);
				String doTuoiChoPhep = resultSet.getString(5);
				String thumbnail = resultSet.getString(6);
				String tenPhong = resultSet.getString(7);
				String soDoGhe = resultSet.getString(8);

				session.setMaSuat(maSuat);
				session.setGioChieu(gioChieu);
				session.setNgayChieu(ngayChieu);

				Movie movie = new Movie();
				movie.setTenPhim(tenPhim);
				movie.setDoTuoiChoPhep(doTuoiChoPhep);
				movie.setThumbnail(thumbnail);
				session.setMovieOfSession(movie);

				Room room = new Room();
				room.setTenPhong(tenPhong);
				room.setSoDoGhe(soDoGhe);
				session.setRoomOfSession(room);

				getTicketTypeSetting(session, sessionId);

				if (type3d)
					session.setType3d(true);
				else
					session.setType3d(false);

			}
			resultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return session;

	}

	private void getTicketTypeSetting(Session session, String sessionId) {

		ArrayList<TicketTypeSetting> ticketTypeList = new ArrayList<TicketTypeSetting>();

		String query = "select CaiDatLoaiVe.MaLoaiVe, LoaiVe.TenLoaiVe, LoaiVe.MoTa, LoaiVe.SoGheThuong, LoaiVe.SoGheVip, CaiDatLoaiVe.GiaTien "
				+ "from CaiDatLoaiVe join LoaiVe on LoaiVe.MaLoaiVe = CaiDatLoaiVe.MaLoaiVe "
				+ "where CaiDatLoaiVe.MaSuat = '" + sessionId + "';";
		try {
			ResultSet resultSetInfo = connection.createStatement().executeQuery(query);
			while (resultSetInfo.next()) {

				TicketTypeSetting ticketTypeSetting = new TicketTypeSetting();
				String maLoaiVe = resultSetInfo.getString(1);
				String tenLoaiVe = resultSetInfo.getString(2);
				String moTa = resultSetInfo.getString(3);
				int soGheThuong = resultSetInfo.getInt(4);
				int soGheVip = resultSetInfo.getInt(5);
				int giaTien = resultSetInfo.getInt(6);

				ticketTypeSetting.setMaLoaiVe(maLoaiVe);
				ticketTypeSetting.setTenLoaiVe(tenLoaiVe);
				ticketTypeSetting.setMoTa(moTa);
				ticketTypeSetting.setSoGheThuong(soGheThuong);
				ticketTypeSetting.setSoGheVip(soGheVip);
				ticketTypeSetting.setGiaTien(giaTien);

				ticketTypeList.add(ticketTypeSetting);

			}
			resultSetInfo.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setTicketTypeSettingList(ticketTypeList);
	}

	private int checkValidSeat(String sessionId, ArrayList<Seat> seatList, String seatCode) {

		String[] bookedSeats_Array = getSeatsBooked(sessionId).split(" ");

		// kiểm tra tồn tại trong booked
		for (int i = 0; i < bookedSeats_Array.length; i++) {
			if (bookedSeats_Array[i].equals(seatCode))
				return -1;
		}

		// kiểm tra tồn tại trong sơ đồ ghế
		for (int i = 0; i < seatList.size(); i++) {

			if (seatList.get(i).getMaGhe().equals(seatCode))
				return i;

		}

		return -1;

	}

	public int checkExistTicketType(String ticketTypeSelected_Info,
			ArrayList<TicketTypeSetting> listTypeTicketSetting) {

		String[] ticket_info = ticketTypeSelected_Info.split("-");

		for (int j = 0; j < listTypeTicketSetting.size(); j++) {

			if (ticket_info[0].equals(listTypeTicketSetting.get(j).getMaLoaiVe())) {
				return j;
			}
		}
		return -1;
	}

	public ArrayList<Seat> getSeatsOfRoom(String sessionId) {

		ArrayList<Seat> listSeat = new ArrayList<Seat>();

		String query = "select PhongChieu.SoDoGhe from PhongChieu "
				+ "join LichChieu on PhongChieu.MaPhong = LichChieu.MaPhong " + "where LichChieu.MaSuat = '" + sessionId
				+ "';";
		String soDoGhe = "";
		try {
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				soDoGhe = resultSet.getString(1).trim();
			}

			// convert
			String[] arr_info = soDoGhe.split(" ");
			//
			for (int i = 0; i < arr_info.length; i++) {
				Seat seat = new Seat();
				String[] temp = arr_info[i].split("-");
				seat.setMaGhe(temp[2]);
				seat.setLoaiGhe(Integer.parseInt(temp[3]));
				listSeat.add(seat);
			}

			resultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listSeat;
	}

	public String finishTransaction(String username, String sessionId, String ticketTypeSelected,
			String fastFoodTypeSelected, String seatsSelected) {

		String maGD = getNewTransactionId();
		if (insertTransaction(maGD, username, sessionId)) {

			if (insertChiTietViTri(maGD, seatsSelected)) {

				if (insertTicketTypeSelected(sessionId, maGD, ticketTypeSelected)) {

					if (insertFastFoodSelected(maGD, fastFoodTypeSelected)) {
						updateTransaction(maGD, username, sessionId);
						return maGD;

					} else {
						System.out.println("roll back insert Fast Food Selected");
						rollback_Transaction(maGD);
						return "-1";
					}

				} else {
					System.out.println("roll back insert Ticket Type Selected");
					rollback_Transaction(maGD);
					return "-1";
				}

			} else {
				System.out.println("roll back insert insert ChiTiet ViTri");
				rollback_Transaction(maGD);
				return "-1";
			}

		} else {
			// roll back
			System.out.println("roll back insert insert Transaction");
			rollback_Transaction(maGD);
			return "-1";
		}

	}

	private void rollback_Transaction(String maGD) {

		Statement rollback_statemnet;
		try {
			rollback_statemnet = connection.createStatement();

			rollback_statemnet.addBatch("delete from ChiTietViTri where MaGD = '" + maGD + "';");
			rollback_statemnet.addBatch("delete from LoaiVeGiaoDich where MaGD = '" + maGD + "';");
			rollback_statemnet.addBatch("delete from ChiTietFF where MaGD = '" + maGD + "';");

			rollback_statemnet.executeBatch();
			rollback_statemnet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Rollback ERROR");
			e.printStackTrace();
		}
	}

	private boolean insertFastFoodSelected(String maGD, String fastFoodTypeSelected) {
		totalFastfoodPrice = 0;
		System.out.println("thuc an da chon"+fastFoodTypeSelected);
		if (fastFoodTypeSelected.equals(""))
			return true;
		else {

			String[] fastFoodTypeSelected_Array = fastFoodTypeSelected.split(" ");

			try {

				Statement insert_statemnet = connection.createStatement();
				for (int i = 0; i < fastFoodTypeSelected_Array.length; i++) {

					String[] info = fastFoodTypeSelected_Array[i].split("-");
					this.totalFastfoodPrice = this.totalFastfoodPrice + getPriceFastFood(info[0], info[1]);
					System.out.println("gia thuc an"+this.totalFastfoodPrice);
					insert_statemnet
							.addBatch("insert into ChiTietFF(MaGD, MaFF, SL, ThanhTien) values('" + maGD + "', '"
									+ info[0] + "', '" + info[1] + "', '" + getPriceFastFood(info[0], info[1]) + "');");

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
	}

	private boolean insertTransaction(String maGD, String username, String sessionId) {

		String query = "INSERT GiaoDich(MaGD, Username, MaSuat, ThoiGianGD, TongTien , TrangThai) VALUES(?, ?, ?, ?, ?, ?);";

		try {
			PreparedStatement pstatement = connection.prepareStatement(query);
			pstatement.setString(1, maGD);
			pstatement.setString(2, username);
			pstatement.setString(3, sessionId);
			pstatement.setString(4, util.getTimeNow());
			pstatement.setInt(5, 0);
			pstatement.setInt(6, 1);

			pstatement.executeUpdate();

			pstatement.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	// private int getPriceTicket(String maSuat, String maLoaiVe, String
	// soLuong) {
	//
	// String query = "select GiaTien from CaiDatLoaiVe where MaLoaiVe =
	// '"+maLoaiVe+"' and MaSuat = '"+maSuat+"';";
	// int giaLoaiVe=0;
	// try {
	// ResultSet resultSet = connection.createStatement().executeQuery(query);
	// while(resultSet.next()){
	// giaLoaiVe = resultSet.getInt(1);
	// }
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return giaLoaiVe*Integer.parseInt(soLuong);
	//
	// }

	private boolean insertChiTietViTri(String maGD, String seatsSelected) {

		String[] seats = seatsSelected.split(" ");

		try {
			Statement insert_statemnet = connection.createStatement();

			for (int i = 0; i < seats.length; i++) {

				insert_statemnet
						.addBatch("insert into ChiTietViTri(MaGD, MaGhe) values('" + maGD + "', '" + seats[i] + "');");
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

	private String getNewTransactionId() {
		String idDo = null;
		try {
			ResultSet resultSet = statement.executeQuery("SELECT MaGD FROM GiaoDich order by MaGD;");
			int id = 0;
			while (resultSet.next()) {
				idDo = resultSet.getString(1);
				int temp = Integer.parseInt(idDo.substring(2, idDo.length()));
				if (temp > id)
					id = temp;
			}
			//
			if (idDo == null)
				return "GD1";
			System.out.println("Last id:" + id);
			id++;
			return "GD" + id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
