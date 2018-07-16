

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.DBManager;
import com.database.UserData;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
      
    }

    
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		  System.out.println("FirstServlet初始化...");
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		String username=request.getParameter("username");
		String userpwd=request.getParameter("userpwd");
	
		System.out.println("用户名"+username);
		System.out.println("密码:"+userpwd);
		
		boolean type=false;//用于判断账号和密码是否与数据库中查询结果一致  
        response.setContentType("text/html; charset=UTF-8");  
        PrintWriter out = response.getWriter();  
        UserData userData=UserData.createInstance();
        try  
        {  
            Connection con=DBManager.getConnection();  
            Statement stmt=con.createStatement();  
	        String sql="select * from user_data where username='"+username+"' and password='"+userpwd+"'"; 
	        ResultSet rs=stmt.executeQuery(sql);  
            while(rs.next())  
            {  
                type=true;  
                userData.setID(rs.getInt(1));
                userData.setDeviceID(rs.getInt(5));
                userData.setNickname(rs.getString(3));
                userData.setUsername(username);
            }  
        }  
        catch(Exception ex)  
        {  
            ex.printStackTrace();  
        }  
        finally  
        {  
        	//关闭数据库连接
           DBManager.Close();  
           //返回结果
            //out.print(type);  
           if(type) {
        	   //UserData userData=UserData.createInstance();
        	  // userData.setAllData(1, 1001, "sume520", "乱花语");
        	   System.out.printf("{\"ID\":\"%d\",\"deviceID\":\"%d\",\"username\":\"%s\",\"nickname\":\"%s\"}", 
        			   userData.getID(),userData.getDeviceID(),userData.getUsername(),userData.getNickname());
        	   out.print(String.format("{\"ID\":\"%d\",\"deviceID\":\"%d\",\"username\":\"%s\",\"nickname\":\"%s\"}", 
        			   userData.getID(),userData.getDeviceID(),userData.getUsername(),userData.getNickname()));
           }else {
        	   out.print("false");
           }
            out.flush();  
            out.close();  
        }  
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		DBManager.Close();
		super.destroy();
		System.out.println("FirstServlet被摧毁");
	}
	
	
}
