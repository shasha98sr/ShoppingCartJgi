

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public User() {
        super();
        
    }

	
	
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        String uname = request.getParameter("UName");
	        String pass = request.getParameter("Password");
	        String name = request.getParameter("Name");
	        HttpSession session=request.getSession();
	        if((uname!="")&&(uname!="null")) {
	        session.setAttribute("UsrName",uname);
	        session.setAttribute("CusName",name);
	        }
	        try 
	        {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoplogin", "root", "");
	            PreparedStatement pst = conn.prepareStatement("insert into login values(?,?,?)");
	            pst.setString(1, uname);
	            pst.setString(2, pass);
	            pst.setString(3, name);
	            pst.executeUpdate();
	            
                
	        }
	        catch (ClassNotFoundException | SQLException e) 
	        {
	        	out.println("Couldn't load database driver: " 
	        			  + e.getMessage());
	        }
	        
	        
	        RequestDispatcher rd=request.getRequestDispatcher("pre.html");  
            rd.include(request, response); 
	}

}
