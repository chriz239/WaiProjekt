package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jndi.JndiFactory;

public class UserCamAccessImpl implements UserCamAccess {

	private final JndiFactory jndi = JndiFactory.getInstance();
	private final String connectionString = "jdbc/CamDB";
	
	@Override
	public void add(long userid, long camid) {
		Connection con = null;
		try {
			con = jndi.getConnection(connectionString);
			
			// check if entry already exists
			PreparedStatement pstmt = con.prepareStatement("select * from usercamaccess where userid = ? AND camid = ?");
			pstmt.setLong(1, userid);
			pstmt.setLong(2, camid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// entry already exists so just return
				return;
			}
			
			// entry doesn't exist so add it
			pstmt = con.prepareStatement("insert into usercamaccess (userid, camid) values (?, ?)");
			pstmt.setLong(1, userid);
			pstmt.setLong(2, camid);
			pstmt.executeQuery();
		} catch (Exception ex) {
			
		} finally {
			closeConnection(con);
		}
	}

	@Override
	public boolean hasAccess(long userid, long camid) {
		Connection con = null;
		try {
			con = jndi.getConnection(connectionString);
			PreparedStatement pstmt = con.prepareStatement("select * from usercamaccess where userid = ? AND camid = ?");
			pstmt.setLong(1, userid);
			pstmt.setLong(2, camid);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (Exception ex) {
			return false;
		} finally {
			closeConnection(con);
		}
	}

	@Override
	public List<Long> getCamsOfUser(long userid) {
		Connection con = null;
		try {
			con = jndi.getConnection(connectionString);
			PreparedStatement pstmt = con.prepareStatement("select * from usercamaccess where userid = ?");
			pstmt.setLong(1, userid);
			ResultSet rs = pstmt.executeQuery();
			List<Long> camIds = new ArrayList<Long>();
			while(rs.next()) {
				camIds.add(rs.getLong("camid"));
			}
			return camIds;
		} catch (Exception ex) {
			return null;
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
