package com.shiwang.app.classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class DbServlet
 */
@WebServlet("/DbServlet")
public class DbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name="jdbc/web_student_tracker")
	private DataSource datasource;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//set up the PrintWriter method
		PrintWriter out=response.getWriter();
		response.setContentType("text/plain");
		
		//setting up the conneciton
		Connection dbConn= null;
		Statement statement= null;
		ResultSet resultset=null;
		
		try {
			dbConn= datasource.getConnection();
			statement= dbConn.createStatement();
			String myquery="select * from student";
			resultset=statement.executeQuery(myquery);
			
			while(resultset.next()) {
				String email=resultset.getString("email");
				out.println(email);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
