package jsoft.ads.article.section;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.ads.basic.BasicImpl;
import jsoft.objects.SectionObject;

public class SectionImpl extends BasicImpl implements Section {

	public SectionImpl(ConnectionPool cp) {
		super("Section", cp);
		// TODO Auto-generated constructor stub
	}

	public SectionImpl(String objectName, ConnectionPool cp) {
		super(objectName, cp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addSection(SectionObject item) {
		// TODO Auto-generated method stub

		if (IsExisting(item)) {
			return false;
		}
		String sql = "INSERT INTO tblsection( ";
		sql += "section_name, section_notes, section_created_date, ";
		sql += "section_manager_id, section_enable, section_delete, ";
		sql += "section_last_modified, section_created_author_id, ";
		sql += "section_name_en, section_language ";
		sql += ") ";
		sql += "VALUES(?,?,?,?,?,?,?,?,?,?)";

		// Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			// Truyền giá trị
			pre.setString(1, item.getSection_name());
			pre.setString(2, item.getSection_notes());
			pre.setString(3, item.getSection_created_date());
			pre.setInt(4, item.getSection_manager_id());
			pre.setBoolean(5, item.isSection_enable());
			pre.setBoolean(6, item.isSection_delete());
			pre.setString(7, item.getSection_last_modified());
			pre.setInt(8, item.getSection_created_author_id());
			pre.setString(9, item.getSection_name_en());
			pre.setByte(10, item.getSection_language());

			return this.add(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Phương thức kiểm tra ràng buộc duy nhất <br>
	 * <u><i>Update: 11/10/2022</i></u></br>
	 * 
	 * @param item
	 * @return
	 */
	private boolean IsExisting(SectionObject item) {
		boolean flag = false;

		String sql = "SELECT section_id FROM tblsection WHERE section_name ='" + item.getSection_name() + "'";

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
	public boolean editSection(SectionObject item) {
		// TODO Auto-generated method stub

		String sql = "UPDATE tblsection SET ";
		sql += "section_name = ?, section_notes = ?, section_manager_id = ?, ";
		sql += "section_enable = ?, section_delete = ?, ";
		sql += "section_last_modified = ?, ";
		sql += "section_name_en = ?, section_language = ? ";
		sql += "WHERE section_id = ? ";

		// Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			// Truyền giá trị
			pre.setString(1, item.getSection_name());
			pre.setString(2, item.getSection_notes());

			pre.setInt(3, item.getSection_manager_id());
			pre.setBoolean(4, item.isSection_enable());
			pre.setBoolean(5, item.isSection_delete());
			pre.setString(6, item.getSection_last_modified());

			pre.setString(7, item.getSection_name_en());
			pre.setByte(8, item.getSection_language());

			pre.setInt(9, item.getSection_id());

			return this.edit(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delSection(SectionObject item) {
		// TODO Auto-generated method stub
		if (!IsEmpty(item)) {
			return false;
		}
		String sql = "DELETE FROM tblsection WHERE section_id = ? ";

		// Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setInt(1, item.getSection_id());

			return this.del(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Phương thức kiểm tra Section xoá có liên kết với bảng khác không <br>
	 * <u><i>Update: 10/10/2022</i></u></br>
	 * 
	 * @param item
	 * @return
	 */
	private boolean IsEmpty(SectionObject item) {
		boolean flag = true;

		String sql1 = "SELECT article_id FROM tblaricle WHERE article_section_id ='"
				+ item.getSection_created_author_id() + "'";

		String sql2 = "SELECT category_section_id FROM tblcategory WHERE category_section_id = " + item.getSection_id();

		ResultSet rs1 = this.gets(sql1);
		ResultSet rs2 = this.gets(sql2);

		if (rs1 != null || rs2 != null) {
			try {
				if (rs1.next() || rs2.next()) {
					flag = false;
				}

				rs1.close();
				rs2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public ResultSet getSection(int id) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM tblsection WHERE section_id = ?";

		return this.get(sql, id);
	}

	@Override
	public ResultSet getSections(SectionObject similar, int at, byte total) {
		// TODO Auto-generated method stub

		return this.getSections(similar, SectionSort.NAME, at, total);
	}

	@Override
	public ResultSet getSections(SectionObject similar, SectionSort ss, int at, byte total) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM tblsection s ";
		sql += "LEFT JOIN tbluser u ON s.section_created_author_id = u.user_id ";
		sql += "";
		switch (ss) {
		case ID:
			sql += "ORDER BY section_id ASC ";
			break;
		case NAME:
			sql += "ORDER BY section_name ASC ";
			break;
		case CREATED_DATE:
			sql += "ORDER BY section_created_date ASC ";
			break;
		}
		sql += "LIMIT " + at + ", " + total;

		return this.gets(sql);
	}

	public static void main(String[] args) {
		// Tạo bộ quản lý kết nối
		ConnectionPool cp = new ConnectionPoolImpl();

		// Tạo đối tượng thực thi chức năng của Interface
		Section s = new SectionImpl(cp);

		// Tạo đối tượng lưu trữ thông tin
		SectionObject nSec = new SectionObject();
		nSec.setSection_id((short) 14);
		nSec.setSection_name("Nguyễn Văn C");
		nSec.setSection_notes("Đây là test sửa Section");
		nSec.setSection_created_date("23/10/2202");
		nSec.setSection_manager_id(20);
		nSec.setSection_created_author_id(8);

		// Thực hiện thêm mới
		boolean result = s.editSection(nSec);

		// Thông báo
		if (!result) {
			System.out.println("\n---------------KHÔNG THÀNH CÔNG----------------\n");
		}

		// Lấy tập kết quả
		ResultSet rs = s.getSections(null, 0, (byte) 20);

		// Trả về kết nối
		s.releaseConnection();

		// In kết quả

		String row;

		if (rs != null) {
			try {
				while (rs.next()) {
					row = "ID: " + rs.getShort("section_id");
					row += "\t\tNAME: " + rs.getString("section_name");
					row += "\t\tNOTES: " + rs.getString("section_notes");
					row += "\t\tCREATE DATE: " + rs.getString("section_created_date");

					System.out.println(row);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
