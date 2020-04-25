package com.edu.deep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet{

	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");
		String sql="select * from login";
		boolean loginFlag=false;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/servletstudy","root","root");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(sql);
		while(rs.next()) {
			if(rs.getString(2).equals(uname)&&rs.getString(3).equals(pwd)) {
				loginFlag=true;
			}
		}
		
		if(loginFlag) {
			res.sendRedirect("homepage.jsp");
		}else {
			res.sendRedirect("Login.jsp");
		}
		
		}catch(Exception e) {
			
			System.out.println();
		}
		
	}
	
}
