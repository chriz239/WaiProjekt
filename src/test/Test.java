package test;

import dao.DaoFactory;
import dao.UserDao;
import models.User;

public class Test {

	public static void main(String[] args) {
		User user = new User();
		user.setName("chris");
		user.setPasswort("123qwe");
		
		UserDao userDao = DaoFactory.getUserDao();
		userDao.save(user);
	}

}
