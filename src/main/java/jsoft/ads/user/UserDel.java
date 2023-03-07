package jsoft.ads.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsoft.ConnectionPool;
import jsoft.library.Utilities;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class UserDel
 */
@WebServlet("/user/del")
public class UserDel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Khai báo kiểu nội dung xuất về trình khách(client)
	private static final String CONTENT_TYPE = "text/html; charset = utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDel() {
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

		// Tìm thông tin đăng nhập trong phiên làm việc
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");

		// Tìm id để xoá
		int id = Utilities.getIntParam(request, "id");

		if (id > 0 && id != user.getUser_id()) {

			// Tìm bộ quản lý kết nối
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");

			// Tạo đối tượng thực thi chức năng
			UserControl uc = new UserControl(cp);

			if (cp == null) {
				getServletContext().setAttribute("CPool", uc.getCP());
			}

			UserObject dUser = new UserObject();

			dUser.setUser_id(id);

			// Thực hiện xoá
			boolean result = uc.delUser(dUser);

			// Trả về kết nối
			uc.releaseConnection();

			// Kiểm tra
			if (result) {
				response.sendRedirect("/adv/user/view");
			} else {
				response.sendRedirect("/adv/user/view?err=notoke");
			}

		} else {
			response.sendRedirect("/adv/user/view?err=noid");
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
