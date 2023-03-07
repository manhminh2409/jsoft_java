package jsoft.ads.article;

import jsoft.*;
import jsoft.objects.*;

import java.util.*;

public class ArticleControl {

	private ArticleModel am;

	public ArticleControl(ConnectionPool cp) {
		this.am = new ArticleModel(cp);
	}

	protected void finalize() throws Throwable {
		this.am = null;
	}
	
	public ConnectionPool getCP() {
		return this.am.getCP();
	}

	public void releaseConnection() {
		this.am.releaseConnection();
	}

	// ---------------------------------------
	public boolean addArticle(ArticleObject item) {
		return this.am.addArticle(item);
	}

	public boolean editArticle(ArticleObject item) {
		return this.am.editArticle(item);
	}

	public boolean delArticle(ArticleObject item) {
		return this.am.delArticle(item);
	}

	// ---------------------------------------------

	public ArticleObject getArticleObject(int id) {
		return this.am.getArticleObject(id);
	}

	public String viewArticles(ArticleObject similer, ArticleSort as, short page, byte total) {
		ArrayList<ArticleObject> items = this.am.getArticleObjects(similer, as, page, total);

		return ArticleLibrary.viewArticles(items);
	}

	public static void main(String[] args) {

		// Toạ đối tượng thực thi chức năng
		ArticleControl ac = new ArticleControl(null);

		// Lấy cấu trúc trình bày
		String view = ac.viewArticles(null, ArticleSort.VISITED, (short) 2, (byte) 10);

		//Trả về kết nối
		ac.releaseConnection();
		
		// Hiển thị
		System.out.print(view);
	}
}
