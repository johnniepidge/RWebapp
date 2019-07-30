import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID=1L;
	Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
	String JDBCUrl = "jdbc:oracle:thin:@ee417.c7clh2c6565n.eu-west-1.rds.amazonaws.com:1521:EE417";
    Vector<User> UDB = new Vector<User>();
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	response.setContentType("text/html");
	PrintWriter out=response.getWriter();
	
		User user =checkDB(request.getParameter("emailinput"),request.getParameter("passwordinput"));
		if(user!=null) {
			
			HttpSession sesh =request.getSession();
			sesh.setAttribute("UserEmail", user);
			response.sendRedirect("/Test/Account");
			
		}
		else {
			response.sendRedirect("/Test/index.html");
		}
		out.close();
	}
	
	private User checkDB(String username,String password) {
		try {
     		System.out.println("\nConnecting to the SSD Database......");
     		Class.forName("oracle.jdbc.driver.OracleDriver");
     		con = DriverManager.getConnection(JDBCUrl, "ee_user", "ee_pass");
     		
		}
 	catch (Exception e) {
 		System.out.println("Error " +e);
 	}   

	try {

	     		stmt = con.createStatement();
 		rs = stmt.executeQuery("SELECT * FROM JP_USERS");
 		
 		while(rs.next()) {
// 		System.out.println("hello" + rs.getString("SURNAME")+rs.getString("FIRSTNAME")+rs.getString("EMAIL")+rs.getString("PASSWORD"));
 			UDB.add(new User(rs.getString("FIRSTNAME"),rs.getString("SURNAME"),rs.getString("EMAIL"),rs.getString("PASSWORD"),rs.getBoolean("ADMIN")));}
 		
 		Enumeration<User> e=UDB.elements();
 		while(e.hasMoreElements()) {
 			User user=(User) e.nextElement();
 			if(user.getEmail().equals(username) && user.getPword().equals(password)) {
 				return user;
 			}
 		}

	}
	
catch (Exception e) {
 		
}   
 	
finally {
 try {    
    if (rs != null) rs.close();
 	if (stmt != null) stmt.close();
 	if (con != null) con.close();
 }
 catch (Exception ex) {

 }
}
		return null;
	}

}
