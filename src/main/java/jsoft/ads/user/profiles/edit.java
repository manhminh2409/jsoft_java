package jsoft.ads.user.profiles;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsoft.ConnectionPool;
import jsoft.ads.user.UserControl;
import jsoft.library.Utilities;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class EditProfiles
 */
@WebServlet("/user/profiles/edit")
public class edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Khai báo kiểu nội dung xuất về trình khách(client)
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public edit() {
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

		// Tìm id để xem chi tiết thông tin
		int id = Utilities.getIntParam(request, "id");

		String name = "", fullname = "", email = "", address = "";
		String phone = "", mobile = "", office = "", notes = "", createdate = "";

		boolean isEdit = false;

		if (id > 0) {
			// Tìm bộ quản lý kết nối
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");

			// Tạo đối tượng thực thi chức năng
			UserControl uc = new UserControl(cp);

			if (cp == null) {
				getServletContext().setAttribute("CPool", uc.getCP());
			}

			// Lấy đối tượng để xem thông tin
			UserObject user = uc.getUserObject(id);

			// Trả về kết nối
			uc.releaseConnection();

			// Tách thông tin
			if (user != null) {
				name = user.getUser_name();
				fullname = user.getUser_fullname();
				email = user.getUser_email();
				address = user.getUser_address();
				phone = user.getUser_homephone();
				mobile = user.getUser_mobilephone();
				office = user.getUser_officephone();
				notes = user.getUser_notes();
				createdate = user.getUser_created_date();
				isEdit = true;
			}
		}

		// Xác định vị trí
		String tab = request.getParameter("t");
		String show = "";
		if (tab != null && tab.equalsIgnoreCase("e")) {
			show = " show active ";
		}
		out.print("<div class=\"tab-pane fade " + show + " profile-edit pt-3\" id=\"profile-edit\">");
		out.print("");
		out.print("<!-- Profile Edit Form -->");
		out.print("<form>");
		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"profileImage\" class=\"col-md-4 col-lg-3 col-form-label\">Profile Image</label>");
		out.print("<div class=\"col-md-8 col-lg-9\">");
		out.print("<img src=\"/adv/img/profile-img.jpg\" alt=\"Profile\">");
		out.print("<div class=\"pt-2\">");
		out.print(
				"<a href=\"#\" class=\"btn btn-primary btn-sm me-1\" title=\"Upload new profile image\"><i class=\"bi bi-upload\"></i></a>");
		out.print(
				"<a href=\"#\" class=\"btn btn-danger btn-sm ms-1\" title=\"Remove my profile image\"><i class=\"bi bi-trash\"></i></a>");
		out.print("</div>");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"fullName\" class=\"col-md-4 col-lg-3 col-form-label\">Full Name</label>");
		out.print("<div class=\"col-md-5 col-lg-5\">");
		out.print("<input name=\"txtFullName\" type=\"text\" class=\"form-control\" id=\"fullName\" value=\"" + fullname
				+ "\">");
		out.print("</div>");
		out.print("<div class=\"col-md-3 col-lg-3\">");
		out.print("<input name=\"\" type=\"text\" class=\"form-control\" id=\"name\" disabled value=\"" + name + "\">");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"notes\" class=\"col-md-4 col-lg-3 col-form-label\">Notes</label>");
		out.print("<div class=\"col-md-8 col-lg-9\">");
		out.print("<textarea name=\"txtNotes\" class=\"form-control\" id=\"notes\" style=\"height: 100px\">" + notes
				+ "</textarea>");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"Address\" class=\"col-md-4 col-lg-3 col-form-label\">Address</label>");
		out.print("<div class=\"col-md-8 col-lg-9\">");
		out.print("<input name=\"txtAddress\" type=\"text\" class=\"form-control\" id=\"Address\" value=\"" + address
				+ "\">");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"Phone\" class=\"col-md-4 col-lg-3 col-form-label\">Phone</label>");
		out.print("<div class=\"col-md-8 col-lg-9\">");
		out.print(
				"<input name=\"txtPhone\" type=\"text\" class=\"form-control\" id=\"Phone\" value=\"" + phone + "\">");
		out.print("</div>");
		out.print("</div>");
		out.print("");

		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"Mobile\" class=\"col-md-4 col-lg-3 col-form-label\">Mobile</label>");
		out.print("<div class=\"col-md-8 col-lg-9\">");
		out.print("<input name=\"txtMobile\" type=\"text\" class=\"form-control\" id=\"Mobile\" value=\"" + mobile
				+ "\">");
		out.print("</div>");
		out.print("</div>");
		out.print("");

		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"Office\" class=\"col-md-4 col-lg-3 col-form-label\">Office</label>");
		out.print("<div class=\"col-md-8 col-lg-9\">");
		out.print("<input name=\"txtOffice\" type=\"text\" class=\"form-control\" id=\"Office\" value=\"" + office
				+ "\">");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"Email\" class=\"col-md-4 col-lg-3 col-form-label\">Email</label>");
		out.print("<div class=\"col-md-8 col-lg-9\">");
		out.print(
				"<input name=\"txtEmail\" type=\"email\" class=\"form-control\" id=\"Email\" value=\"" + email + "\">");
		out.print("</div>");
		out.print("</div>");
		out.print("");

		out.print("<div class=\"row mb-3\">");
		out.print("<label for=\"CreateD\" class=\"col-md-4 col-lg-3 col-form-label\">Create date</label>");
		out.print("<div class=\"col-md-8 col-lg-9\">");
		out.print("<input name=\"txtCreateD\" type=\"text\" class=\"form-control\" id=\"CreateD\" value=\"" + createdate
				+ "\">");
		out.print("</div>");
		out.print("</div>");
		out.print("");
		out.print("");
		out.print("<div class=\"text-center\">");
		out.print(
				"<button type=\"button\" class=\"btn btn-primary\" onClick=\"editProfile(this.form)\">Save Changes</button>");
		out.print("</div>");

		// Truyền id để ghi nhận sự cập nhật
		if (isEdit) {
			// Theo cơ chế biến form ẩn
			out.print("<input type=\"hidden\" name =\"txtID\" value =\"" + id + "\"/>");
		}
		out.print("</form><!-- End Profile Edit Form -->");
		out.print("");
		out.print("</div>");
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

		// Tìm id để cập nhật thông tin doGet truyền cho
		int id = Utilities.getIntParam(request, "txtID");

		String fullname = "", address = "";
		String phone = "", mobile = "", office = "", notes = "";
		String email = request.getParameter("txtEmail");

		if (id > 0 && email != null & !email.equalsIgnoreCase("")) {
			// Tìm bộ quản lý kết nối
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");

			// Tạo đối tượng thực thi chức năng
			UserControl uc = new UserControl(cp);

			if (cp == null) {
				getServletContext().setAttribute("CPool", uc.getCP());
			}

			// Lấy đối tượng để câp nhật
			UserObject user = uc.getUserObject(id);

			// Lấy tiếp thông tin mới nhất

			fullname = request.getParameter("txtFullName");
			address = request.getParameter("txtAddress");
			phone = request.getParameter("txtPhone");
			mobile = request.getParameter("txtMobile");
			office = request.getParameter("txtOffice");
			notes = request.getParameter("txtNotes");

			// Bổ xung thông tin
			if (user != null) {
				user.setUser_id(id);
				user.setUser_fullname(Utilities.encodeToHtml(fullname));
				user.setUser_address(Utilities.encodeToHtml(address));
				user.setUser_mobilephone(mobile);
				user.setUser_officephone(office);
				user.setUser_homephone(phone);
				user.setUser_email(email);
				user.setUser_notes(Utilities.encodeToHtml(notes));

			}

			// Cập nhật trở lại
			boolean result = uc.editUser(user);

			// Trả về kết nối
			uc.releaseConnection();

			// Chuyển về giao diện chỉnh

			if (result) {
				response.sendRedirect("/adv/user/view");
			} else {
				response.sendRedirect("/adv/user/profiles?id=" + id + "&err=notoke");
			}
		} else {
			response.sendRedirect("/adv/user/view?err=value");
		}
	}

}
