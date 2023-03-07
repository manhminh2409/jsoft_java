package jsoft.ads.article.category;

import jsoft.*;
import jsoft.objects.*;

import java.util.*;

public class CategoryControl {
	private CategoryModel cm;

	public CategoryControl(ConnectionPool cp) {
		this.cm = new CategoryModel(cp);
	}

	protected void finalize() throws Throwable {
		this.cm = null;
	}

	public ConnectionPool getCP() {
		return this.cm.getCP();
	}

	public void releaseConnection() {
		this.cm.releaseConnection();
	}

	// -------------------------------------------
	public boolean addCategory(CategoryObject item) {
		return this.cm.addCategory(item);
	}

	public boolean editCategory(CategoryObject item) {
		return this.cm.editCategory(item);
	}

	public boolean delCategory(CategoryObject item) {
		return this.cm.delCategory(item);
	}

	// ----------------------------------------------
	public CategoryObject getCategoryObject(int id) {
		return this.cm.getCategoryObject(id);
	}

	public String viewCategories(CategoryObject similer, CategorySort cs, short page, byte total) {
		// Lấy dữ liệu từ Category Model

		ArrayList<CategoryObject> items = this.cm.getCategoryObjects(similer, cs, page, total);

		return CategoryLibrary.viewCategories(items);
	}

	public static void main(String[] args) {
		// Tạo đối tượng thực thi chức năng
		CategoryControl cc = new CategoryControl(null);

		// Lấy cấu trúc trình bày
		String view = cc.viewCategories(null, CategorySort.ID, (short) 1, (byte) 15);

		// Trả về kết nối
		cc.releaseConnection();

		// Hiển thị
		System.out.print(view);
	}

}
