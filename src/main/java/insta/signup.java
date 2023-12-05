package insta;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class signup
 */
public class signup extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("emailmob");
		String name=req.getParameter("name");
		String ussername=req.getParameter("uname");
		String pass=req.getParameter("pass");
		
		PrintWriter out=resp.getWriter();
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/instgram","root","Zoya@123");
			String query="insert into mytable values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, name);
			ps.setString(3, ussername);
			ps.setString(4, pass);
			
			int count =ps.executeUpdate();
			if(count>0) {
				resp.setContentType("text/html");
				out.println("<h2 style='color:green'>user register sucessfully</h2>");
				RequestDispatcher rd=req.getRequestDispatcher("/signup.jsp");
				rd.include(req, resp);
			
			}
			else {
				resp.setContentType("text/html");
				out.println("<h2 style='color:green'>user not registerd sucessfully</h2>");
				RequestDispatcher rd=req.getRequestDispatcher("/signup.jsp");
				rd.include(req, resp);
			}
		
			
		}
		catch(Exception e)
		{
			resp.setContentType("text/html");
			out.println("<h2 style='color:red'>error occerd</h2>"+e.getMessage());
			RequestDispatcher rd=req.getRequestDispatcher("/signup.jsp");
			rd.include(req, resp);
		}
	}

}
