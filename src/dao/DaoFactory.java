package dao;

public class DaoFactory {
	public static CamDao getCamDao() {
		return new CamDaoImpl();
	}
	
	public static CamImageDao getCamImageDao() {
		return new CamImageDaoImpl();
	}
	
	public static UserDao getUserDao() {
		return new UserDaoImpl();
	}
}
