package dao;

import java.util.List;

import models.Cam;

public interface CamDao {
	public void save(Cam cam);
	public void delete(Long id);
	public Cam get(Long id);
	public List<Cam> getAll();
}
