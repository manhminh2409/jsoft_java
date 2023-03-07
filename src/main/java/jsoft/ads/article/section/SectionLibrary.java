package jsoft.ads.article.section;

import jsoft.objects.*;
import jsoft.library.*;

import java.util.*;

public class SectionLibrary {

	public static String viewSections(ArrayList<SectionObject> items) {
		String tmp = "";

		tmp += "<div class=\"card\">\n";
		tmp += "<div class=\"card-body\">\n";
		tmp += "<h5 class=\"card-title\">Sections<span>| List</span></h5>\n";
		tmp += "";
		tmp += "<table class=\"table table-striped\">\n";
		tmp += "<thead>\n";
		tmp += "<tr>";
		tmp += "<th scope=\"col\">ID</th>";
		tmp += "<th scope=\"col\">Section name</th>";
		tmp += "<th scope=\"col\">Notes</th>";
		tmp += "<th scope=\"col\">Date created</th>";
		tmp += "<th scope=\"col\">Manager</th>";
		tmp += "<th scope=\"col\">Creator</th>";
		tmp += "<th scope=\"col\" colspan=\"2\" class=\"text-center\">Option</th>";
		tmp += "</tr>";
		tmp += "</thead>\n";
		tmp += "";

		tmp += "<tbody>\n";

		for (SectionObject item : items) {
			tmp += "<tr>\n";
			tmp += "<th scope=\"row\">" + item.getSection_id() + "</th>";
			tmp += "<td>" + item.getSection_name() + "</td>";
			tmp += "<td>" + Utilities.getInfo(item.getSection_notes(), (byte) 0) + "</td>";
			tmp += "<td>" + item.getSection_created_date() + "</td>";
			tmp += "<td>" + item.getSection_manager_id() + "</td>";
			tmp += "<td>" + Utilities.getInfo(item.getSection_notes(), (byte) 1) + "</td>";
			tmp += "<td class=\"text-center\"><a href=\"#\" class=\"btn btn-primary btn-sm\"><i class=\"fa-regular fa-pen-to-square\"></i></a></td>";
			tmp += "<td class=\"text-center\"><a href=\"#\" class=\"btn btn-danger btn-sm\"><i class=\"fa-solid fa-trash-can\"></i></a></td>";
			tmp += "</tr>\n";
		}

		tmp += "</tbody>\n";

		tmp += "</table>";
		tmp += "";
		tmp += "</div>";
		tmp += "</div>";
		return tmp;
	}

	
}
