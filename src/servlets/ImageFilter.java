package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CamDao;
import dao.CamImageDao;
import dao.DaoFactory;
import models.Cam;
import models.CamImage;
import models.User;

@WebServlet("/ImageFilter")
public class ImageFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// hier seite normal anzeigen
		
		if (checkUserIsLoggedIn(request)) return;
		
		
		setCams(request);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/image_filter.jsp");
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

	private boolean checkUserIsLoggedIn(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		return (user == null);
	}

	private void setCams(HttpServletRequest request) {
		// cams �bergeben, die user sehen darf
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		List<Long> camIds = DaoFactory.getUserCamAccessDao().getCamsOfUser(user.getId());
		// get cam of each camId
		List<Cam> cams = new ArrayList<Cam>();
		CamDao camDao = DaoFactory.getCamDao();
		for (Long camId : camIds) {
			cams.add(camDao.get(camId));
		}
		
		request.setAttribute("cams", cams);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (checkUserIsLoggedIn(request)) return;
		
		// check params: only camId is realy needed
		if (request.getParameter("camId") == null) {
			// error
			return;
		}
		
		// prepare variables
		String sCamId = request.getParameter("camId");
		String sDateFrom = request.getParameter("Date_from");
		String sDateTill = request.getParameter("Date_till");
		
		long camId = Long.parseLong(sCamId);
		
		// set default values if date is not provided
		Timestamp dateFrom = (sDateFrom.equals("")) ? Timestamp.valueOf("2011-10-02 18:48:05.123456") : Timestamp.valueOf(sDateFrom);
		Timestamp dateTill = (sDateTill.equals("")) ? new Timestamp(Calendar.getInstance().getTime().getTime()) : Timestamp.valueOf(sDateTill);
		
		// get selected images from DB and display them
		CamImageDao camImageDao = DaoFactory.getCamImageDao();
		List<CamImage> camImages = camImageDao.getBetween(dateFrom, dateTill, camId);
		
		// camImages an JSP �bergeben
		request.setAttribute("images", camImages);
		setCams(request);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/image_filter.jsp");
		dispatcher.forward(request, response);
	}

}
