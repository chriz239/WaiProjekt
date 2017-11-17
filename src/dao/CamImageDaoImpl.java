package dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

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
				PreparedStatement pstmt = con.prepareStatement("insert into camImages (id, captureTime, name, thumbnail, camId) values (?, ?, ?, ?, ?)");
				pstmt.setLong(1, img.getId());
				pstmt.setTimestamp(2, img.getCaptureTime());
				pstmt.setString(3, img.getName());
				pstmt.setBlob(4, convertImageToBlob(img.getThumbnail()));
				pstmt.setLong(5, img.getCamId());
				pstmt.executeUpdate();
			} else {
				PreparedStatement pstmt = con.prepareStatement("update camImages set caputreTime = ?, name = ?, thumbnail = ?, camId = ? where id = ?");
				pstmt.setTimestamp(1, img.getCaptureTime());
				pstmt.setString(2, img.getName());
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
				camImage.setName(rs.getString("name"));
				camImage.setThumbnail(convertBlobToImage(rs.getBlob("thumbnail")));
				camImage.setCamId(rs.getLong("camId"));
				return camImage;
			} else {
				throw new Exception("Image not found");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeConnection(con);
		}
	}
	
	@Override
	public void saveCaputredImage(BufferedImage img) {
		// TODO: thumbnail generieren
		
		// TODO: UUID generieren
		
		// TODO: Datensatz speichern
		
		// TODO: BufferedImage img an richtiger Stelle abspeichern
	}
	
	@Override
	public List<CamImage> getBetween(Timestamp start, Timestamp end) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void saveCamImageToFile(CamImage camImg) {
		try{
			BufferedImage bufImg = camImg.getThumbnail();
			File outImg = new File(UUID.fromString(Integer.toString(camImg.getCaptureTime()) + ".png"));
			ImageIO.write(bufImg, "png", outImg); 
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private BufferedImage loadCamImageFromFile(CamImage camImg) {
		return null;
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
		return null;
		
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
