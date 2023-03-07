package jsoft.ads.article.section;

import jsoft.*;
import jsoft.objects.*;

import java.util.*;

public class SectionControl {
	private SectionModel sm;

	public SectionControl(ConnectionPool cp) {
		this.sm = new SectionModel(cp);
	}

	protected void finalize() throws Throwable {
		this.sm = null;
	}

	public ConnectionPool getCP() {
		return this.sm.getCP();
	}

	public void releaseConnection() {
		this.sm.releaseConnection();
	}

	// ----------------------------------
	public boolean addSection(SectionObject item) {
		return this.sm.addSection(item);
	}

	public boolean editSection(SectionObject item) {
		return this.sm.editSection(item);
	}

	public boolean delSection(SectionObject item) {
		return this.sm.delSection(item);
	}

	// ----------------------------------
	public SectionObject getSectionObject(short id) {
		return this.sm.getSectionObject(id);
	}

	public String viewSections(SectionObject similer, SectionSort ss, short page, byte total) {
		ArrayList<SectionObject> items = this.sm.getSectionObjects(similer, ss, page, total);

		return SectionLibrary.viewSections(items);
	}

	public static void main(String[] args) {
		// Tạo đối tượng thực thi chức năng
		SectionControl sc = new SectionControl(null);

		// Lấy cấu trúc trình bày
		String view = sc.viewSections(null, SectionSort.NAME, (short) 1, (byte) 20);

		//Trả về kết nối
		sc.releaseConnection();
		
		// Hiển thị
		System.out.print(view);
	}
}
