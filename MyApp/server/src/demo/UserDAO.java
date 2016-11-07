package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO{
	protected static final String FIELDS_INSERT="user_id,user_name,user_password";
	protected static String INSERT_SQL="insert into user("+FIELDS_INSERT+")"+"values(?,?,?)";
	protected static String SELECT_SQL="select"+ FIELDS_INSERT +"from user where user_id=?";
	protected static String UPDATE_SQL="update user set user_id=?,user_name=?,user_password=?";
	protected static String DELETE_SQL="delete from user where user_id=?"; 
	@Override
	public User create(User user) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=DBConnection.getDBConnection();
			ps=conn.prepareStatement(INSERT_SQL);
			ps.setString(1,user.getUser_id());
			ps.setString(2,user.getUser_name());
			ps.setString(3,user.getUser_password());
			ps.executeUpdate();
		}catch(Exception e){
		}finally{
			DBConnection.closeDB(conn,ps,rs);
		}
		return user;
	}
	
	@Override
	public void remove(User user) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;	
		ResultSet rs=null;
		try{
			conn=DBConnection.getDBConnection();
			ps=conn.prepareStatement(DELETE_SQL);
			ps.setString(1,user.getUser_id());
			ps.executeUpdate();
		}catch(Exception e){
		}finally{
			DBConnection.closeDB(conn,ps,rs);
		}
	}
	@Override
	public User find(User user) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User user1=null;
		try{
			conn=DBConnection.getDBConnection();
			ps=conn.prepareStatement(SELECT_SQL);
			ps.setString(1,user.getUser_id());
			rs=ps.executeQuery();
			if(rs.next()){
				user1=new User();
				user1.setUser_id(rs.getString(1));
				user1.setUser_name(rs.getString(2));
				user1.setUser_password(rs.getString(3));
			}
		}catch(Exception e){
		}finally{
			DBConnection.closeDB(conn,ps,rs);
		}
		return user1;
	}
	@Override
	public List<User> findAll() throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<User> user1=new ArrayList<User>();
		conn=DBConnection.getDBConnection();
		ps=conn.prepareStatement("select * from user");
		rs=ps.executeQuery();
		while(rs.next()){
			User user2=new User();
			user2.setUser_id(rs.getString(1));
			user2.setUser_name(rs.getString(2));
			user2.setUser_password(rs.getString(3));
			user1.add(user2);
		}
		DBConnection.closeDB(conn,ps,rs);
		return user1;
	}
	@Override
	public void update(User user) throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=DBConnection.getDBConnection();
			ps=conn.prepareStatement(UPDATE_SQL);
			ps.setString(1, user.getUser_id());
			ps.setString(2,user.getUser_name());
			ps.setString(3,user.getUser_password());
			int rowCount=ps.executeUpdate();
			if(rowCount==0){
				throw new Exception("Update Error:User Id:"+user.getUser_id());
				}
		}catch(Exception e){
			}finally{
				DBConnection.closeDB(conn,ps,rs);
			}
	}
}

