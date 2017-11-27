package dao;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import javax.imageio.ImageIO;

import exception.CamImageNotFoundException;
import jndi.JndiFactory;
import models.CamImage;

public class CamImageDaoImpl implements CamImageDao {
	
	private final JndiFactory jndi = JndiFactory.getInstance();
	private final String connectionString = "jdbc/CamDB";

	@Override
	public void save(CamImage img) {
		if (img == null)
			throw new IllegalArgumentException("image can't be null");
		
		Connection con = null;
		try {
			con = jndi.getConnection(connectionString);
			if (img.getId() == null) {
				PreparedStatement pstmt = con.prepareStatement("insert into camImages (captureTime, uuid, camId, path) values (?, ?, ?, ?)");
				pstmt.setTimestamp(1, img.getCaptureTime());
				pstmt.setString(2, img.getUuid().toString());
				pstmt.setLong(3, img.getCamId());
				pstmt.setString(4, img.getImagePath());
				pstmt.executeUpdate();
			} else {
				PreparedStatement pstmt = con.prepareStatement("update camImages set captureTime = ?, name = ?, camId = ?, path = ? where id = ?");
				pstmt.setTimestamp(1, img.getCaptureTime());
				pstmt.setString(2, img.getUuid().toString());
				pstmt.setLong(3, img.getCamId());
				pstmt.setString(4, img.getImagePath());
				pstmt.setLong(5, img.getId());
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	@Override
	public void delete(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id can't be null");
		}
		
		Connection con = null;
		try {
			con = jndi.getConnection(connectionString);
			PreparedStatement pstmt = con.prepareStatement("delete from camImages where id = ?");
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeConnection(con);
		}
	}

	@Override
	public CamImage get(Long id) {
		if (id == null)
			throw new IllegalArgumentException("id can't be null");
		
		Connection con = null;
		try {
			con = jndi.getConnection(connectionString);
			PreparedStatement pstmt = con.prepareStatement("select id, captureTime, name, camId from camImages where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				CamImage camImage = new CamImage();
				camImage.setId(rs.getLong("id"));
				camImage.setCaptureTime(rs.getTimestamp("captureTime"));
				camImage.setUuid(UUID.fromString(rs.getString("UUID")));
				camImage.setCamId(rs.getLong("camId"));
				return camImage;
			} else {
				throw new Exception("Image not found");
			}
		} catch (Exception e) {
			throw new CamImageNotFoundException();
		} finally {
			closeConnection(con);
		}
	}
	
	public static BufferedImage generateThumb(BufferedImage img){
		// TODO: scale (w,h) Werte prüfen
		try{
			/*
			int w = 216;
			int h = 162;
			int imageWidth =img.getWidth();
			int imageHeigth = img.getHeight();
			double outputAspect = 1.0*w/h;
			double inputAspect = 1.0*imageWidth/imageHeigth;
			if(outputAspect < inputAspect) {
				h = (int)(w/inputAspect);
			}
			else{
				w = (int)(h*inputAspect);
			}
			*/
			
			double factor;
			int w, h, maxSize = 150;
			// find bigger one
			if (img.getHeight() > img.getWidth()) {
				factor = 1.0 * img.getHeight() / maxSize; //100px
				h = maxSize;
				w = (int) Math.round(img.getWidth() / factor);
			} else {
				factor = 1.0 * img.getWidth() / maxSize;
				w = maxSize;
				h = (int) Math.round(img.getHeight() / factor);
			}
			
			Image scaledImage = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			BufferedImage thumb = new BufferedImage(w ,h, BufferedImage.TYPE_INT_RGB); 
			Graphics g = thumb.getGraphics();
            g.drawImage(scaledImage, 0, 0, null);
            g.dispose();
            
            return thumb; 
		}
		catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void saveCapturedImage(BufferedImage img, Long camId) {
		UUID uuid = UUID.randomUUID();
		
		// thumbnail generieren
		BufferedImage thumbnail = generateThumb(img);
		
		// Datensatz speichern
		CamImage camImage = new CamImage();
		camImage.setCamId(camId);
		camImage.setCaptureTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
	
		String imagePath = "C:\\Users\\Christopher\\Documents\\Studium\\WorkspaceWAI\\WaiCams\\WebContent\\bilder\\"
							+ camId + "\\" 
							+ camImage.getCaptureTime().getMonth() + "\\"
							+ uuid + ".png";
		String thumbPath = "C:\\Users\\Christopher\\Documents\\Studium\\WorkspaceWAI\\WaiCams\\WebContent\\bilder\\Thumbs\\"
				+ camId + "\\" 
				+ camImage.getCaptureTime().getMonth() + "\\"
				+ uuid + ".png";
	
		camImage.setUuid(uuid);
		
		save(camImage);
	
		// BufferedImage img an richtiger Stelle abspeichern
		saveCamImageToFile(img, imagePath);
		// Thumbnail an richtiger Stelle speichern
		saveCamImageToFile(thumbnail, thumbPath);
	}
	
	
	@Override
	public List<CamImage> getBetween(Timestamp start, Timestamp end, Long camId) {
		if (start == null || end == null || camId == null) {
			throw new IllegalArgumentException("start or end is null");
		}
		
		Connection con = null;
		try {
			con = jndi.getConnection(connectionString);
			PreparedStatement pstmt = con.prepareStatement("select id, captureTime, uuid, camId from camImages where camid = ? AND capturetime > ? AND CaptureTime < ?");
			pstmt.setLong(1, camId);
			pstmt.setTimestamp(2, start);
			pstmt.setTimestamp(3, end);
			ResultSet rs = pstmt.executeQuery();
			List<CamImage> camImages = new ArrayList<CamImage>();
			while (rs.next()) {
				CamImage camImage = new CamImage();
				camImage.setId(rs.getLong("id"));
				camImage.setCaptureTime(rs.getTimestamp("captureTime"));
				camImage.setUuid(UUID.fromString(rs.getString("UUID")));
				camImage.setCamId(rs.getLong("camId"));
				camImages.add(camImage);
			}
			return camImages;
		} catch (Exception e) {
			throw new CamImageNotFoundException();
		} finally {
			closeConnection(con);
		}
		
	}
	
	private void saveCamImageToFile(BufferedImage img, String path) {
		try{
			File outImg = new File(path);
			// generate Parent Folder if not exist
			outImg.getParentFile().mkdirs();
			ImageIO.write(img, "png", outImg); 
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	

	
	private ByteArrayInputStream convertImageToBlob(BufferedImage img) {
		ByteArrayOutputStream baos = null;
		try {
		    baos = new ByteArrayOutputStream();
		    ImageIO.write(img, "png", baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    try {
		        baos.close();
		    } catch (Exception e) {
		    }
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		return bais;
	}
	
	private BufferedImage convertBlobToImage(Blob blob) {
		try {
			return ImageIO.read(blob.getBinaryStream());
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
				con = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	

	
	
}
