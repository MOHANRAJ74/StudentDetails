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

public class RegisterControl extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		JDBCConnection jdbc=new JDBCConnection();	
		Student stu=new Student();
		
		String rno=request.getParameter("rno");
		String sname=request.getParameter("sname");
		String fname=request.getParameter("fname");
		String mobile=request.getParameter("mobile");
		String email=request.getParameter("email");
		String dob=request.getParameter("dob");
		String gender=request.getParameter("gender");
		String region=request.getParameter("region");
		String edu=request.getParameter("edu");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String pincode=request.getParameter("pincode");
		
		try {
			Connection con=jdbc.getConnection();
			String sql="insert into student(rno,sname,fname,mob,email,dob,gender,region,educat,address,city,pincode)values(?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			
			int rollno=Integer.parseInt(rno);
			stu.setRno(rollno);
			stu.setSname(sname);
			stu.setFname(fname);
			stu.setMobile(mobile);
			stu.setEmail(email);
			stu.setDob(dob);
			stu.setGender(gender);
			stu.setRegion(region);
			stu.setEdu(edu);
			stu.setAddress(address);
			stu.setCity(city);
			int pin=Integer.parseInt(pincode);
			stu.setPincode(pin);
			
			ps.setInt(1, stu.getRno());
			ps.setString(2, stu.getSname());
			ps.setString(3, stu.getFname());
			ps.setString(4, stu.getMobile());
			ps.setString(5, stu.getEmail());
			ps.setString(6, stu.getDob());
			ps.setString(7, stu.getGender());
			ps.setString(8, stu.getRegion());
			ps.setString(9, stu.getEdu());
			ps.setString(10, stu.getAddress());
			ps.setString(11, stu.getCity());
			ps.setInt(12, stu.getPincode());
			
			int update=ps.executeUpdate();			
			if(update>0) {
				out.println("Registraion Success");
				RequestDispatcher rd=request.getRequestDispatcher("/ViewStudent");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd=request.getRequestDispatcher("/insertdata.html");
				rd.include(request, response);
				out.println("Registration Unsuccess");
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
