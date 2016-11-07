package demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;



/*
 * ��¼������user_name,��ѯ���ݿ⣬�����¼��û�о�ʧ��
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
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
		String sql="select * from user where user_name=? and user_password=?";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DBConnection.getDBConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1,user_name);
			ps.setString(2,user_password);
			rs=ps.executeQuery();
			//true,���¼��֤�ɹ�
			if(rs.next()){       
				JsonObject json=new JsonObject();
				json.addProperty("flag", "true");
				json.addProperty("user_id", rs.getString(1));
				response.getWriter().write(json.toString());
			}
			//��֤ʧ�ܣ�����flag=false
			else{
				JsonObject json=new JsonObject();
				json.addProperty("flag", "false");
				json.addProperty("user_id", "-1");
				response.getWriter().write(json.toString());
			}
		} catch (SQLException e) {
		}finally{
			DBConnection.closeDB(conn, ps, rs);
		}
    }
}
