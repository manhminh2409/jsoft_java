package jsoft.ads.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.ads.basic.BasicImpl;
import jsoft.objects.UserObject;

public class UserImpl extends BasicImpl implements User {

	public UserImpl(ConnectionPool cp) {
		super("User", cp);
	}

	@Override
	public boolean addUser(UserObject item) {
		// TODO Auto-generated method stub

		if (this.IsExisting(item)) {
			return false;
		}

		String sql = "INSERT INTO tbluser(";
		sql += "user_name, user_pass, user_fullname ";
		sql += ", user_birthday, user_mobilephone, user_homephone, user_officephone ";
		sql += ", user_email, user_address , user_jobarea,user_job, user_position ";
		sql += ", user_applyyear, user_permission, user_notes ";
		sql += ", user_roles,user_logined, user_created_date, user_last_modified ";
		sql += ", user_last_logined, user_parent_id, user_actions ";
		sql += ") ";
		sql += "VALUES(?,md5(?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

		// Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			// Truyền giá trị
			pre.setString(1, item.getUser_name());
			pre.setString(2, item.getUser_pass());
			pre.setString(3, item.getUser_fullname());
			pre.setString(4, item.getUser_birthday());
			pre.setString(5, item.getUser_mobilephone());
			pre.setString(6, item.getUser_homephone());
			pre.setString(7, item.getUser_officephone());
			pre.setString(8, item.getUser_email());
			pre.setString(9, item.getUser_address());
			pre.setString(10, item.getUser_jobarea());
			pre.setString(11, item.getUser_job());
			pre.setString(12, item.getUser_position());
			pre.setShort(13, item.getUser_applyyear());
			pre.setByte(14, item.getUser_permission());
			pre.setString(15, item.getUser_notes());
			pre.setString(16, item.getUser_roles());
			pre.setInt(17, item.getUser_logined());
			pre.setString(18, item.getUser_created_date());
			pre.setString(19, item.getUser_last_modified());
			pre.setString(20, item.getUser_last_logined());
			pre.setInt(21, item.getUser_parent_id());
			pre.setByte(22, item.getUser_actions());

			return this.add(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Phương thức ràng buộc duy nhất bằng mã nguồn <br>
	 * <u><i>Update: 10/10/2022</i></u></br>
	 * 
	 * @param item
	 * @return
	 * @author Mạnh
	 */
	private boolean IsExisting(UserObject item) {
		boolean flag = false;

		String sql = "SELECT user_id FROM tbluser WHERE user_name = '" + item.getUser_name() + "' ";

		ResultSet rs = this.gets(sql);

		if (rs != null) {
			try {
				if (rs.next()) {
					flag = true;
				}

				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;
	}

	@Override
	public boolean editUser(UserObject item) {
		// TODO Auto-generated method stub

		String sql = "UPDATE tbluser SET ";
		sql += "user_fullname = ? ";
		sql += ", user_birthday = ?, user_mobilephone = ?, user_homephone = ?, user_officephone = ? ";
		sql += ", user_email = ?, user_address = ?, user_jobarea = ?, user_job = ?, user_position = ? ";
		sql += ", user_applyyear = ?, user_permission = ?, user_notes = ? ";
		sql += ", user_roles = ?, user_last_modified = ? ";
		sql += ", user_last_logined = ?, user_actions = ? ";
		sql += " WHERE user_id = ? ";

		// Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			// Truyền giá trị

			pre.setString(1, item.getUser_fullname());
			pre.setString(2, item.getUser_birthday());
			pre.setString(3, item.getUser_mobilephone());
			pre.setString(4, item.getUser_homephone());
			pre.setString(5, item.getUser_officephone());
			pre.setString(6, item.getUser_email());
			pre.setString(7, item.getUser_address());
			pre.setString(8, item.getUser_jobarea());
			pre.setString(9, item.getUser_job());
			pre.setString(10, item.getUser_position());
			pre.setShort(11, item.getUser_applyyear());
			pre.setByte(12, item.getUser_permission());
			pre.setString(13, item.getUser_notes());
			pre.setString(14, item.getUser_roles());

			pre.setString(15, item.getUser_last_modified());
			pre.setString(16, item.getUser_last_logined());

			pre.setByte(17, item.getUser_actions());

			pre.setInt(18, item.getUser_id());

			return this.edit(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delUser(UserObject item) {
		// TODO Auto-generated method stub

		if (!IsEmpty(item)) {
			return false;
		}
		String sql = "DELETE FROM tbluser WHERE user_id = ? ";

		// Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setInt(1, item.getUser_id());

			return this.del(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Phương thức kiểm tra User xoá có liên kết với bảng khác không <br>
	 * <u><i>Update: 10/10/2022</i></u></br>
	 * 
	 * @param item
	 * @return
	 */
	private boolean IsEmpty(UserObject item) {
		boolean flag = true;

		String sql1 = "SELECT article_id FROM tblarticle WHERE article_author_name ='" + item.getUser_name() + "'";

		String sql2 = "SELECT product_id FROM tblproduct WHERE product_manager_id = " + item.getUser_id();

		String sql3 = "SELECT user_id FROM tbluser WHERE user_parent_id = " + item.getUser_id();

		ResultSet rs1 = this.gets(sql1);
		ResultSet rs2 = this.gets(sql2);
		ResultSet rs3 = this.gets(sql3);

		if (rs1 != null || rs2 != null || rs3 != null) {
			try {
				if (rs1.next() || rs2.next() || rs3.next()) {
					flag = false;
				}

				rs1.close();
				rs2.close();
				rs3.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public ResultSet getUser(int id) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM tbluser WHERE user_id = ?";

		return this.get(sql, id);
	}

	@Override
	public ResultSet getUser(String username, String userpass) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbluser WHERE (user_name = ?) AND (user_pass = md5(?))";

		return this.get(sql, username, userpass);
	}

	@Override
	public ResultSet getUsers(UserObject similar, int at, byte total) {
		// TODO Auto-generated method stub

		return this.getUsers(similar, UserSort.ID, at, total);
	}

	@Override
	public ResultSet getUsers(UserObject similar, UserSort us, int at, byte total) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM tbluser ";

		sql += this.createConditions(similar);

		switch (us) {
		case ID:
			sql += "ORDER BY user_id DESC ";
			break;
		case NAME:
			sql += "ORDER BY user_name ASC ";
			break;
		case FULLNAME:
			sql += "ORDER BY user_fullname ASC ";
			break;
		case AGE:
			sql += "ORDER BY user_age ASC ";
			break;
		}

		sql += "LIMIT " + at + ", " + total;

		return this.gets(sql);
	}

	private String createConditions(UserObject similer) {
		String tmp = "";

		if (similer != null) {
			// Xác định từ khoá tìm kiếm
			String keyw = similer.getUser_name();

			if (keyw != null && !keyw.equalsIgnoreCase("")) {
				// name, fullname, email, notes, address
				tmp += "( ";

				tmp += "(user_name LIKE '%" + keyw + "%') OR ";
				tmp += "(user_fullname LIKE '%" + keyw + "%') OR ";
				tmp += "(user_email LIKE '%" + keyw + "%') OR ";
				tmp += "(user_address LIKE '%" + keyw + "%') OR ";
				tmp += "(user_notes LIKE '%" + keyw + "%') ";

				tmp += ") ";
			}
		}

		if (!tmp.equalsIgnoreCase("")) {
			tmp = "WHERE " + tmp;
		}

		return tmp;
	}
	
	@Override
	public boolean changePass(UserObject item) {
		// TODO Auto-generated method stub
		
		String sql = "UPDATE tbluser SET ";
		sql += " user_pass = MD5(?) ";
		sql += " WHERE user_id = ? ";
		
		//Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			
			//Truyền giá trị
			pre.setString(1, item.getUser_pass());
			
			pre.setInt(2, item.getUser_id());
			
			return this.edit(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public static void main(String[] args) {
		// Tạo bộ quản lý kết nối
		ConnectionPool cp = new ConnectionPoolImpl();

		// Tạo đối tượng thực thi chức năng Interface
		User u = new UserImpl(cp);

		// Tạo đối tượng lưu trữ thông tin
		UserObject nUser = new UserObject();
		nUser.setUser_id(46);
		nUser.setUser_pass("123456");

		// Thực hiện thêm mới
		boolean result = u.changePass(nUser);

		// Thông báo
		if (!result) {
			System.out.println("\n---------------KHÔNG THÀNH CÔNG----------------\n");
		}

		// Lấy tập kết quả
		ResultSet rs = u.getUsers(null, 0, (byte)20);

		// Trả về kết nối
		u.releaseConnection();

		// Duyệt và in ra màn hình
		String row;

		if (rs != null) {
			try {
				while (rs.next()) {
					row = "ID: " + rs.getInt("user_id");
					row += "\t\tNAME: " + rs.getString("user_name");
					row += "\t\tPASS: " + rs.getString("user_pass");
					row += "\t\tFULLNAME: " + rs.getString("user_fullname");
					row += "\t\tEMAIL: " + rs.getString("user_email");
					
					System.out.println(row);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	

}
