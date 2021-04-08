package com.shiwang.app.classes;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.sql.DataSource;

public class StudentDbUtil {

	private DataSource dsource;
	
	public StudentDbUtil(DataSource datasource) {
		this.dsource=datasource;
	}
	
	public List<Student> getStudents() throws SQLException{
		
	List<Student> studentlist= new ArrayList<>();
		Connection dbConn= null;
		Statement statement= null;
		ResultSet resultset=null;
	
			try {
				//get database connection
				dbConn= dsource.getConnection();
				statement= dbConn.createStatement();
				String myquery="select * from student order by id";
				//getting up the resultset
				resultset=statement.executeQuery(myquery);
				
				while(resultset.next()) {
					Long sid=resultset.getLong("id");
					String fname=resultset.getString("first_name");
					String lname=resultset.getString("last_name");
					String email=resultset.getString("email");
					studentlist.add(new Student(sid,fname,lname,email));
				}
				
			
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(resultset!=null) {
					resultset.close();	
				}
				if(statement!=null) {
					statement.close();	
				}
				if(dbConn!=null) {
					dbConn.close();	
				}
			}
			
		return studentlist;
	}

	public boolean addStudent(Student student) throws SQLException {
		// TODO Auto-generated method stub
		Connection dbConn= null;
		Statement statement= null;
		ResultSet resultset=null;
			try {
				//get database connection
				dbConn= dsource.getConnection();
				statement= dbConn.createStatement();
				String myquery="insert into student (first_name, last_name, email) values ('"+student.getFirstName()+"', '"+student.getLastName() +"', '"+student.getEmail()+"')";
				//getting up the resultset
				int x=statement.executeUpdate(myquery);
				if(x>=0) {
					return true;
				}else {
					return false;
				}
				}catch(SQLException e) {
					e.printStackTrace();
					return false;
				}finally {
					if(resultset!=null) {
						resultset.close();	
					}
					if(statement!=null) {
						statement.close();	
					}
					if(dbConn!=null) {
						dbConn.close();	
					}
				}
			

	}

	public Student getStudent(String sid) throws Exception {
		// TODO Auto-generated method stub
		
		Connection dbConn= null;
		PreparedStatement statement= null;
		ResultSet resultset=null;
		Student student=null;
		long Studentid;
			try {
				//get database connection
				Studentid=Long.parseLong(sid);
				dbConn= dsource.getConnection();
				String myquery="select * from student where id=?";
				statement=dbConn.prepareStatement(myquery);
				statement.setLong(1, Studentid);
				resultset=statement.executeQuery();
				if(resultset.next()) {
					String Fname=resultset.getString(2);
					String Lname=resultset.getString(3);
					String Email=resultset.getString(4);
				student= new Student(Studentid,Fname,Lname,Email);
				}
				else {
					throw new Exception("couldn't find your student with student id = "+sid);
				}
				}catch(SQLException e) {
					e.printStackTrace();
					
				}finally {
					if(resultset!=null) {
						resultset.close();	
					}
					if(statement!=null) {
						statement.close();	
					}
					if(dbConn!=null) {
						dbConn.close();	
					}
				}
			
		return student;
	}

	public void updateStudentById(Student updatableStudent) throws Exception {
		// TODO Auto-generated method stub
		Connection dbConn= null;
		PreparedStatement statement= null;
		Long Studentid=updatableStudent.getId();
		String fname=updatableStudent.getFirstName();
		String lname=updatableStudent.getLastName();
		String em=updatableStudent.getEmail();
			try {
				//get database connection
				dbConn= dsource.getConnection();
				String myquery="update student set first_name=?, last_name=? , email=? where id=?";
				statement=dbConn.prepareStatement(myquery);
				statement.setString(1, fname);
				statement.setString(2, lname);
				statement.setString(3, em);
				statement.setLong(4, Studentid);
				statement.execute();
				
				}catch(SQLException e) {
					e.printStackTrace();
					
				}finally {
					if(statement!=null) {
						statement.close();	
					}	
					if(dbConn!=null) {
						dbConn.close();	
					}
				}
	}

	public void deleteStudent(String theStudentId) throws Exception {
		// TODO Auto-generated method stub
		Connection dbConn = null;
		PreparedStatement statement = null;
		
		try {
			// convert student id to int
			int studentId = Integer.parseInt(theStudentId);
			
			// get connection to database
			dbConn = dsource.getConnection();
			
			// create sql to delete student
			String sql = "delete from student where id=?";
			
			// prepare statement
			statement = dbConn.prepareStatement(sql);
			
			// set params
			statement.setInt(1, studentId);
			
			// execute sql statement
			statement.execute();
		}
		finally {
			// clean up JDBC code
			
				if(statement!=null) {
					statement.close();	
				}	
				if(dbConn!=null) {
					dbConn.close();	
				}
		}	
	}
	
}
	
	

