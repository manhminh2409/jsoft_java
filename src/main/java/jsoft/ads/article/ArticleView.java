package jsoft.ads.article;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import jsoft.ConnectionPool;
import jsoft.objects.*;

/**
 * Servlet implementation class View
 */
@WebServlet("/article/view")
public class ArticleView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Khai báo kiểu nội dung xuất về trình khách(client)
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticleView() {
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

		// Tìm bộ quản lý kết nối
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");

		// Tạo đối tượng thực thi chức năng
		ArticleControl cc = new ArticleControl(cp);

		if (cp == null) {
			getServletContext().setAttribute("CPool", cc.getCP());
		}

		// Lấy cấu trúc trình bày
		String view = cc.viewArticles(null, ArticleSort.ID, (short) 1, (byte) 10);

		// Trả về kết nối
		cc.releaseConnection();

		
		
		
		out.print("<main id=\"main\" class=\"main\">");
		out.print("");
		out.print("<div class=\"pagetitle\">");
		out.print("<h1>Article management</h1>");
		out.print("<nav>");
		out.print("<ol class=\"breadcrumb\">");
		out.print("<li class=\"breadcrumb-item\"><a href=\"/adv/view\">Home</a></li>");
		out.print("<li class=\"breadcrumb-item\">Pages</li>");
		out.print("<li class=\"breadcrumb-item active\">Blank</li>");
		out.print("</ol>");
		out.print("</nav>");
		out.print("</div><!-- End Page Title -->");
		out.print("");
		out.print("<section class=\"section\">");
		out.print("<div class=\"row\">");
		out.print("<div class=\"col-lg-12\">");

		out.print(view);

		out.print("</div>");
		out.print("</div>");
		out.print("</section>");
		out.print("");
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
