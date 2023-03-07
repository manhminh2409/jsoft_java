package jsoft.ads.article;

import jsoft.library.Utilities;
import jsoft.objects.*;
import java.util.*;

public class ArticleLibrary {

	public static String viewArticles(ArrayList<ArticleObject> items) {

		String tmp = "";

		tmp += "<div class=\"card\">\n";
		tmp += "<div class=\"card-body\">\n";
		tmp += "<h5 class=\"card-title\">Articles <span>| List</span></h5>\n";
		tmp += "";
		tmp += "<!-- Table with stripped rows -->\n";
		tmp += "<table class=\"table table-striped align-items-center\">\n";
		tmp += "<thead>\n";
		tmp += "<tr>";
		tmp += "<th scope=\"col\">ID</th>";
		tmp += "<th scope=\"col\"></th>";
		tmp += "<th scope=\"col\">Title</th>";
		tmp += "<th scope=\"col\">Category name</th>";
		tmp += "<th scope=\"col\">Section name</th>";
		tmp += "<th scope=\"col\">Visited</th>";
		tmp += "<th scope=\"col\">Creator</th>";
		tmp += "<th scope=\"col\" colspan=\"2\">Option</th>";
		tmp += "</tr>";
		tmp += "</thead>";
		tmp += "";

		tmp += "<tbody>";

		for (ArticleObject item : items) {
			tmp += "<tr>\n";
			tmp += "<th scope=\"row\">" + item.getArticle_id() + "</th>";
			tmp += "<td><img src=\"" + item.getArticle_image()
					+ "\" alt=\"\" class=\"rounded\" width=\"75px\" height=\"75px\"></td>";
			tmp += "<td>" + item.getArticle_title() + "</td>";
			tmp += "<td>" + Utilities.getInfo(item.getArticle_title_en(), (byte) 1) + "</td>";
			tmp += "<td>" + Utilities.getInfo(item.getArticle_author_name(), (byte) 1) + "</td>";
			tmp += "<td>" + item.getArticle_visited() + "</td>";
			tmp += "<td>" + Utilities.getInfo(item.getArticle_author_name(), (byte) 0) + "</td>";
			tmp += "<td class=\"text-center\"><a href=\"#\" class=\"btn btn-primary btn-sm\"><i class=\"fa-regular fa-pen-to-square\"></i></a></td>";
			tmp += "<td class=\"text-center\"><a href=\"#\" class=\"btn btn-danger btn-sm\"><i class=\"fa-solid fa-trash-can\"></i></a></td>";
			tmp += "</tr>";
		}

		tmp += "</tbody>";

		tmp += "</table>";
		tmp += "";
		tmp += "</div>";
		tmp += "</div>";
		return tmp;
	}
}
