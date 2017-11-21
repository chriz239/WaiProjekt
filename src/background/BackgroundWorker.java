package background;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dao.CamDao;
import dao.CamImageDao;
import dao.DaoFactory;
import models.Cam;

public class BackgroundWorker implements Job {
	
	private static Logger jlog = Logger.getLogger(BackgroundWorker.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// itterate all cams in db
		CamDao camDao = DaoFactory.getCamDao();
		CamImageDao camImageDao = DaoFactory.getCamImageDao();
		List<Cam> cams = camDao.getAll();
		for (Cam cam : cams) {
			// get Image from URL and save to DB
			try {
				jlog.info("Get image from URL: " + cam.getUrl().toString());
				//BufferedImage bi = ImageIO.read(cam.getUrl());
				// geht 100 MAl schneller als die Zeile darüber
				Image img = (new ImageIcon(cam.getUrl())).getImage();
				BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			    Graphics2D bGr = bi.createGraphics();
			    bGr.drawImage(img, 0, 0, null);
			    bGr.dispose();
				jlog.info("Save image to db");
				camImageDao.saveCapturedImage(bi, cam.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
