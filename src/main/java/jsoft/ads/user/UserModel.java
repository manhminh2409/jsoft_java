package jsoft.ads.user;

import jsoft.*;
import jsoft.objects.*;

import java.sql.*;
import java.util.*;

public class UserModel {

	private User u;

	public UserModel(ConnectionPool cp) {
		this.u = new UserImpl(cp);
	}

	protected void finalize() throws Throwable {
		this.u = null;
	}

	public ConnectionPool getCP() {
		return this.u.getCP();
	}

	public void releaseConnection() {
		this.u.releaseConnection();
	}

	// -------------------------------------
	public boolean addUser(UserObject item) {
		return this.u.addUser(item);
	}

	public boolean editUser(UserObject item) {
		return this.u.editUser(item);
	}

	public boolean delUser(UserObject item) {
		return this.u.delUser(item);
	}
	
	public boolean changePass(UserObject item) {
		return this.u.changePass(item);
	}
	// --------------------------------------
	public UserObject getUserObject(int id) {
		UserObject item = null;

		ResultSet rs = this.u.getUser(id);

		if (rs != null) {
			try {
				if (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_pass(rs.getString("user_pass"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_officephone(rs.getString("user_officephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_permission(rs.getByte("user_permission"));
					item.setUser_notes(rs.getString("user_notes"));
					item.setUser_logined(rs.getInt("user_logined"));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return item;
	}

	public UserObject getUserObject(String username, String userpass) {
		UserObject item = null;

		ResultSet rs = this.u.getUser(username, userpass);

		if (rs != null) {
			try {
				if (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_pass(rs.getString("user_pass"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_officephone(rs.getString("user_officephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_permission(rs.getByte("user_permission"));
					item.setUser_notes(rs.getString("user_notes"));
					item.setUser_logined(rs.getInt("user_logined"));
				}

				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return item;
	}

	public ArrayList<UserObject> getUserObjects(UserObject similer, UserSort us, short page, byte total) {
		ArrayList<UserObject> items = new ArrayList<>();

		UserObject item = null;

		int at = total * (page - 1);

		ResultSet rs = this.u.getUsers(similer, us, at, total);

		if (rs != null) {
			try {
				while (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_pass(rs.getString("user_pass"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_officephone(rs.getString("user_officephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_permission(rs.getByte("user_permission"));
					item.setUser_notes(rs.getString("user_notes"));
					item.setUser_logined(rs.getInt("user_logined"));
					item.setUser_created_date(rs.getString("user_created_date"));

					// Đưa đối tượng vào danh sách
					items.add(item);

				}

				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return items;
	}
	// ---------------------------------------------
}
