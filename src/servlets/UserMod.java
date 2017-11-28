package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CamDao;
import dao.DaoFactory;
import dao.UserCamAccess;
import dao.UserDao;

@WebServlet("/UserMod")
public class UserMod extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// check params
		if (request.getParameter("action") == null || request.getParameter("userId") == null) {
			// error
			return;
		}

		// extract params
		String action = request.getParameter("action");
		String sUserId = request.getParameter("userId");
		long userid = Long.parseLong(sUserId);

		switch (action) {
		case "delete":
			// delete user and redirect
			UserDao userDao = DaoFactory.getUserDao();
			userDao.delete(userid);
			response.sendRedirect("UserList");
			break;
		case "cams":
			// get all cams from db
			CamDao camDao = DaoFactory.getCamDao();
			request.setAttribute("cams", camDao.getAll());
			
			// passthrough user
			request.setAttribute("userId", userid);
			
			// show user_mod_cams.jsp
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user_mod_cams.jsp");
			dispatcher.forward(request, response);
			break;
		default:
			return;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// grap some additional parameters
		if (request.getParameter("userId") == null || request.getParameter("mod") == null || request.getParameter("camId") == null) {
			// error
			return;
		}
		
		// extract params
		long userId = Long.parseLong(request.getParameter("userId"));
		long camId = Long.parseLong(request.getParameter("camId"));
		
		UserCamAccess userCamAccess = DaoFactory.getUserCamAccessDao();
		
		switch (request.getParameter("mod")) {
		case "add":
			userCamAccess.add(userId, camId);
			break;
		case "remove":
			userCamAccess.remove(userId, camId);
			break;
		default:
			return;
		}
		
		// redirect to UserList
		response.sendRedirect("UserList");
	}
}
