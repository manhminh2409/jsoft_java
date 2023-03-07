package jsoft.ads.user;

import jsoft.library.Utilities;
import jsoft.objects.*;
import java.util.*;

public class UserLibrary {
	public static String viewUsers(ArrayList<UserObject> items, UserObject user) {
		String tmp = "";
		tmp += "<div class=\"card\">";
		tmp += "<div class=\"card-body\">";
		tmp += "<h5 class=\"card-title\">Users <span> | List </span></h5>";
		tmp += "";
		tmp += "<!-- Table with stripped rows -->";
		tmp += "<table class=\"table table-sm table-striped\">";
		tmp += "<thead>";
		tmp += "<tr>";
		tmp += "<th scope=\"col\">ID</th>";
		tmp += "<th scope=\"col\">Date created</th>";
		tmp += "<th scope=\"col\">Full name</th>";
		tmp += "<th scope=\"col\">Email</th>";
		tmp += "<th scope=\"col\">Logied</th>";
		tmp += "<th scope=\"col\" colspan=\"2\" class= \"text-center\">Option</th>";
		tmp += "</tr>";
		tmp += "</thead>";
		tmp += "";

		tmp += "<tbody>";

		if (items.size() > 0) {

			for (UserObject item : items) {
				tmp += "<tr>";
				tmp += "<th scope=\"row\">" + item.getUser_id() + "</th>";
				tmp += "<td>" + item.getUser_created_date() + "</td>";
				tmp += "<td>" + item.getUser_fullname() + " <span>(" + item.getUser_name() + ")</span></td>";
				tmp += "<td>" + item.getUser_email() + "</td>";
				tmp += "<td>" + item.getUser_logined() + "</td>";
				tmp += "<td class=\"text-center\"><a href=\"/adv/user/profiles?id=" + item.getUser_id()
						+ "&t=o\" class=\"btn btn-primary btn-sm\" style=\"--bs-btn-padding-x: 0.35rem;\"><i class=\"fa-solid fa-user-pen\" ></i></a></td>";
				if (item.getUser_id() != user.getUser_id()) {
					tmp += "<td class=\"text-center\"><a href=\"#\" class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#model"
							+ item.getUser_id() + "\"><i class=\"fa-solid fa-trash-can\"></i></a></td>";
				} else {
					tmp += "<td class=\"text-center\"><a href=\"#\" class=\"btn btn-secondary btn-sm\" disabled"
							+ item.getUser_id() + "\"><i class=\"fa-solid fa-trash-can\"></i></a></td>";
				}

				tmp += "</tr>";

				tmp += UserLibrary.getModal(item);
			}
		}
		tmp += "</tbody>";

		tmp += "</table>";
		tmp += "";
		tmp += "</div>";
		tmp += "</div>";

		// Chèn biểu đồ đồ
		tmp += UserLibrary.viewChart(items);

		return tmp;
	}

	private static String getModal(UserObject item) {
		String tmp = "";
		tmp += "<!-- Modal -->";
		tmp += "<div class=\"modal fade\" id=\"model" + item.getUser_id()
				+ "\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">";
		tmp += "<div class=\"modal-dialog\">";
		tmp += "<div class=\"modal-content\">";
		tmp += "<div class=\"modal-header\">";
		tmp += "<h1 class=\"modal-title fs-5\" id=\"exampleModalLabel\"><i class=\"fa-solid fa-triangle-exclamation\"></i> Xác nhận xoá</h1>";
		tmp += "<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>";
		tmp += "</div>";
		tmp += "<div class=\"modal-body\">";
		tmp += "Bạn có chắc chắn xoá tài khoản <strong>" + item.getUser_fullname() + "</strong> (" + item.getUser_name()
				+ ") này<br/>";
		tmp += "Nếu không xoá được do người sử dụng này còn có liên quan tới nội dung khác";
		tmp += "</div>";
		tmp += "<div class=\"modal-footer\">";
		tmp += "<a href=\"/adv/user/del?id=" + item.getUser_id()
				+ "\" type=\"button\" class=\"btn btn-danger\">Xoá</a>";
		tmp += "<button type=\"button\" class=\"btn btn-primary\" data-bs-dismiss=\"modal\">Thoát</button>";
		tmp += "</div>";
		tmp += "</div>";
		tmp += "</div>";
		tmp += "</div>";
		return tmp;
	}

	private static String viewChart(ArrayList<UserObject> items) {

		String tmp = "";

		String datas = "";
		String categories = "";
		if (!items.isEmpty()) {
			for (int i = 0; i < items.size(); i++) {
				datas += items.get(i).getUser_logined() + ",";
				categories += "'" + Utilities.decodeFromHtml(items.get(i).getUser_fullname()) + "',";
			}
			// loại bỏ kí tự cuối cùng
			datas = datas.substring(0, datas.length() - 1);
			categories = categories.substring(0, categories.length() - 1);
		}
		tmp += "<div class=\"card\">";
		tmp += "<div class=\"card-body\">";
		tmp += "<h5 class=\"card-title\">User <span>| Chart logined</span></h5>";
		tmp += "";
		tmp += "<!-- Bar Chart -->";
		tmp += "<div id=\"barChart\"></div>";
		tmp += "";
		tmp += "<script>";
		tmp += "document.addEventListener(\"DOMContentLoaded\", () => {";
		tmp += "new ApexCharts(document.querySelector(\"#barChart\"), {";
		tmp += "series: [{";
		tmp += "data: [" + datas + "]";
		tmp += "}],";
		tmp += "chart: {";
		tmp += "type: 'bar',";
		tmp += "height: 500";
		tmp += "},";
		tmp += "plotOptions: {";
		tmp += "bar: {";
		tmp += "borderRadius: 4,";
		tmp += "horizontal: true,";
		tmp += "}";
		tmp += "},";
		tmp += "dataLabels: {";
		tmp += "enabled: false";
		tmp += "},";
		tmp += "xaxis: {";
		tmp += "categories: [" + categories + "],";
		tmp += "}";
		tmp += "}).render();";
		tmp += "});";
		tmp += "</script>";
		tmp += "<!-- End Bar Chart -->";
		tmp += "";
		tmp += "</div>";
		tmp += "</div>";

		return tmp;
	}

}
