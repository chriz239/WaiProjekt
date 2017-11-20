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
import java.util.List;
import java.util.UUID;
import java.awt.image.BufferedImage; 

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
				PreparedStatement pstmt = con.prepareStatement("insert into camImages (id, captureTime, uuid, thumbnail, camId, path) values (?, ?, ?, ?, ?, ?)");
				pstmt.setLong(1, img.getId());
				pstmt.setTimestamp(2, img.getCaptureTime());
				pstmt.setString(3, img.getUuid().toString());
				pstmt.setBlob(4, convertImageToBlob(img.getThumbnail()));
				pstmt.setLong(5, img.getCamId());
				pstmt.executeUpdate();
			} else {
				PreparedStatement pstmt = con.prepareStatement("update camImages set caputreTime = ?, name = ?, thumbnail = ?, camId = ? where id = ?");
				pstmt.setTimestamp(1, img.getCaptureTime());
				pstmt.setString(2, img.getUuid().toString());
				pstmt.setBlob(3, convertImageToBlob(img.getThumbnail()));
				pstmt.setLong(4, img.getCamId());
				pstmt.setLong(5, img.getId());
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
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
			PreparedStatement pstmt = con.prepareStatement("select id, captureTime, name, thumbnail, camId from camImages where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				CamImage camImage = new CamImage();
				camImage.setId(rs.getLong("id"));
				camImage.setCaptureTime(rs.getTimestamp("captureTime"));
				camImage.setUuid(UUID.fromString(rs.getString("UUID")));
				camImage.setThumbnail(convertBlobToImage(rs.getBlob("thumbnail")));
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
			int w = 120;
			int h = 90;
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
	
	@Override
	public void saveCaputredImage(BufferedImage img, Long camId) {
		// TODO:"caputred" zu "captured" ändern
		// TODO: UUID generieren
		UUID uuid = UUID.randomUUID();
		
		// TODO: Datensatz speichern
	
	}
		// TODO: BufferedImage img an richtiger Stelle abspeichern
	
	
	@Override
	public List<CamImage> getBetween(Timestamp start, Timestamp end) {
		if (start == null || end == null) {
			throw new IllegalArgumentException("start or end is null");
		}
		
		Connection con = null;
		try {
			con = jndi.getConnection(connectionString);
			PreparedStatement pstmt = con.prepareStatement("select id where CaptureTime > ? AND CaputreTime < ?");
			pstmt.setTimestamp(1, start);
			pstmt.setTimestamp(2, end);
			ResultSet rs = pstmt.executeQuery();
			List<CamImage> camImages = new ArrayList<CamImage>();
			while (rs.next()) {
				camImages.add(get(rs.getLong("id")));
			}
			return camImages;
		} catch (Exception e) {
			throw new CamImageNotFoundException();
		} finally {
			closeConnection(con);
		}
		
	}
	
	private void saveCamImageToFile(CamImage camImg) {
		try{
			BufferedImage bufImg = camImg.getThumbnail();
			//File outImg = new File(UUID.fromString(Integer.toString(camImg.getCaptureTime()) + ".png"));
			// TODO: prefix nicht vergessen
			File outImg = new File(camImg.getPath());
			ImageIO.write(bufImg, "png", outImg); 
		}
		catch(IOException e){
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
