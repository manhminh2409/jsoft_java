package jsoft.ads.user;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import jsoft.ConnectionPool;
import jsoft.objects.*;

import jsoft.library.*;

/**
 * Servlet implementation class View
 */
@WebServlet("/user/add")
public class UserAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Khai báo kiểu nội dung xuất về trình khách(client)
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAdd() {
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
		UserControl uc = new UserControl(cp);

		if (cp == null) {
			getServletContext().setAttribute("CPool", uc.getCP());
		}

		// Lấy cấu trúc trình bày
		// String view = uc.viewUsers(null, UserSort.NAME, (short) 1, (byte) 20);

		// Tìm tham số báo lỗi nếu có
//		String error = request.getParameter("err");
//		String message = "";
//
//		if (error != null) {
//			switch (error) {
//
//			case "param":
//				message = "Không chính xác tham số lấy giá trị!";
//				break;
//
//			case "value":
//				message = "Không có giá trị để tạo người sử dụng!";
//				break;
//
//			case "notok":
//				message = "Đăng nhập không thành công!";
//				break;
//
//			default:
//				message = "Không thành công!";
//			}
//		}
		// Trả về kết nối
		uc.releaseConnection();

		out.print("<main id=\"main\" class=\"main\">");
		out.print("");
		out.print("<div class=\"pagetitle\">");
		out.print("<h1>Users management</h1>");
		out.print("<nav>");
		out.print("<ol class=\"breadcrumb\">");
		out.print("<li class=\"breadcrumb-item\"><a href=\"/adv/view\">Dashboard</a></li>");
		out.print("<li class=\"breadcrumb-item\"><a href=\"/adv/user/view\">Users</a></li>");
		out.print("<li class=\"breadcrumb-item active\">New user</li>");
		out.print("</ol>");
		out.print("</nav>");
		out.print("</div><!-- End Page Title -->");
		out.print("");
		out.print("<section class=\"section\">");
		out.print("<div class=\"row\">");
		out.print("<div class=\"col-lg-12\">");

		// ===============
		out.print("<div class=\"card\">");
		out.print("<div class=\"card-body\">");
		out.print("<h5 class=\"card-title\">Users <span>| New</span></h5>");

		out.print("<form>");
		out.print("<!-- fullname-->");
		out.print("<div class=\"row mt-3 align-items-center\">");
		out.print("<label class=\"col-lg-2 text-end py-2\" for=\"fullName\">");
		out.print("Full name");
		out.print("</label>");
		out.print("<div class=\"col-lg-3\">");
		out.print("<input type=\"text\" class=\"form-control\"  id =\"fullName\" name =\"txtFullName\">");
		out.print("</div>");
		out.print("</div>");

		out.print("<!--username-->");
		out.print("<div class=\"row mt-3 align-items-center\">");
		out.print("<label class=\"col-lg-2 text-end py-2\" for=\"username\">");
		out.print("Username:");
		out.print("</label>");

		out.print("<div class=\"col-lg-3 \">");
		out.print(
				"<input type=\"text\" class=\"form-control\" id=\"username\" name =\"txtUserName\" onkeyup =\"checkUsername()\">");
		out.print("</div>");
		out.print("<div class=\"col-lg-5 \" id =\"errUsername\"></div>");
		out.print("</div>");
		out.print("<!--password&confirm-->");

		out.print("<div class=\"row mt-3 align-items-center\">");
		out.print("<label class=\"col-lg-2  text-end py-2\" for=\"pass\">");
		out.print("Password:");
		out.print("</label>");
		out.print("<div class=\"col-lg-3\">");
		out.print(
				"<input type=\"password\" class=\"form-control\" id=\"pass\" name=\"txtUserPass\" onkeyup=\"checkPassword()\">");
		out.print("<div class = \"\" id=\"errPassword\"></div>");
		out.print("</div>");

		out.print("<div class = \"col-lg-1\" id=\"iconPassword\"></div>");

		out.print("<label class=\"col-lg-2 text-end py-2 cfpass\" for=\"cfpass\">");
		out.print("Confirm password:");
		out.print("</label>");
		out.print("");
		out.print("<div class=\" col-lg-3 \">");
		out.print(
				"<input type=\"password\" class=\"form-control\" id =\"cfpass\" name=\"txtUserPass2\" onkeyup=\"checkPassword()\">");
		out.print("<div class = \"\" id=\"errCfPassword\"></div>");
		out.print("</div>");
		out.print("");
		out.print("<div class = \"col-lg-1\" id=\"iconCfPassword\"></div>");
		out.print("</div>");
		out.print("");
		out.print("<!--email-->");
		out.print("<div class=\"row mt-3 align-items-center\">");
		out.print("<label class=\"col-lg-2 text-end py-2\" for=\"email\">");
		out.print("Email:");
		out.print("</label>");
		out.print("<div class=\"col-lg-3\">");
		out.print(
				"<input type=\"email\" class=\"form-control\" id=\"email\" name=\"txtUserEmail\" onkeyup=\"checkEmail()\">");
		out.print("<div class=\"\" id =\"errEmail\"></div>");
		out.print("</div>");
		out.print("<div class=\"col-lg-1\" id =\"iconEmail\"></div>");
		out.print("</div>");
		out.print("");
		out.print("<!--permission-->");
		out.print("<div class=\"row mt-3 align-items-center\">");
		out.print("<label class = \"col-lg-2 text-end py-2\" for=\"permission\">");
		out.print("Permission:");
		out.print("</label>");
		out.print("");
		out.print("<div class=\"col-lg-3\">");
		out.print("<script language = \"javascript\">");
		out.print("generatePermis();");
		out.print("</script>");
		out.print("");
		out.print("</div>");
		out.print("<div class=\"col-lg-1\" id =\"iconPermis\"></div>");
		out.print("</div>");

		out.print("<div class=\"row align-items-center\">");
		out.print("<div class=\"col-md-12 text-center\">");
		out.print("<hr class=\" border-top border-1 border-primary\">");
		out.print("</div>");
		out.print("</div>");

		out.print("<div class=\"row mt-3 \">");
		out.print("<div class=\"col-md-12 text-center\">");
		out.print(
				"<button type=\"button\" class=\"btn btn-primary mx-2\" id=\"send\" onclick=\"createAccount(this.form)\"><i class=\"fa-solid fa-right-to-bracket\"></i> Create</button>");
		out.print(
				"<button type=\"submit\" class=\"btn btn-primary\">Exit <i class=\"fa-solid fa-arrow-right-from-bracket\"></i></button>");
		out.print("</div>");
		out.print("</div>");

		out.print("</form>");
		out.print("</div>");// card-body
		out.print("</div>");// card
		// ===============

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
		// Thiết lập tập ký tự
		request.setCharacterEncoding("utf-8");

		// Lấy thông tin bắt buộc trên giao diện
		String name = request.getParameter("txtUserName");
		String pass = request.getParameter("txtUserPass");
		String email = request.getParameter("txtUserEmail");

		if (name != null && pass != null) {

			// Trường hợp name đã là email
			if (email == null) {
				email = name;
			}

			if (!name.equalsIgnoreCase("") && !pass.equalsIgnoreCase("") && !email.equalsIgnoreCase("")) {

				// Lấy các thông tin không bắt buộc
				String fullname = request.getParameter("txtFullName");

				// Tìm bộ quản lý kết nối
				ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");

				// Tạo đối tượng thực thi chức năng
				UserControl uc = new UserControl(cp);

				if (cp == null) {
					getServletContext().setAttribute("CPool", uc.getCP());
				}

				// Tạo đối tượng lưu trữ thông tin
				UserObject nUser = new UserObject();
				nUser.setUser_name(name);
				nUser.setUser_pass(pass);
				nUser.setUser_email(email);
				nUser.setUser_fullname(Utilities.encodeToHtml(fullname));
				nUser.setUser_created_date("06/11/2022");
				nUser.setUser_parent_id(5);

				// Thực hiện
				boolean result = uc.addUser(nUser);

				// Trả về kết nối
				uc.releaseConnection();

				// Kiếm tra
				if (result) {
					response.sendRedirect("/adv/user/view");
				} else {
					response.sendRedirect("/adv/user/add?err=notok");
				}

			} else {
				response.sendRedirect("/adv/user/add?err=value");
			}

		} else {
			response.sendRedirect("/adv/user/add?err=param");
		}

	}

}
