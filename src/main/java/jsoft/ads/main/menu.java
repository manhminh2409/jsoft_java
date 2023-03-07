package jsoft.ads.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class menu
 */
@WebServlet("/menu")
public class menu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Khai báo kiểu nội dung xuất về trình khách(client)
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public menu() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Xác định kiểu nội dung xuất về trình khách
		response.setContentType(CONTENT_TYPE);

		// Tạo đối tượng xuất nội dung về trình khách
		PrintWriter out = response.getWriter();

		// Xác định vị trí
		String pos = request.getRequestURI().substring(5); // /adv/...

		int at = pos.indexOf('/');
		String menu = pos, submenu = "";
		HashMap<String, String> collapses = new HashMap<>();
		HashMap<String, String> shows = new HashMap<>();
		HashMap<String, String> actives = new HashMap<>();

		if (at != -1) {
			menu = pos.substring(0, at);

			shows.put(menu, "show");
			collapses.put(menu, "");

			// Xác định menu con
			submenu = pos.substring(at + 1);
			if (menu.equalsIgnoreCase("user")) {
				if (submenu.contains("add")) {
					actives.put("add", " active ");
				} else if (submenu.contains("profiles")) {
					actives.put("profiles", " active ");
				} else if (submenu.contains("view")) {
					actives.put("view", " active ");
				}
			}

		} else {
			collapses.put("view", "");
		}

		out.print("<!-- ======= Sidebar ======= -->");
		out.print("<aside id=\"sidebar\" class=\"sidebar\">");
		out.print("");
		out.print("<ul class=\"sidebar-nav\" id=\"sidebar-nav\">");
		out.print("");
		out.print("<li class=\"nav-item\">");
		out.print("<a class=\"nav-link " + collapses.getOrDefault("view", "collapsed") + " \" href=\"/adv/view\" >");
		out.print("<i class=\"bi bi-grid\"></i>");
		out.print("<span>Dashboard</span>");
		out.print("</a>");
		out.print("</li><!-- End Dashboard Nav -->");
		out.print("");
		out.print("<li class=\"nav-item\">");
		out.print("<a class=\"nav-link " + collapses.getOrDefault("user", "collapsed")
				+ "\" data-bs-target=\"#user-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.print(
				"<i class=\"fa-solid fa-users-viewfinder\" style=\"width: 16px;\"></i><span>Users</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.print("</a>");
		out.print("<ul id=\"user-nav\" class=\"nav-content collapse " + shows.getOrDefault("user", "")
				+ "\" data-bs-parent=\"#sidebar-nav\">");

		out.print("<li>");
		out.print("<a href=\"/adv/user/view\" class=\"" + actives.getOrDefault("view", "") + "\">");
		out.print("<i class=\"bi bi-circle\"></i><span>View users</span>");
		out.print("</a>");
		out.print("</li>");

		out.print("<li>");
		out.print("<a href=\"/adv/user/add\" class=\"" + actives.getOrDefault("add", "") + "\">");
		out.print("<i class=\"bi bi-circle\"></i><span>New user</span>");
		out.print("</a>");
		out.print("</li>");

		out.print("<li>");
		out.print("<a href=\"/adv/user/profiles\" class=\"" + actives.getOrDefault("profiles", "") + "\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Profile user</span>");
		out.print("</a>");
		out.print("</li>");

		out.print("<li>");
		out.print("<a href=\"/adv/user/...\" class=\"" + actives.getOrDefault("", "") + "\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Template</span>");
		out.print("</a>");
		out.print("</li>");

		out.print("</ul>");
		out.print("</li><!-- End User Nav -->");

		out.print("<li class=\"nav-item\">");
		out.print(
				"<a class=\"nav-link "+collapses.getOrDefault("section", "collapsed")+"\" data-bs-target=\"#section-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.print(
				"<i class=\"bi bi-journal-text\"></i><span>Sections</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.print("</a>");
		out.print("<ul id=\"section-nav\" class=\"nav-content collapse "+shows.getOrDefault("section", "")+"\" data-bs-parent=\"#sidebar-nav\">");
		out.print("<li>");
		out.print("<a href=\"/adv/section/view\" class=\""+actives.getOrDefault("view", "")+"\">");
		out.print("<i class=\"bi bi-circle\"></i><span>View sections</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"/adv/section/add\">");
		out.print("<i class=\"bi bi-circle\"></i><span>New section</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"/adv/section/...\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Template</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("</ul>");
		out.print("</li><!-- End Section Nav -->");

		out.print("<li class=\"nav-item\">");
		out.print(
				"<a class=\"nav-link collapsed\" data-bs-target=\"#category-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.print(
				"<i class=\"bi bi-layout-text-window-reverse\"></i><span>Categories</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.print("</a>");
		out.print("<ul id=\"category-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.print("<li>");
		out.print("<a href=\"/adv/category/view\">");
		out.print("<i class=\"bi bi-circle\"></i><span>View categories</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"/adv/category/add\">");
		out.print("<i class=\"bi bi-circle\"></i><span>New category</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"/adv/category/...\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Mẫu...</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("</ul>");
		out.print("</li><!-- End Category Nav -->");

		out.print("<li class=\"nav-item\">");
		out.print(
				"<a class=\"nav-link collapsed\" data-bs-target=\"#article-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.print(
				"<i class=\"fa-regular fa-newspaper\"></i><span>Articles</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.print("</a>");
		out.print("<ul id=\"article-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.print("<li>");
		out.print("<a href=\"/adv/article/view\">");
		out.print("<i class=\"bi bi-circle\"></i><span>View artilces</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"/adv/article/add\">");
		out.print("<i class=\"bi bi-circle\"></i><span>New article</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"/adv/article/...\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Template</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("</ul>");
		out.print("</li><!-- End Article Nav -->");

		out.print("<li class=\"nav-item\">");
		out.print(
				"<a class=\"nav-link collapsed\" data-bs-target=\"#charts-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.print("<i class=\"bi bi-bar-chart\"></i><span>Charts</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.print("</a>");
		out.print("<ul id=\"charts-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.print("<li>");
		out.print("<a href=\"charts-chartjs.html\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Chart.js</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"charts-apexcharts.html\">");
		out.print("<i class=\"bi bi-circle\"></i><span>ApexCharts</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"charts-echarts.html\">");
		out.print("<i class=\"bi bi-circle\"></i><span>ECharts</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("</ul>");
		out.print("</li><!-- End Charts Nav -->");
		out.print("");
		out.print("<li class=\"nav-item\">");
		out.print(
				"<a class=\"nav-link collapsed\" data-bs-target=\"#icons-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.print("<i class=\"bi bi-gem\"></i><span>Icons</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.print("</a>");
		out.print("<ul id=\"icons-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.print("<li>");
		out.print("<a href=\"icons-bootstrap.html\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Bootstrap Icons</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"icons-remix.html\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Remix Icons</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("<li>");
		out.print("<a href=\"icons-boxicons.html\">");
		out.print("<i class=\"bi bi-circle\"></i><span>Boxicons</span>");
		out.print("</a>");
		out.print("</li>");
		out.print("</ul>");
		out.print("</li><!-- End Icons Nav -->");
		out.print("");
		out.print("<li class=\"nav-heading\">Pages</li>");
		out.print("");
		out.print("<li class=\"nav-item\">");
		out.print("<a class=\"nav-link collapsed\" href=\"users-profile.html\">");
		out.print("<i class=\"bi bi-person\"></i>");
		out.print("<span>Profile</span>");
		out.print("</a>");
		out.print("</li><!-- End Profile Page Nav -->");
		out.print("");
		out.print("<li class=\"nav-item\">");
		out.print("<a class=\"nav-link collapsed\" href=\"pages-faq.html\">");
		out.print("<i class=\"bi bi-question-circle\"></i>");
		out.print("<span>F.A.Q</span>");
		out.print("</a>");
		out.print("</li><!-- End F.A.Q Page Nav -->");
		out.print("");
		out.print("<li class=\"nav-item\">");
		out.print("<a class=\"nav-link collapsed\" href=\"pages-contact.html\">");
		out.print("<i class=\"bi bi-envelope\"></i>");
		out.print("<span>Contact</span>");
		out.print("</a>");
		out.print("</li><!-- End Contact Page Nav -->");
		out.print("");
		out.print("<li class=\"nav-item\">");
		out.print("<a class=\"nav-link collapsed\" href=\"pages-register.html\">");
		out.print("<i class=\"bi bi-card-list\"></i>");
		out.print("<span>Register</span>");
		out.print("</a>");
		out.print("</li><!-- End Register Page Nav -->");
		out.print("");
		out.print("<li class=\"nav-item\">");
		out.print("<a class=\"nav-link collapsed\" href=\"pages-login.html\">");
		out.print("<i class=\"bi bi-box-arrow-in-right\"></i>");
		out.print("<span>Login</span>");
		out.print("</a>");
		out.print("</li><!-- End Login Page Nav -->");
		out.print("");
		out.print("<li class=\"nav-item\">");
		out.print("<a class=\"nav-link collapsed\" href=\"pages-error-404.html\">");
		out.print("<i class=\"bi bi-dash-circle\"></i>");
		out.print("<span>Error 404</span>");
		out.print("</a>");
		out.print("</li><!-- End Error 404 Page Nav -->");
		out.print("");
		out.print("<li class=\"nav-item\">");
		out.print("<a class=\"nav-link collapsed\" href=\"pages-blank.html\">");
		out.print("<i class=\"bi bi-file-earmark\"></i>");
		out.print("<span>Blank</span>");
		out.print("</a>");
		out.print("</li><!-- End Blank Page Nav -->");
		out.print("");
		out.print("</ul>");
		out.print("");
		out.print("</aside><!-- End Sidebar-->");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
