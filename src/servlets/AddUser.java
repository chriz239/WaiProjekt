package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.UserDao;
import models.User;

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserDao userDao = DaoFactory.getUserDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Hier AddUser.jsp normal anzeigen
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/add_user.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// check params
		if (request.getParameter("name") == null || request.getParameter("password") == null) {
			// error
		}

		// extract params
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		// create user in db
		User user = new User(name, password);
		userDao.save(user);

		doGet(request, response);
	}

}
