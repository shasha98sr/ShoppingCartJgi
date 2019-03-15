
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/myServlet")
public class myServlet extends HttpServlet {
	private String message;
	public void init() throws ServletException{
		message= "hello world";
	}
    public myServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
	     out.println(message);
	     
	     
	     String query =" SELECT * FROM Login";


	        Statement myStatement = null;
	        myStatement = con.createStatement();
	        ResultSet result = myStatement.executeQuery(query);

	        while(result.next()){
	            System.out.println("User name = " + result.getString("userID"));
	            System.out.println("User password = " + result.getString("userPassword"));
	}

}
