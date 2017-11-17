package dao;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.CamNotFoundException;
import jndi.JndiFactory;
import models.Cam;

public class CamDaoImpl implements CamDao{

	private final JndiFactory jndi = JndiFactory.getInstance();
	private final String connectionString = "jdbc/CamDB";
	
	@Override
	public void save(Cam cam) {
		if (cam == null) {
			throw new IllegalArgumentException("cam can't be null");
		}
		
		Connection con = null;
		try {
			con = jndi.getConnection(connectionString);
			if (cam.getId() == null) {
				PreparedStatement pstmt = con.prepareStatement("insert into cams (name, url) values (?,?)");
				pstmt.setString(1, cam.getName());
				pstmt.setString(2, cam.getUrl().toString());
				pstmt.executeUpdate();
			} else {
				PreparedStatement pstmt = con.prepareStatement("update cams set name = ?, url = ? where id =?");
				pstmt.setString(1, cam.getName());
				pstmt.setString(2, cam.getUrl().toString());
				pstmt.setLong(3, cam.getId());
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
			PreparedStatement pstmt = con.prepareStatement("delete from cams where id = ?");
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeConnection(con);
		}
	}

	@Override
	public Cam get(Long id) {
		if (id == null)
			throw new IllegalArgumentException("id can't be null");
		
		Connection con = null;
		try {
			con = jndi.getConnection(connectionString);
			PreparedStatement pstmt = con.prepareStatement("select id, name, url from cams where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Cam cam = new Cam();
				cam.setId(rs.getLong("id"));
				cam.setName(rs.getString("name"));
				cam.setUrl(new URL(rs.getString("url")));
				return cam;
			} else {
				throw new Exception("Cam not found");
			}
		} catch (Exception e) {
			throw new CamNotFoundException(id);
		} finally {
			closeConnection(con);
		}
	}

	@Override
	public List<Cam> getAll() {
		List<Cam> cams = new ArrayList<Cam>();
		
		Connection con = null;
		try {
			con = jndi.getConnection(connectionString);
			PreparedStatement pstmt = con.prepareStatement("select id, name, url from cams");
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Cam cam = new Cam();
				cam.setId(rs.getLong("id"));
				cam.setName(rs.getString("name"));
				cam.setUrl(new URL(rs.getString("url")));
				cams.add(cam);
			}
			return cams;
		} catch (Exception e) {
			throw new CamNotFoundException(0L);
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
