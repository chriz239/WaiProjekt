package servlets;

import java.io.IOException;
import java.net.URL;

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
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check params
		if (request.getParameter("name") == null || request.getParameter("url") == null || request.getParameter("standort") == null) {
			// error
		}
		
		// extract parameters
		String name = request.getParameter("name");
		URL url = new URL(request.getParameter("url"));
		String standort = request.getParameter("standort");
		
		// Cam in db anlegen
		Cam cam = new Cam(name, url, standort);
		camDao.save(cam);
		
		// TODO: Seite normal anzeigen
	}

}
