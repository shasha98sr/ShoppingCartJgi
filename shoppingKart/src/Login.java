import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

import javax.servlet.http.HttpSession; 

public class Login extends HttpServlet {


	private static final long serialVersionUID = 1L;
	String name;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");
       // String name = request.getParameter("name");
        HttpSession session=request.getSession();  
        if(session!=null)
		name=(String)session.getAttribute("uname"); 
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoplogin", "root", "");
            PreparedStatement pst = conn.prepareStatement("Select uname,pass from login where uname=? and pass=?");
            pst.setString(1, uname);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) 
            {   
            	RequestDispatcher rd=request.getRequestDispatcher("/frames.html");  
                rd.include(request, response); 
                
                
            } 
            else
            {
            	out.print("<h1 style='text-align:center;color:#757677;'>Sorry UserName or Password Error!</h1>");  
                RequestDispatcher rd=request.getRequestDispatcher("/signin.html");  
                rd.include(request, response);  
            }
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
        	out.println("Couldn't load database driver: " 
        		  + e.getMessage());
        }
         
    }
}