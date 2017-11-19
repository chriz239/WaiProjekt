package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.DaoFactory;
import dao.UserDao;
import models.User;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserDao userDao = DaoFactory.getUserDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		// Hier Login-Seite normal anzeigen
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// check params
		if (request.getParameter("name") == null || request.getParameter("password") == null) {
			// error
		}
		
		// prepare variables
		String name = request.getParameter("name");
		String password = request.getParameter("password"); 
		
		User user = userDao.getUserByName(name);
		
		// check password
		if (!user.getPasswort().equals(password)) {
			// error
		}
		
		// set user as logged in
		HttpSession session = request.getSession(true);
		session.setAttribute("loggedin", new Boolean(true));
		
		// forward to next page
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/filter_images.jsp");
		dispatcher.forward(request, response);
	}
}
