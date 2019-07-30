import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Account")
public class AccountHome extends HttpServlet {
	private static final long serialVersionUID=1l;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException,IOException{
		Connection con = null;
	    Statement stmt = null;
	    ResultSet rs = null;
		String JDBCUrl = "jdbc:oracle:thin:@ee417.c7clh2c6565n.eu-west-1.rds.amazonaws.com:1521:EE417";
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		HttpSession sesh=request.getSession(true);
		User user=(User) sesh.getAttribute("UserEmail");
		
		if(user==null) 
			response.sendRedirect("/Test/index.html");
		
		try {
     		System.out.println("\nConnecting to the SSD Database......");
     		Class.forName("oracle.jdbc.driver.OracleDriver");
     		con = DriverManager.getConnection(JDBCUrl, "ee_user", "ee_pass");
     		System.out.println("succesfull");
    		stmt = con.createStatement();
    		
		}
 	catch (Exception e) {
 		System.out.println("Error " +e);
 	}   	     		

			out.println("<html>\n" + 
					"<head>\n" + 
					"<meta charset=\"UTF-8\">\n" + 
					"<link rel=\"stylesheet\" type=\"text\\css\" href=\"Upage.css\">\n" + 
					"<title>Account</title>\n" + 
					"</head>\n" + 
					"<script>\n" + 
					"</script>\n" + 
					"<body>\n" + 
					"<h1>Account </h1>\n" + 
					"<p1 >Welcome to the NuLibray service you will be able to use this service \n" + 
					"if you are a student, academic, staff or alumni of the Dublin City university</p1>\n" );
			

			try {
				rs = stmt.executeQuery("SELECT * FROM JP_BOOK");
				while(rs.next()) {
					out.println("<br>"
							+ "<br>"
							+ "<table style=\"width:100%\\>"
							+ "<tr>"
							+ "<th>TITLE</th>"
							+ "<th>AUTHOR</th>"
							+ "<th>BOOK_NO</th> </tr>"
							+ "\"<tr>\"\n "); 
					out.println(
						"					+ \"<tr><th>"+rs.getString("TITLE") +"</th>\"" + 
						"					+ \"<th>"+rs.getString("AUTHOR")+"</th>\"" + 
						"					+ \"<th>"+rs.getString("ID")+"</th> </tr></table>" );}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

					out.println("<br>\n" + 
							"<br>\n" +
							"<p2>test "  +user.getEmail()+"</p2>"+
					"<br>\n" + 
					"<br>\n" + 
					"</body>\n" + 
					"</html>");
			
			System.out.println("test2 "  +user.getEmail());	
		

		out.close();
	}

}


