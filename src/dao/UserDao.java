package dao;

import java.util.List;

import models.User;

public interface UserDao  {
	public void save(User user);
	public void delete(Long id);
	public User get(Long id);
	public User getUserByName(String name);
	public List<User> getAll();
}
