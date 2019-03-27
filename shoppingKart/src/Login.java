import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

import javax.servlet.http.HttpSession; 

public class Login extends HttpServlet {


	private static final long serialVersionUID = 1L;
	String name;
	

	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");
       // String name = request.getParameter("name");
        
    	       // session.setAttribute("CusName",name);
    	        
    	  
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoplogin", "root", "");
            PreparedStatement pst = conn.prepareStatement("Select uname,pass,name from login where uname=? and pass=?");
            pst.setString(1, uname);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) 
            {  String name=rs.getString("name");
            	//out.print("<a>hello "+name+"</a>");
            HttpSession session=request.getSession(true);  
            session.setAttribute("UsrName",name);
           String usname=(String) session.getAttribute("UsrName");
          // out.print("<a>hello "+usname+"</a>");
           		out.print("<style>div{background-color:#333;}");
           		out.print("a{float:right;}</style>");
            	out.print("<div><h2>Welcome "+usname+"<a href='#logout'>Logout</a></h2></div>");
            	RequestDispatcher rd=request.getRequestDispatcher("/pre.html");  
                rd.include(request, response); 
                
				//out.print("<input type='hidden' class='cname' id='cname' value=cname>");
                
                
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
      /*  else {
        	RequestDispatcher rd=request.getRequestDispatcher("/pre.html");  
            rd.include(request, response); 
            out.print("Welcome ");
            
        }*/
         
    }
}