package com.silicon.crud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.silicon.core.JDBCConnection;
import com.silicon.model.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteStudentControl")
public class DeleteStudentControl extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		JDBCConnection jdbc=new JDBCConnection();
		Student stu=new Student();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Delete Student</title>");
		out.println("</head><body>");
		out.println("<center>");
		out.println("<h2>Delete Student</h2>");	
		try {
			Connection con=jdbc.getConnection();
			String sql="delete from student where rno=?";
			PreparedStatement ps=con.prepareStatement(sql);
			String rno=request.getParameter("delstudent");
			int rollno=Integer.parseInt(rno);
			ps.setInt(1, rollno);
			
			int update=ps.executeUpdate();
			if(update>0) {
				RequestDispatcher rd=request.getRequestDispatcher("ViewStudent");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd=request.getRequestDispatcher("DeleteStudent");
				rd.include(request, response);
			}
			
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
