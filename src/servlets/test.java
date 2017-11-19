package servlets;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import models.Cam;
import models.User;

import java.awt.image.BufferedImage; 


@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getImageFromUrlAndSaveToDb();
	}
	
	private void getImageFromUrlAndSaveToDb() {
		// itterate all cams in db
		CamDao camDao = DaoFactory.getCamDao();
		CamImageDao camImageDao = DaoFactory.getCamImageDao();
		List<Cam> cams = camDao.getAll();
		for (Cam cam : cams) {
			// get Image from URL and save to DB
			try {
				BufferedImage bi = ImageIO.read(cam.getUrl());
				camImageDao.saveCaputredImage(bi, cam.getId());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	private void CamDaoUpdate() {
		
	}
	
	private void CamDaoInsert() {
		CamDao camDao = DaoFactory.getCamDao();
		URL url = null;
		try {
			url = new URL("https://www.mvv.de/webcam_maritim/MA-Wasserturm.jpg");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cam cam = new Cam("Wasserturm", url, "Mannheim");
		camDao.save(cam);
	}
	
	private void UserDao() {
		UserDao userDao = DaoFactory.getUserDao();
		User user = userDao.get(1L);
		user.setPasswort("qwertz");
		userDao.save(user);
	}
}
