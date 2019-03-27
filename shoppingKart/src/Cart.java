

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String pname = request.getParameter("pname");
        String qty = request.getParameter("qty");
        String price = request.getParameter("price");
        String pagename = request.getParameter("pagename");
        request.setAttribute("originalRequest", request.getRequestURL());
        

        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoplogin", "root", "");
            PreparedStatement pst = conn.prepareStatement("insert into cart values(?,?,?)");
            pst.setString(1, pname);
            pst.setString(2, qty);
            pst.setString(3, price);
            pst.executeUpdate();
            RequestDispatcher rd=request.getRequestDispatcher(pagename);  
            rd.include(request, response);
         
          out.print("<style>"
            		+ "h3{text-align:center; padding:20px; background-color: #013802;"
            		+ "color: white; position:absolute; top:400px !important;"
            		+ "animation: fadeIn 6s;-webkit-animation: fadeIn 6s; animation-duration: 6s;animation-fill-mode: forwards;  }"
            		+ "@keyframes fadeIn {from {opacity: 1;}to {opacity:0 ;}" 
            		+"}"
            		+ "</style>");
             out.print("<div style='float:right'><h3>"+pname+" added to cart!</h3></div>"); 
        }
        catch (ClassNotFoundException | SQLException e) 
        {
        	out.println("Couldn't load database driver: " 
        			  + e.getMessage());
        }
        
	}

}
