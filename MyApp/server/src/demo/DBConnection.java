package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
	private static String driverName="com.mysql.jdbc.Driver"; //驱动程序名
	private static String userName="root";   //数据库用户名
	private static String userPwd="root";  //数据库密码
	private static String dbName="wehelp";  //数据库名
	
	public static Connection getDBConnection(){
		String url1="jdbc:mysql://localhost:3306/"+dbName;
		String url2="?user="+userName+"&password="+userPwd;
		String url3="&useUnicode=true&characterEncoding=UTF-8&useSSL=true";
		String url=url1+url2+url3;
		try{
			Class.forName(driverName);   //加载注册驱动程序
			Connection conn=DriverManager.getConnection(url);
			return conn;
		}catch(Exception e){e.printStackTrace();}
		return null;
	}
	
	public static void closeDB(Connection conn,PreparedStatement ps,ResultSet rs){
		try{
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		}catch(Exception e){e.printStackTrace();}
	}
}
