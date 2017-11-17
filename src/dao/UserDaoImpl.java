package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.UserNotFoundException;
import jndi.JndiFactory;
import models.User;

public class UserDaoImpl implements UserDao {

	private final JndiFactory jndi = JndiFactory.getInstance();
	private final String connectionString = "jdbc/CamDB";
	
	@Override
	public void save(User user) {
		if (user == null) {
			throw new IllegalArgumentException("user can't be null");
		}
		
		Connection con = null;
		try {
			con = jndi.getConnection(connectionString);
			if (user.getId() == null) {
				PreparedStatement pstmt = con.prepareStatement("insert into users (name, password) values (?,?)");
				pstmt.setString(1, user.getName());
				pstmt.setString(2, user.getPasswort());
				pstmt.executeUpdate();
			} else {
				PreparedStatement pstmt = con.prepareStatement("update users set name = ?, password = ? where id = ?");
				pstmt.setString(1, user.getName());
				pstmt.setString(2, user.getPasswort());
				pstmt.setLong(3, user.getId());
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
			throw new IllegalArgumentException("Id can't be null");
		}
		
		Connection con = null;
		try {
			con = jndi.getConnection(connectionString);
			PreparedStatement pstmt = con.prepareStatement("delete from users where id = ?");
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeConnection(con);
		}
	}

	@Override
	public User get(Long id) {
		if (id == null)
			throw new IllegalArgumentException("id can't be null");
		
		Connection con = null;
		try {
			con = jndi.getConnection(connectionString);
			PreparedStatement pstmt = con.prepareStatement("select id, name, password from users where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setPasswort(rs.getString("password"));
				return user;
			} else {
				throw new Exception("User not found");
			}
		} catch (Exception e) {
			throw new UserNotFoundException();
		} finally {
			closeConnection(con);
		}
	}

	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<User>();
		
		Connection con = null;
		try {
			con = jndi.getConnection(connectionString);
			PreparedStatement pstmt = con.prepareStatement("select id, name, password from users");
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setPasswort(rs.getString("password"));
				users.add(user);
			}
			return users;
		} catch (Exception e) {
			throw new UserNotFoundException();
		} finally {
			closeConnection(con);
		}
	}
	
	private void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				// nothing to do
				e.printStackTrace();
			}				
		}
	}
}
