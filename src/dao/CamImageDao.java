package dao;

import java.awt.image.BufferedImage;
import java.util.List;

import models.CamImage;

public interface CamImageDao {
	public void save(CamImage img);
	public void delete(Long id);
	public CamImage get(Long id);
	public List<CamImage> getAll(); 
	public void saveCaputredImage(BufferedImage img);
}
