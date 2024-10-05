package com.luv2code.web.jdbc;

import java.io.IOException;
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
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource datasource;
	

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		// create our student db util .... pass in the connection pool /datasource
		try {
			studentDbUtil =new StudentDbUtil(datasource);
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		// read the "command" parameter
		String thecommand=request.getParameter("command");
		if(thecommand==null) {
			thecommand="LIST";
		}
		switch(thecommand) {
		case "LIST":
			listStudents(request,response);
			break;
		case "ADD":
			addStudent(request,response);
			break;
		case "DELETE":
			deletestudent(request,response);
			break;
		case "LOAD":
			loadstudents(request,response);
			break;
		case "UPDATE":
			updatestudents(request,response);
			break;
		default:
			listStudents(request,response);
			break;
		}}catch(Exception e) {
		   throw new ServletException(e);
		}
     
}
	private void deletestudent(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		   // read student id from form data
		String thestudentid=request.getParameter("studentID");
		
		// delete student from the database
		studentDbUtil.deletestudent(thestudentid);
		// go back to the list-students
		listStudents(request,response);
		
	}
	private void updatestudents(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
			// read student info from the data
		int id=Integer.parseInt(request.getParameter("studentID"));     
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		
		// create a new student object
		 Student student=new Student(id,firstname,lastname,email);
		 // perform update to the database
		 
		studentDbUtil.updatestudent(student);
		
		// send them back to the "list-students"
		 listStudents(request,response);
		
		}
		
	
	private void loadstudents(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		// read id from form data
		  String id=request.getParameter("studentID");
		  // get student from database(db util)
		  Student thestudent=studentDbUtil.getStudent(id);
		  
		  // place student in request attribute
		  request.setAttribute("THE_STUDENT",thestudent);
		  
		  // send data to the student-form-jsp page
		  RequestDispatcher dispatcher=request.getRequestDispatcher("/update-student-form.jsp");
		  
		  dispatcher.forward(request, response);
	}
	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// get students from db util
		List<Student> students =studentDbUtil.getstudents();
		// add students to the get request
		
		request.setAttribute("STUDENT_LIST", students);
		
		// send to jsp page(view)
		RequestDispatcher dispatcher =request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
	}
	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		
		// create a new student object
		Student s=new Student(firstname,lastname,email);
		// add this student to the database 
		studentDbUtil.addStudents(s);
		// go back to the main page
		listStudents(request,response);
	}
	}
