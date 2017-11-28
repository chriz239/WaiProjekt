package servlets;

import java.io.IOException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CamDao;
import dao.DaoFactory;
import models.Cam;

@WebServlet("/AddCam")
public class AddCam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CamDao camDao = DaoFactory.getCamDao();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Hier seite normal anzeigen
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/add_webcam.jsp");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check params
		if (request.getParameter("camName") == null || request.getParameter("camUri") == null) {
			// error
		}
		
		// extract parameters
		String name = request.getParameter("camName");
		String sURL = request.getParameter("camUri");
		URL url = new URL(sURL);
		String standort = request.getParameter("standort");
		
		// Cam in db anlegen
		Cam cam = new Cam(name, url, standort);
		camDao.save(cam);
		
		doGet(request, response);
	}

}
