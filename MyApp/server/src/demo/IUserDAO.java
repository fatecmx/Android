package demo;

import java.util.List;

public interface IUserDAO {
	public abstract User create(User user) throws Exception; //添加记录
	public abstract void remove(User user) throws Exception; //删除记录
	public abstract User find(User user) throws Exception;  //查询记录
	public abstract List<User> findAll() throws Exception;  //查询所有记录
	public abstract void update(User user) throws Exception; //修改记录
}
