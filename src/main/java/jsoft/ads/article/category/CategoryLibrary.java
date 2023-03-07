package jsoft.ads.article.category;

import jsoft.objects.*;
import jsoft.library.*;

import java.util.*;

public class CategoryLibrary {
	public static String viewCategories(ArrayList<CategoryObject> items) {
		String tmp = "";

		tmp += "<div class=\"card\">\n";
		tmp += "<div class=\"card-body\">\n";
		tmp += "<h5 class=\"card-title\">Categories <span> | List</span></h5>\n";
		tmp += "<table class=\"table table-sm table-striped\">\n";
		tmp += "<thead>\n";
		tmp += "<tr>";
		tmp += "<th scope=\"col\">ID</th>";
		tmp += "<th scope=\"col\">Category name</th>";
		tmp += "<th scope=\"col\">Section name</th>";
		tmp += "<th scope=\"col\">Date created</th>";
		tmp += "<th scope=\"col\">Creator</th>";
		tmp += "<th scope=\"col\">Last modified</th>";
		tmp += "<th scope=\"col\">Manager</th>";
		tmp += "<th scope=\"col\" colspan=\"2\" class=\"text-center\">Option</th>";
		tmp += "</tr>";
		tmp += "</thead>\n";
		tmp += "<tbody>\n";

		for (CategoryObject item : items) {
			tmp += "<tr>\n";
			tmp += "<th scope=\"row\">" + item.getCategory_id() + "</th>";
			tmp += "<td>" + item.getCategory_name() + "</td>";
			tmp += "<td>" + item.getSection_name() + "</td>";
			tmp += "<td>" + item.getCategory_created_date() + "</td>";
			tmp += "<td>" + Utilities.getInfo(item.getCategory_notes(), (byte) 1) + "</td>";
			tmp += "<td>" + item.getCategory_last_modified() + "</td>";
			tmp += "<td>" + item.getCategory_manager_id() + "</td>";
			tmp += "<td class=\"text-center\"><a href=\"#\" class=\"btn btn-primary btn-sm\"><i class=\"fa-regular fa-pen-to-square\"></i></a></td>";
			tmp += "<td class=\"text-center\"><a href=\"#\" class=\"btn btn-danger btn-sm\"><i class=\"fa-solid fa-trash-can\"></i></a></td>";
			tmp += "</tr>\n";
		}

		tmp += "</tbody>\n";
		tmp += "</table>";
		tmp += "<!-- End small tables -->";
		return tmp;

	}

}
