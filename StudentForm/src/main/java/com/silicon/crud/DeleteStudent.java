package com.silicon.crud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.silicon.core.JDBCConnection;
import com.silicon.model.Student;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteStudent")
public class DeleteStudent extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		JDBCConnection jdbc=new JDBCConnection();
		Student stu=new Student();
		String sql="select * from student";
		out.println("<html><head>");
		out.println("<title>Delete Student</title>");
		out.println("</head><body>");		
		out.println("<form action='DeleteStudentControl' method='get' align='right'>");
		out.println("<input type='text' name='delstudent' placeholder='Rollno	'>");
		out.println("<input type='submit' value='Remove'>");
		
		out.println("<body><center>");
		out.println("<h3>Student Details</h3>");

        
		try {
			Connection con=jdbc.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
	        out.println("<table border=4 width=85% height=15%>");  
	        out.println("<tr><th>Roll No</th><th>Student Name</th><th>Father' Name</th><th>Mobile No</th><th>E-Mail</th><th>Date of Birth</th>"
	        		+ "<th>Gender</th><th>Region</th><th>Education</th><th>Address</th><th>City</th><th>Pincode</th></tr>"); 
			while(rs.next()) {
				stu.setRno(rs.getInt(1));
				stu.setSname(rs.getString(2));
				stu.setFname(rs.getString(3));
				stu.setMobile(rs.getString(4));
				stu.setEmail(rs.getString(5));
				stu.setDob(rs.getString(6));
				stu.setGender(rs.getString(7));
				stu.setRegion(rs.getString(8));
				stu.setEdu(rs.getString(9));
				stu.setAddress(rs.getString(10));
				stu.setCity(rs.getString(11));
				stu.setPincode(rs.getInt(12));
				
				int rollno=stu.getRno();
				String sname=stu.getSname();
				String fname=stu.getFname();
				String mobile=stu.getMobile();
				String email=stu.getEmail();
				String dob=stu.getDob();
				String gender=stu.getGender();
				String region=stu.getRegion();
				String educat=stu.getEdu();
				String address=stu.getAddress();
				String city=stu.getCity();
				int pincode=stu.getPincode();
						
				out.println("<tr><td>" + rollno + "</td><td>" + sname + "</td><td>" + fname + "</td><td>" +mobile + "</td><td>" + email + "</td><td>" + dob + "</td>"
		               		+ "<td>" + gender + "</td><td>" + region+ "</td><td>" + educat + "</td><td>" + address + "</td><td>" + city + "</td><td>" + pincode + "</td></tr>");
			}
			con.close();
			out.println("</table>");
			out.println("</center></body></html>");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}