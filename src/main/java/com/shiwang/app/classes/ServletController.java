package com.shiwang.app.classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ServletController
 */
@WebServlet("/ServletController")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private StudentDbUtil studentdb;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource datasource;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletController() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	@Override
	public void init() throws ServletException {
		super.init();
		// TODO Auto-generated method stub
		try {
			studentdb= new StudentDbUtil(datasource);
		}catch(Exception e) {
			throw new ServletException(e);
			}
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		try{
			
			String thecommand=request.getParameter("command");
			
			if(thecommand==null) {
				thecommand="LIST";
			}
			
				switch(thecommand) {
				
				case "LIST":
					listStudents(request,response);
					break;
				
				case "ADD":
					add(request,response);
					break;
					
				case "LOAD":
					loadStudent(request,response);
					break;
				
				case "UPDATE":
					updateStudent(request,response);
					break;
					
				case "DELETE":
					deleteStudent(request, response);
					break;	
					
				default:
					listStudents(request,response);
				}
				
				listStudents(request,response);
			
			}catch(Exception e){
				e.printStackTrace();
					}
			
	}
	
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student id from form data
			String theStudentId = request.getParameter("studentId");
			
			// delete student from database
			studentdb.deleteStudent(theStudentId);
			
			// send them back to "list students" page
			listStudents(request, response);
		}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int sid=Integer.parseInt(request.getParameter("studentId"));
		String studentFirstname=request.getParameter("fname");
		String studentlastname=request.getParameter("lname");
		String studentemail=request.getParameter("email");
		
		Student updatableStudent= new Student(sid,studentFirstname , studentlastname, studentemail);
		
		studentdb.updateStudentById(updatableStudent);
		
		listStudents(request, response);
	}


	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String sid=request.getParameter("studentId");
		Student st= studentdb.getStudent(sid);
		request.setAttribute("UpdateStudent", st);
		RequestDispatcher dispatcher= request.getRequestDispatcher("/updateStudent.jsp");
		dispatcher.forward(request, response);
	}


	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<Student> sList= studentdb.getStudents();
		
		//i used this just to check if i am getting student info 
//		for(Student s: sList) {
//			PrintWriter pw= response.getWriter();
//			pw.print(s.getId()+" "+s.getFirstName()+" "+s.getLastName()+" "+s.getEmail());
//			pw.println();
//	}
		request.setAttribute("returnedList", sList);

		RequestDispatcher dispatch= request.getRequestDispatcher("/student-List.jsp");
		dispatch.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(request.getParameter("fname")!=null && request.getParameter("lname")!=null  && request.getParameter("email")!=null) {
		String studentFirstname=request.getParameter("fname");
		String studentlastname=request.getParameter("lname");
		String studentemail=request.getParameter("email");
		
		Student student= new Student(studentFirstname, studentlastname,studentemail );
		
		boolean isAdded=studentdb.addStudent(student);
		
		if(isAdded) {
			listStudents(request, response);
				
			}
		}
	}	
	
	
}