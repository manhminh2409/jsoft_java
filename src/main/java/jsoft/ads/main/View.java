package jsoft.ads.main;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import jsoft.objects.*;

/**
 * Servlet implementation class View
 */
@WebServlet("/view")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Khai báo kiểu nội dung xuất về trình khách(client)
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public View() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Tìm thông tin đăng nhập trong phiên làm việc
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");

		if (user != null) {
			view(request, response);
		} else {
			response.sendRedirect("/adv/user/login");
		}
	}

	protected void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Xác định kiểu nội dung xuất về trình khách
		response.setContentType(CONTENT_TYPE);

		// Tạo đối tượng xuất nội dung về trình khách
		PrintWriter out = response.getWriter();

		
		
		// Tham chiếu servlet header thông qua đối tượng RequestDispatcher
		RequestDispatcher header = request.getRequestDispatcher("/header");
		if (header != null) {
			header.include(request, response);
		}


		out.print("<main id=\"main\" class=\"main\">");
		out.print("");
		out.print("<div class=\"pagetitle\">");
		out.print("<h1>Dashboard</h1>");
		out.print("<nav>");
		out.print("<ol class=\"breadcrumb\">");
		out.print("<li class=\"breadcrumb-item\"><a href=\"/adv/view\">Home</a></li>");
		out.print("<li class=\"breadcrumb-item active\">Dashboard</li>");
		out.print("</ol>");
		out.print("</nav>");
		out.print("</div><!-- End Page Title -->");
		
		out.print("<section class=\"section dashboard\">");
		out.print("<div class=\"row\">");
		out.print("");
		out.print("<!-- Left side columns -->");
		out.print("<div class=\"col-lg-8\">");
		out.print("<div class=\"row\">");
		out.print("");
		out.print("<!-- Sales Card -->");
		out.print("<div class=\"col-xxl-4 col-md-6\">");
		out.print("<div class=\"card info-card sales-card\">");
		out.print("");
		out.print("<div class=\"filter\">");
		out.print("<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.print("<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.print("<li class=\"dropdown-header text-start\">");
		out.print("<h6>Filter</h6>");
		out.print("</li>");
		out.print("");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">Today</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>");
		out.print("</ul>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"card-body\">");
		out.print("<h5 class=\"card-title\">Sales <span>| Today</span></h5>");
		out.print("");
		out.print("<div class=\"d-flex align-items-center\">");
		out.print("<div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
		out.print("<i class=\"bi bi-cart\"></i>");
		out.print("</div>");
		out.print("<div class=\"ps-3\">");
		out.print("<h6>145</h6>");
		out.print("<span class=\"text-success small pt-1 fw-bold\">12%</span> <span class=\"text-muted small pt-2 ps-1\">increase</span>");
		out.print("");
		out.print("</div>");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("</div>");
		out.print("</div><!-- End Sales Card -->");
		out.print("");
		out.print("<!-- Revenue Card -->");
		out.print("<div class=\"col-xxl-4 col-md-6\">");
		out.print("<div class=\"card info-card revenue-card\">");
		out.print("");
		out.print("<div class=\"filter\">");
		out.print("<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.print("<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.print("<li class=\"dropdown-header text-start\">");
		out.print("<h6>Filter</h6>");
		out.print("</li>");
		out.print("");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">Today</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>");
		out.print("</ul>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"card-body\">");
		out.print("<h5 class=\"card-title\">Revenue <span>| This Month</span></h5>");
		out.print("");
		out.print("<div class=\"d-flex align-items-center\">");
		out.print("<div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
		out.print("<i class=\"bi bi-currency-dollar\"></i>");
		out.print("</div>");
		out.print("<div class=\"ps-3\">");
		out.print("<h6>$3,264</h6>");
		out.print("<span class=\"text-success small pt-1 fw-bold\">8%</span> <span class=\"text-muted small pt-2 ps-1\">increase</span>");
		out.print("");
		out.print("</div>");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("</div>");
		out.print("</div><!-- End Revenue Card -->");
		out.print("");
		out.print("<!-- Customers Card -->");
		out.print("<div class=\"col-xxl-4 col-xl-12\">");
		out.print("");
		out.print("<div class=\"card info-card customers-card\">");
		out.print("");
		out.print("<div class=\"filter\">");
		out.print("<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.print("<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.print("<li class=\"dropdown-header text-start\">");
		out.print("<h6>Filter</h6>");
		out.print("</li>");
		out.print("");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">Today</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>");
		out.print("</ul>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"card-body\">");
		out.print("<h5 class=\"card-title\">Customers <span>| This Year</span></h5>");
		out.print("");
		out.print("<div class=\"d-flex align-items-center\">");
		out.print("<div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
		out.print("<i class=\"bi bi-people\"></i>");
		out.print("</div>");
		out.print("<div class=\"ps-3\">");
		out.print("<h6>1244</h6>");
		out.print("<span class=\"text-danger small pt-1 fw-bold\">12%</span> <span class=\"text-muted small pt-2 ps-1\">decrease</span>");
		out.print("");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("</div><!-- End Customers Card -->");
		out.print("");
		out.print("<!-- Reports -->");
		out.print("<div class=\"col-12\">");
		out.print("<div class=\"card\">");
		out.print("");
		out.print("<div class=\"filter\">");
		out.print("<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.print("<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.print("<li class=\"dropdown-header text-start\">");
		out.print("<h6>Filter</h6>");
		out.print("</li>");
		out.print("");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">Today</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>");
		out.print("</ul>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"card-body\">");
		out.print("<h5 class=\"card-title\">Reports <span>/Today</span></h5>");
		out.print("");
		out.print("<!-- Line Chart -->");
		out.print("<div id=\"reportsChart\"></div>");
		out.print("");
		out.print("<script>");
		out.print("document.addEventListener(\"DOMContentLoaded\", () => {");
		out.print("new ApexCharts(document.querySelector(\"#reportsChart\"), {");
		out.print("series: [{");
		out.print("name: 'Sales',");
		out.print("data: [31, 40, 28, 51, 42, 82, 56],");
		out.print("}, {");
		out.print("name: 'Revenue',");
		out.print("data: [11, 32, 45, 32, 34, 52, 41]");
		out.print("}, {");
		out.print("name: 'Customers',");
		out.print("data: [15, 11, 32, 18, 9, 24, 11]");
		out.print("}],");
		out.print("chart: {");
		out.print("height: 350,");
		out.print("type: 'area',");
		out.print("toolbar: {");
		out.print("show: false");
		out.print("},");
		out.print("},");
		out.print("markers: {");
		out.print("size: 4");
		out.print("},");
		out.print("colors: ['#4154f1', '#2eca6a', '#ff771d'],");
		out.print("fill: {");
		out.print("type: \"gradient\",");
		out.print("gradient: {");
		out.print("shadeIntensity: 1,");
		out.print("opacityFrom: 0.3,");
		out.print("opacityTo: 0.4,");
		out.print("stops: [0, 90, 100]");
		out.print("}");
		out.print("},");
		out.print("dataLabels: {");
		out.print("enabled: false");
		out.print("},");
		out.print("stroke: {");
		out.print("curve: 'smooth',");
		out.print("width: 2");
		out.print("},");
		out.print("xaxis: {");
		out.print("type: 'datetime',");
		out.print("categories: [\"2018-09-19T00:00:00.000Z\", \"2018-09-19T01:30:00.000Z\", \"2018-09-19T02:30:00.000Z\", \"2018-09-19T03:30:00.000Z\", \"2018-09-19T04:30:00.000Z\", \"2018-09-19T05:30:00.000Z\", \"2018-09-19T06:30:00.000Z\"]");
		out.print("},");
		out.print("tooltip: {");
		out.print("x: {");
		out.print("format: 'dd/MM/yy HH:mm'");
		out.print("},");
		out.print("}");
		out.print("}).render();");
		out.print("});");
		out.print("</script>");
		out.print("<!-- End Line Chart -->");
		out.print("");
		out.print("</div>");
		out.print("");
		out.print("</div>");
		out.print("</div><!-- End Reports -->");
		out.print("");
		out.print("<!-- Recent Sales -->");
		out.print("<div class=\"col-12\">");
		out.print("<div class=\"card recent-sales overflow-auto\">");
		out.print("");
		out.print("<div class=\"filter\">");
		out.print("<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.print("<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.print("<li class=\"dropdown-header text-start\">");
		out.print("<h6>Filter</h6>");
		out.print("</li>");
		out.print("");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">Today</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>");
		out.print("</ul>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"card-body\">");
		out.print("<h5 class=\"card-title\">Recent Sales <span>| Today</span></h5>");
		out.print("");
		out.print("<table class=\"table table-borderless datatable\">");
		out.print("<thead>");
		out.print("<tr>");
		out.print("<th scope=\"col\">#</th>");
		out.print("<th scope=\"col\">Customer</th>");
		out.print("<th scope=\"col\">Product</th>");
		out.print("<th scope=\"col\">Price</th>");
		out.print("<th scope=\"col\">Status</th>");
		out.print("</tr>");
		out.print("</thead>");
		out.print("<tbody>");
		out.print("<tr>");
		out.print("<th scope=\"row\"><a href=\"#\">#2457</a></th>");
		out.print("<td>Brandon Jacob</td>");
		out.print("<td><a href=\"#\" class=\"text-primary\">At praesentium minu</a></td>");
		out.print("<td>$64</td>");
		out.print("<td><span class=\"badge bg-success\">Approved</span></td>");
		out.print("</tr>");
		out.print("<tr>");
		out.print("<th scope=\"row\"><a href=\"#\">#2147</a></th>");
		out.print("<td>Bridie Kessler</td>");
		out.print("<td><a href=\"#\" class=\"text-primary\">Blanditiis dolor omnis similique</a></td>");
		out.print("<td>$47</td>");
		out.print("<td><span class=\"badge bg-warning\">Pending</span></td>");
		out.print("</tr>");
		out.print("<tr>");
		out.print("<th scope=\"row\"><a href=\"#\">#2049</a></th>");
		out.print("<td>Ashleigh Langosh</td>");
		out.print("<td><a href=\"#\" class=\"text-primary\">At recusandae consectetur</a></td>");
		out.print("<td>$147</td>");
		out.print("<td><span class=\"badge bg-success\">Approved</span></td>");
		out.print("</tr>");
		out.print("<tr>");
		out.print("<th scope=\"row\"><a href=\"#\">#2644</a></th>");
		out.print("<td>Angus Grady</td>");
		out.print("<td><a href=\"#\" class=\"text-primar\">Ut voluptatem id earum et</a></td>");
		out.print("<td>$67</td>");
		out.print("<td><span class=\"badge bg-danger\">Rejected</span></td>");
		out.print("</tr>");
		out.print("<tr>");
		out.print("<th scope=\"row\"><a href=\"#\">#2644</a></th>");
		out.print("<td>Raheem Lehner</td>");
		out.print("<td><a href=\"#\" class=\"text-primary\">Sunt similique distinctio</a></td>");
		out.print("<td>$165</td>");
		out.print("<td><span class=\"badge bg-success\">Approved</span></td>");
		out.print("</tr>");
		out.print("</tbody>");
		out.print("</table>");
		out.print("");
		out.print("</div>");
		out.print("");
		out.print("</div>");
		out.print("</div><!-- End Recent Sales -->");
		out.print("");
		out.print("<!-- Top Selling -->");
		out.print("<div class=\"col-12\">");
		out.print("<div class=\"card top-selling overflow-auto\">");
		out.print("");
		out.print("<div class=\"filter\">");
		out.print("<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.print("<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.print("<li class=\"dropdown-header text-start\">");
		out.print("<h6>Filter</h6>");
		out.print("</li>");
		out.print("");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">Today</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>");
		out.print("</ul>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"card-body pb-0\">");
		out.print("<h5 class=\"card-title\">Top Selling <span>| Today</span></h5>");
		out.print("");
		out.print("<table class=\"table table-borderless\">");
		out.print("<thead>");
		out.print("<tr>");
		out.print("<th scope=\"col\">Preview</th>");
		out.print("<th scope=\"col\">Product</th>");
		out.print("<th scope=\"col\">Price</th>");
		out.print("<th scope=\"col\">Sold</th>");
		out.print("<th scope=\"col\">Revenue</th>");
		out.print("</tr>");
		out.print("</thead>");
		out.print("<tbody>");
		out.print("<tr>");
		out.print("<th scope=\"row\"><a href=\"#\"><img src=\"assets/img/product-1.jpg\" alt=\"\"></a></th>");
		out.print("<td><a href=\"#\" class=\"text-primary fw-bold\">Ut inventore ipsa voluptas nulla</a></td>");
		out.print("<td>$64</td>");
		out.print("<td class=\"fw-bold\">124</td>");
		out.print("<td>$5,828</td>");
		out.print("</tr>");
		out.print("<tr>");
		out.print("<th scope=\"row\"><a href=\"#\"><img src=\"assets/img/product-2.jpg\" alt=\"\"></a></th>");
		out.print("<td><a href=\"#\" class=\"text-primary fw-bold\">Exercitationem similique doloremque</a></td>");
		out.print("<td>$46</td>");
		out.print("<td class=\"fw-bold\">98</td>");
		out.print("<td>$4,508</td>");
		out.print("</tr>");
		out.print("<tr>");
		out.print("<th scope=\"row\"><a href=\"#\"><img src=\"assets/img/product-3.jpg\" alt=\"\"></a></th>");
		out.print("<td><a href=\"#\" class=\"text-primary fw-bold\">Doloribus nisi exercitationem</a></td>");
		out.print("<td>$59</td>");
		out.print("<td class=\"fw-bold\">74</td>");
		out.print("<td>$4,366</td>");
		out.print("</tr>");
		out.print("<tr>");
		out.print("<th scope=\"row\"><a href=\"#\"><img src=\"assets/img/product-4.jpg\" alt=\"\"></a></th>");
		out.print("<td><a href=\"#\" class=\"text-primary fw-bold\">Officiis quaerat sint rerum error</a></td>");
		out.print("<td>$32</td>");
		out.print("<td class=\"fw-bold\">63</td>");
		out.print("<td>$2,016</td>");
		out.print("</tr>");
		out.print("<tr>");
		out.print("<th scope=\"row\"><a href=\"#\"><img src=\"assets/img/product-5.jpg\" alt=\"\"></a></th>");
		out.print("<td><a href=\"#\" class=\"text-primary fw-bold\">Sit unde debitis delectus repellendus</a></td>");
		out.print("<td>$79</td>");
		out.print("<td class=\"fw-bold\">41</td>");
		out.print("<td>$3,239</td>");
		out.print("</tr>");
		out.print("</tbody>");
		out.print("</table>");
		out.print("");
		out.print("</div>");
		out.print("");
		out.print("</div>");
		out.print("</div><!-- End Top Selling -->");
		out.print("");
		out.print("</div>");
		out.print("</div><!-- End Left side columns -->");
		out.print("");
		out.print("<!-- Right side columns -->");
		out.print("<div class=\"col-lg-4\">");
		out.print("");
		out.print("<!-- Recent Activity -->");
		out.print("<div class=\"card\">");
		out.print("<div class=\"filter\">");
		out.print("<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.print("<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.print("<li class=\"dropdown-header text-start\">");
		out.print("<h6>Filter</h6>");
		out.print("</li>");
		out.print("");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">Today</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>");
		out.print("</ul>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"card-body\">");
		out.print("<h5 class=\"card-title\">Recent Activity <span>| Today</span></h5>");
		out.print("");
		out.print("<div class=\"activity\">");
		out.print("");
		out.print("<div class=\"activity-item d-flex\">");
		out.print("<div class=\"activite-label\">32 min</div>");
		out.print("<i class='bi bi-circle-fill activity-badge text-success align-self-start'></i>");
		out.print("<div class=\"activity-content\">");
		out.print("Quia quae rerum <a href=\"#\" class=\"fw-bold text-dark\">explicabo officiis</a> beatae");
		out.print("</div>");
		out.print("</div><!-- End activity item-->");
		out.print("");
		out.print("<div class=\"activity-item d-flex\">");
		out.print("<div class=\"activite-label\">56 min</div>");
		out.print("<i class='bi bi-circle-fill activity-badge text-danger align-self-start'></i>");
		out.print("<div class=\"activity-content\">");
		out.print("Voluptatem blanditiis blanditiis eveniet");
		out.print("</div>");
		out.print("</div><!-- End activity item-->");
		out.print("");
		out.print("<div class=\"activity-item d-flex\">");
		out.print("<div class=\"activite-label\">2 hrs</div>");
		out.print("<i class='bi bi-circle-fill activity-badge text-primary align-self-start'></i>");
		out.print("<div class=\"activity-content\">");
		out.print("Voluptates corrupti molestias voluptatem");
		out.print("</div>");
		out.print("</div><!-- End activity item-->");
		out.print("");
		out.print("<div class=\"activity-item d-flex\">");
		out.print("<div class=\"activite-label\">1 day</div>");
		out.print("<i class='bi bi-circle-fill activity-badge text-info align-self-start'></i>");
		out.print("<div class=\"activity-content\">");
		out.print("Tempore autem saepe <a href=\"#\" class=\"fw-bold text-dark\">occaecati voluptatem</a> tempore");
		out.print("</div>");
		out.print("</div><!-- End activity item-->");
		out.print("");
		out.print("<div class=\"activity-item d-flex\">");
		out.print("<div class=\"activite-label\">2 days</div>");
		out.print("<i class='bi bi-circle-fill activity-badge text-warning align-self-start'></i>");
		out.print("<div class=\"activity-content\">");
		out.print("Est sit eum reiciendis exercitationem");
		out.print("</div>");
		out.print("</div><!-- End activity item-->");
		out.print("");
		out.print("<div class=\"activity-item d-flex\">");
		out.print("<div class=\"activite-label\">4 weeks</div>");
		out.print("<i class='bi bi-circle-fill activity-badge text-muted align-self-start'></i>");
		out.print("<div class=\"activity-content\">");
		out.print("Dicta dolorem harum nulla eius. Ut quidem quidem sit quas");
		out.print("</div>");
		out.print("</div><!-- End activity item-->");
		out.print("");
		out.print("</div>");
		out.print("");
		out.print("</div>");
		out.print("</div><!-- End Recent Activity -->");
		out.print("");
		out.print("<!-- Budget Report -->");
		out.print("<div class=\"card\">");
		out.print("<div class=\"filter\">");
		out.print("<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.print("<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.print("<li class=\"dropdown-header text-start\">");
		out.print("<h6>Filter</h6>");
		out.print("</li>");
		out.print("");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">Today</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>");
		out.print("</ul>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"card-body pb-0\">");
		out.print("<h5 class=\"card-title\">Budget Report <span>| This Month</span></h5>");
		out.print("");
		out.print("<div id=\"budgetChart\" style=\"min-height: 400px;\" class=\"echart\"></div>");
		out.print("");
		
		out.print("</div>");
		out.print("</div><!-- End Budget Report -->");
		out.print("");
		out.print("<!-- Website Traffic -->");
		out.print("<div class=\"card\">");
		out.print("<div class=\"filter\">");
		out.print("<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.print("<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.print("<li class=\"dropdown-header text-start\">");
		out.print("<h6>Filter</h6>");
		out.print("</li>");
		out.print("");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">Today</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>");
		out.print("</ul>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"card-body pb-0\">");
		out.print("<h5 class=\"card-title\">Website Traffic <span>| Today</span></h5>");
		out.print("");
		out.print("<div id=\"trafficChart\" style=\"min-height: 400px;\" class=\"echart\"></div>");
		out.print("");
		
		out.print("");
		out.print("</div>");
		out.print("</div><!-- End Website Traffic -->");
		out.print("");
		out.print("<!-- News & Updates Traffic -->");
		out.print("<div class=\"card\">");
		out.print("<div class=\"filter\">");
		out.print("<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.print("<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.print("<li class=\"dropdown-header text-start\">");
		out.print("<h6>Filter</h6>");
		out.print("</li>");
		out.print("");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">Today</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>");
		out.print("<li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>");
		out.print("</ul>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"card-body pb-0\">");
		out.print("<h5 class=\"card-title\">News &amp; Updates <span>| Today</span></h5>");
		out.print("");
		out.print("<div class=\"news\">");
		out.print("<div class=\"post-item clearfix\">");
		out.print("<img src=\"assets/img/news-1.jpg\" alt=\"\">");
		out.print("<h4><a href=\"#\">Nihil blanditiis at in nihil autem</a></h4>");
		out.print("<p>Sit recusandae non aspernatur laboriosam. Quia enim eligendi sed ut harum...</p>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"post-item clearfix\">");
		out.print("<img src=\"assets/img/news-2.jpg\" alt=\"\">");
		out.print("<h4><a href=\"#\">Quidem autem et impedit</a></h4>");
		out.print("<p>Illo nemo neque maiores vitae officiis cum eum turos elan dries werona nande...</p>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"post-item clearfix\">");
		out.print("<img src=\"assets/img/news-3.jpg\" alt=\"\">");
		out.print("<h4><a href=\"#\">Id quia et et ut maxime similique occaecati ut</a></h4>");
		out.print("<p>Fugiat voluptas vero eaque accusantium eos. Consequuntur sed ipsam et totam...</p>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"post-item clearfix\">");
		out.print("<img src=\"assets/img/news-4.jpg\" alt=\"\">");
		out.print("<h4><a href=\"#\">Laborum corporis quo dara net para</a></h4>");
		out.print("<p>Qui enim quia optio. Eligendi aut asperiores enim repellendusvel rerum cuder...</p>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"post-item clearfix\">");
		out.print("<img src=\"assets/img/news-5.jpg\" alt=\"\">");
		out.print("<h4><a href=\"#\">Et dolores corrupti quae illo quod dolor</a></h4>");
		out.print("<p>Odit ut eveniet modi reiciendis. Atque cupiditate libero beatae dignissimos eius...</p>");
		out.print("</div>");
		out.print("");
		out.print("</div><!-- End sidebar recent posts-->");
		out.print("");
		out.print("</div>");
		out.print("</div><!-- End News & Updates -->");
		out.print("");
		out.print("</div><!-- End Right side columns -->");
		out.print("");
		out.print("</div>");
		out.print("</section>");
		
		out.print("</main><!-- End #main -->");

		
		
		// Tham chiếu servlet footer thông qua đối tượng RequestDispatcher
		RequestDispatcher footer = request.getRequestDispatcher("/footer");
		if (footer != null) {
			footer.include(request, response);
		}
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
