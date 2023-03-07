package jsoft.ads.article.category;

import java.sql.ResultSet;

import jsoft.ShareControl;
import jsoft.objects.CategoryObject;

public interface Category extends ShareControl {
	public boolean addCategory(CategoryObject item);

	public boolean editCategory(CategoryObject item);

	public boolean delCategory(CategoryObject item);

	public ResultSet getCategory(int id);

	public ResultSet getCategories(CategoryObject similar, int at, byte total);
	
	public ResultSet getCategories(CategoryObject similar,CategorySort cs, int at, byte total);
}
