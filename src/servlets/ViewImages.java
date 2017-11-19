package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CamImageDao;
import dao.DaoFactory;
import models.CamImage;

@WebServlet("/ViewImages")
public class ViewImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CamImageDao camImageDao = DaoFactory.getCamImageDao();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Hier formular normal anzeigen
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check params
		if (request.getParameter("start") == null || request.getParameter("end") == null) {
			// error
		}
		
		// parameter in Timestamp wandeln
		Timestamp start = Timestamp.valueOf(request.getParameter("start"));
		Timestamp end = Timestamp.valueOf(request.getParameter("end"));
		
		// hole alle passenden images aus DB
		List<CamImage> camImages = camImageDao.getBetween(start, end);
		
		// show found images
		// TODO: schauen wie man parameter an jsp übergibt
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/view_images.jsp");
		dispatcher.forward(request, response);
	}

}
