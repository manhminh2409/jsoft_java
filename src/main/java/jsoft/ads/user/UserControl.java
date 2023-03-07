package jsoft.ads.user;

import jsoft.*;
import jsoft.objects.*;

import java.util.*;

public class UserControl {

	private UserModel um;

	public UserControl(ConnectionPool cp) {
		this.um = new UserModel(cp);
	}

	protected void finalize() throws Throwable {
		this.um = null;
	}

	public ConnectionPool getCP() {
		return this.um.getCP();
	}

	public void releaseConnection() {
		this.um.releaseConnection();
	}

	// -------------------------------------
	public boolean addUser(UserObject item) {
		return this.um.addUser(item);
	}

	public boolean editUser(UserObject item) {
		return this.um.editUser(item);
	}

	public boolean delUser(UserObject item) {
		return this.um.delUser(item);
	}
	
	public boolean changePass(UserObject item) {
		return this.um.changePass(item);
	}

	// -------------------------------------
	public UserObject getUserObject(int id) {
		return this.um.getUserObject(id);
	}

	public UserObject getUserObject(String username, String userpass) {
		return this.um.getUserObject(username, userpass);
	}

	// -------------------------------------
	public String viewUsers(UserObject similer, UserSort us, short page, byte total) {
		// Lấy dữ liệu từ Model
		ArrayList<UserObject> items = this.um.getUserObjects(similer, us, page, total);

		return UserLibrary.viewUsers(items, similer);
	}

	public static void main(String[] args) {
		// Tạo đối tượng thực thi chức năng
		UserControl uc = new UserControl(null);

		// Lấy cấu trúc trình bày
		String view = uc.viewUsers(null, UserSort.NAME, (short) 1, (byte) 20);

		// Trả về kết nối
		uc.releaseConnection();

		// Hiển thị
		System.out.print(view);
	}

}
