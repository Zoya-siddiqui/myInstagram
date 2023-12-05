package insta;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uname=req.getParameter("uname");
		String password=req.getParameter("pass");
		PrintWriter out=resp.getWriter();
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/instgram","root","Zoya@123");
			String quert="Select * from mytable where username=? and password= ?";
			PreparedStatement ps=con.prepareStatement(quert);
			ps.setString(1, uname);
			ps.setString(2,password);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				//for display detail on anuther page
				HttpSession ses=req.getSession();
				ses.setAttribute("session_name", rs.getString("name"));
				resp.setContentType("text/html");
				out.print("<h2 style='color:green'>User login sucessfully</h2>");
				RequestDispatcher rd=req.getRequestDispatcher("/profile.jsp");
				rd.include(req, resp);
			
					
			}
			else
			{
				resp.setContentType("text/html");
				out.print("<h2 style='color:red'>User not find please login first</h2>");
				RequestDispatcher rd=req.getRequestDispatcher("/login.jsp");
				rd.include(req, resp);
			}
				
		}
		catch(Exception e)
		{
			resp.setContentType("text/html");
			out.print("<h2 style='color:red'>Zoya kiya kr rhi hai pgl hai tu</h2>"+e.getMessage());
			RequestDispatcher rd=req.getRequestDispatcher("/signup.jsp");
			rd.include(req, resp);
		}
	}

}
