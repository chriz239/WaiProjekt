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
		/*
		String name = request.getParameter("UserName");
		String password = request.getParameter("Password");
		*/
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// check params
		if (request.getParameter("name") == null 
				|| request.getParameter("password") == null
				|| request.getParameter("selectMode") == null) {
			// error
			return;
		}
		
		// prepare variables
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String selectedMode = request.getParameter("selectMode");
		
		User user = userDao.getUserByName(name);
		
		// check password
		if (!user.getPasswort().equals(password)) {
			// error
			return;
		}
		
		// set user as logged in
		HttpSession session = request.getSession(true);
		session.setAttribute("loggedin", new Boolean(true));
		
		// forward to next page depending on mode
		if (selectedMode.equals("uMode")) {
			// redirect user to webcam_search
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WebcamSearch");
			dispatcher.forward(request, response);
		}
		
		if (selectedMode.equals("pMode")) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user_list.jsp");
			dispatcher.forward(request, response);
		}
	}
}
