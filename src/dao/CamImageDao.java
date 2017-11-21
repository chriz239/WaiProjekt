package dao;

import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.util.List;

import models.CamImage;

public interface CamImageDao {
	public void save(CamImage img);
	public void delete(Long id);
	public CamImage get(Long id);
	public void saveCapturedImage(BufferedImage img, Long camId);
	public List<CamImage> getBetween(Timestamp start, Timestamp end);
}
