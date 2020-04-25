package com.edu.deep;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		System.out.println("Inside doPost Method");
		
		PrintWriter out=res.getWriter();
		String name=req.getParameter("name");
		String mobileno=req.getParameter("mobileno");
		String pwd=req.getParameter("pwd");
		String city=req.getParameter("city");
		String email=req.getParameter("email");
		
		String sql="insert into register values(?,?,?,?,?,?)";
		String sql1="insert into login values(?,?,?)";
		
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/servletstudy","root","root");
		int id=getId();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, id);	
		ps.setString(2, name);
		ps.setString(3, city);
		ps.setString(4, mobileno);
		ps.setString(5, email);
		ps.setString(6, pwd);
		ps.executeUpdate();
		ps=con.prepareStatement(sql1);
		ps.setInt(1, id);
		ps.setString(2, email);
		ps.setString(3, pwd);
		ps.executeUpdate();
		con.close();
		
		}catch(Exception e) {
			
			System.out.println("Inside Exception "+e);
			
		}
		
		res.sendRedirect("Login.jsp");
		
	}

	private int getId() {
		
		String sql="select * from register";
		int no=1;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/servletstudy","root","root");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(sql);
		
		while(rs.next()) {
			no=rs.getInt(1);
			no++;
		}
		
		}catch(Exception e) {
			System.out.println("Error in Connection "+e);
			no=1;
		}
		return no;
	}

}
