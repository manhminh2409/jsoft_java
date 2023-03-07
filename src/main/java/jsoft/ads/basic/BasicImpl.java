package jsoft.ads.basic;

import java.sql.*;
import jsoft.*;

public class BasicImpl implements Basic {

	// Đối tượng làm việc với Basic
	private String objectName;

	// Bộ quản lý kết nối của riêng Basic
	private ConnectionPool cp;

	// Kết nối để làm việc với cơ sở dữ liệu
	protected Connection con;

	public BasicImpl(String objectName, ConnectionPool cp) {

		// Xác định tên đối tượng làm việc với Basic
		this.objectName = objectName;

		// Xác định bộ quản lý kết nối làm việc cho Basic
		if (cp == null) {
			this.cp = new ConnectionPoolImpl();// tạo mới
		} else {
			this.cp = cp;
		}

		// Xin kết nối để làm việc
		try {
			this.con = this.cp.getConnection(this.objectName);

			// Kiểm tra chế độ thực thi của kết nối
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);// huỷ thực thi tự động
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Phương thức thực hiện cập nhập vào cơ sở dữ liệu dùng chung cho add, del,
	 * edit
	 * 
	 * @param pre
	 * @return
	 */
	private synchronized boolean exe(PreparedStatement pre) {

		if (pre != null) {

			try {
				int result = pre.executeUpdate();

				if (result == 0) {
					// Không có bản ghi nào được thực hiện
					this.con.rollback();
					return false;
				}

				// Xác nhận thực thi sau cùng vào cơ sở dữ liệu
				this.con.commit();
				return true;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				// Trở lại trạng thái an toàn của kết nối
				try {
					this.con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} finally {
				// Dọn dẹp sau cùng
				pre = null;
			}

		}

		return false;
	}

	@Override
	public boolean add(PreparedStatement pre) {
		// TODO Auto-generated method stub
		return this.exe(pre);
	}

	@Override
	public boolean edit(PreparedStatement pre) {
		// TODO Auto-generated method stub
		return this.exe(pre);
	}

	@Override
	public boolean del(PreparedStatement pre) {
		// TODO Auto-generated method stub
		return this.exe(pre);
	}

	@Override
	public ResultSet get(String sql, int value) {
		// TODO Auto-generated method stub

		// Biên dịch câu lệnh sql
		try {
			
			PreparedStatement preGet = this.con.prepareStatement(sql);

			if (value > 0) {
				preGet.setInt(1, value);
			}
			
			return preGet.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
			try {
				
				this.con.rollback();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public ResultSet get(String sql, String name, String pass) {
		// TODO Auto-generated method stub

		try {
			PreparedStatement preGet = this.con.prepareStatement(sql);

			preGet.setString(1, name);
			preGet.setString(2, pass);

			// Trả về kết quả
			return preGet.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public ResultSet gets(String sql) {
		// TODO Auto-generated method stub
		return this.get(sql, 0);
	}

	@Override
	public ConnectionPool getCP() {
		// TODO Auto-generated method stub
		return this.cp;
	}

	@Override
	public void releaseConnection() {
		// TODO Auto-generated method stub
		try {
			this.cp.releaseConnection(this.con, this.objectName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
