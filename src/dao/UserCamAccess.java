package dao;

import java.util.List;

public interface UserCamAccess {
	public void add(long userid, long camid);
	public boolean hasAccess(long userid, long camid);
	public List<Long> getCamsOfUser(long userid);
}
