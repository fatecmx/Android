package demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

/**
 * 注册：获得user_name，查询数据库，有则提示已被注册，没有就注册
 */
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SignInServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String user_name=request.getParameter("user_name");
		String user_password=request.getParameter("user_password");
		User user=new User();
		User user1=new User();
		UserDAO uDAO=new UserDAO();
		String sql="select * from user where user_name=?";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DBConnection.getDBConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1,user_name);
			rs=ps.executeQuery();
			if(rs.next()){       //true,则用户名被注册
				JsonObject json=new JsonObject();
				json.addProperty("flag", "false");
				json.addProperty("user_id", "-1");
				response.getWriter().write(json.toString());
				return;
			}
			//没有，则注册
			user.setUser_id(UUID.randomUUID().toString());
			user.setUser_name(user_name);
			user.setUser_password(user_password);
			try {
				user1=uDAO.create(user);
			} catch (Exception e) {}
			JsonObject json=new JsonObject();
			json.addProperty("flag", "true");
			json.addProperty("user_id",user1.getUser_id());
			response.getWriter().write(json.toString());
		} catch (SQLException e) {
		}finally{
			DBConnection.closeDB(conn, ps, rs);
		}
	}
}
