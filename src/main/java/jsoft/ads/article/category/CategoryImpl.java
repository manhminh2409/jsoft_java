package jsoft.ads.article.category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.ads.article.section.SectionImpl;
import jsoft.objects.CategoryObject;

public class CategoryImpl extends SectionImpl implements Category {

	public CategoryImpl(ConnectionPool cp) {
		super("Category", cp);
		// TODO Auto-generated constructor stub
	}

	public CategoryImpl(String objectName, ConnectionPool cp) {
		// TODO Auto-generated constructor stub
		super(objectName, cp);
	}

	@Override
	public boolean addCategory(CategoryObject item) {
		// TODO Auto-generated method stub

		if (this.IsExisting(item)) {
			return false;
		}

		String sql = "INSERT INTO tblcategory( ";
		sql += "";
		sql += "category_name, category_section_id, category_notes, ";
		sql += "category_created_date, category_created_author_id, ";
		sql += "category_last_modified, category_manager_id, category_enable, ";
		sql += "category_delete, category_image, category_name_en, category_language ";
		sql += ") ";
		sql += "VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ";

		// Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			// Truyền giá trị
			pre.setString(1, item.getCategory_name());
			pre.setInt(2, item.getCategory_section_id());
			pre.setString(3, item.getCategory_notes());
			pre.setString(4, item.getCategory_created_date());
			pre.setInt(5, item.getCategory_created_author_id());
			pre.setString(6, item.getCategory_last_modified());
			pre.setInt(7, item.getCategory_manager_id());
			pre.setBoolean(8, item.isCategory_enable());
			pre.setBoolean(9, item.isCategory_delete());
			pre.setString(10, item.getCategory_image());
			pre.setString(11, item.getCategory_name_en());
			pre.setInt(12, item.getCategory_language());

			return this.add(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Phương thức ràng buộc duy nhất bằng mã nguồn <br>
	 * <u><i>Update: 16/10/2022</i></u></br>
	 * 
	 * @param item
	 * @return
	 * @author Mạnh
	 */
	private boolean IsExisting(CategoryObject item) {
		boolean flag = false;

		String sql = "SELECT category_id FROM tblcategory WHERE category_name = '" + item.getCategory_name() + "' ";

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
	public boolean editCategory(CategoryObject item) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tblcategory SET ";
		sql += "";
		sql += "category_name = ?, category_notes= ?, ";
		sql += "category_last_modified= ?,";
		sql += "category_manager_id = ?, category_enable = ?, ";
		sql += "category_delete= ?, category_image= ?, category_name_en= ?, category_language = ? ";
		sql += "WHERE category_id = ? ";

		// Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			// Truyền giá trị
			pre.setString(1, item.getCategory_name());

			pre.setString(2, item.getCategory_notes());

			pre.setString(3, item.getCategory_last_modified());
			pre.setInt(4, item.getCategory_manager_id());
			pre.setBoolean(5, item.isCategory_enable());
			pre.setBoolean(6, item.isCategory_delete());
			pre.setString(7, item.getCategory_image());
			pre.setString(8, item.getCategory_name_en());
			pre.setInt(9, item.getCategory_language());

			pre.setInt(10, item.getCategory_id());

			return this.edit(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delCategory(CategoryObject item) {
		// TODO Auto-generated method stub

		if (!IsEmpty(item)) {
			return false;
		}
		String sql = "DELETE FROM tblcategory WHERE category_id = ? ";

		// Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setInt(1, item.getCategory_id());

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
	private boolean IsEmpty(CategoryObject item) {
		boolean flag = true;

		String sql1 = "SELECT article_id FROM tblarticle WHERE article_category_id ='" + item.getCategory_id() + "'";

		ResultSet rs1 = this.gets(sql1);

		if (rs1 != null) {
			try {
				if (rs1.next()) {
					flag = false;
				}

				rs1.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public ResultSet getCategory(int id) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM tblcategory ";
		sql += "LEFT JOIN  tblsection ON category_section_id = section_id ";
		sql += "WHERE category_id = ? ";
		sql += "";

		return this.get(sql, id);
	}

	@Override
	public ResultSet getCategories(CategoryObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		return this.getCategories(similar, CategorySort.NAME, at, total);
	}

	@Override
	public ResultSet getCategories(CategoryObject similar, CategorySort cs, int at, byte total) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblcategory c ";
		sql += "LEFT JOIN  tblsection s ON c.category_section_id = s.section_id ";
		sql += "LEFT JOIN  tbluser u ON c.category_created_author_id = u.user_id ";

		sql += "";

		switch (cs) {
		case ID:
			sql += "ORDER BY category_id ASC ";
			break;
		case NAME:
			sql += "ORDER BY category_name ASC ";
			break;
		case MANAGER:
			sql += "ORDER BY category_manager_id ASC ";
			break;
		case AUTHOR:
			sql += "ORDER BY category_created_author_id ASC ";
			break;
		}

		sql += "LIMIT " + at + ", " + total;

		return this.gets(sql);
	}

	public static void main(String[] args) {
		// Tạo bộ quản lý kết nối
		ConnectionPool cp = new ConnectionPoolImpl();

		// Tạo đối tượng thực thi chức năng Interface
		Category c = new CategoryImpl(cp);

		// Tạo đối tượng lưu trữ thông tin
		CategoryObject nCat = new CategoryObject();
		nCat.setCategory_name("md2409");
		nCat.setCategory_notes("Đây là test add category");
		nCat.setCategory_created_date("17-10-2022");
		nCat.setCategory_created_author_id(10);
		nCat.setCategory_section_id((short) 12);

		// Thực hiện thêm mới
		boolean result = c.addCategory(nCat);

		// Thông báo
		if (!result) {
			System.out.println("\n---------------KHÔNG THÀNH CÔNG----------------\n");
		}
		// Lấy tập kết quả
		ResultSet rs = c.getCategories(null, 0, (byte) 30);

		// Trả về kết nối
		c.releaseConnection();

		// Duyệt và in ra màn hình
		String row;

		if (rs != null) {
			try {
				while (rs.next()) {
					row = "ID: " + rs.getInt("category_id");
					row += "\t\tNAME: " + rs.getString("category_name");
					row += "\t\tNOTES: " + rs.getString("category_notes");
					System.out.println(row);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
