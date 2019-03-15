
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	    {
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        String UserName = request.getParameter("first_name");
	        String password = request.getParameter("password");
	        try 
	        {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/miniproject", "root", "admin");
	            PreparedStatement pst = conn.prepareStatement("Select first_name,password from users where first_name=? and password=?");
	            pst.setString(1 ,UserName);
	            pst.setString(2, password);
	            ResultSet rs = pst.executeQuery();
	            if (rs.next()) 
	            {
	                out.println("Correct login credentials" + UserName);
	            } 
	            else
	            {
	                out.println("Incorrect login credentials");
	            }
	        } 
	        catch (ClassNotFoundException | SQLException e) 
	        {
	            e.printStackTrace();
	        }
	    }
            
            

 	    }

