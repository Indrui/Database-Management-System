package com.luv2code.web.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.sql.DataSource;

public class StudentDbUtil {
    private DataSource datasource;
    
	public StudentDbUtil(DataSource thedatasource) {
		datasource=thedatasource;
	}
	
	public List<Student> getstudents() throws Exception{
		List <Student> students=new ArrayList<>();
		// create jdbc objects
		Connection myconn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		try {
			// get a connection
			myconn=datasource.getConnection();
			//create sql statement
			String sql="select * from student order by last_name";
			myStmt=myconn.createStatement();
			// execute query
			myRs =myStmt.executeQuery(sql);
			//process resultset
			while(myRs.next()) {
				// create jdbc objects
			   int id=myRs.getInt("id");
			   String firstname=myRs.getString("first_name");
			   String lastname=myRs.getString("last_name");
			   String email=myRs.getString("email");
			   Student tempStudent=new Student(id,firstname,lastname,email);
			   // add it to the list of students
			   students.add(tempStudent);
			}
			return students;
		}
		finally {
			close(myconn,myStmt,myRs);
		}
	}
    public void addStudents(Student thestudent)throws Exception {
    	// create jdbc objects
    	Connection myconn=null;
    	PreparedStatement mystmt=null;
    	try {
    	// get db connection
    	myconn=datasource.getConnection();
    	// create sql for insert
    	String sql="insert into student"
    			    + "(first_name,last_name,email) "
    			    + "values (?,?,?)";
    	mystmt=myconn.prepareStatement(sql);
    	// set param values for the student
    	mystmt.setString(1, thestudent.getFirstname());
    	mystmt.setString(2, thestudent.getLastname());
    	mystmt.setString(3, thestudent.getEmail());
    	// execute sql query
    	mystmt.execute();
    	}
    	finally {
    		
    		// clean up jdbc objects
    		close(myconn,mystmt,null);
    	}
    	
    }
	private void close(Connection myconn, Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
		try {
			if(myRs!=null) {
				myRs.close();
			}
			if(myStmt!=null) {
				myStmt.close();
			}
			if(myconn!=null) {
				myconn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Student getStudent(String thestudentid) throws Exception{
			Student thestudent=null;
			Connection myconn=null;
			PreparedStatement mystmt=null;
			ResultSet myRs=null;
			int studentId;
			try {
			  // convert student id to int 
				studentId=Integer.parseInt(thestudentid);
				// get the connection to the database
				myconn=datasource.getConnection();
				// create sql to get the selected  student
				String sql="select * from student where id=?";
				// create prepared statement
				mystmt=myconn.prepareStatement(sql);
				// set params
				mystmt.setInt(1, studentId);
				// execute statement
				myRs=mystmt.executeQuery();
				// retreive data from the result set row
				if(myRs.next()) {
					String firstName=myRs.getString("first_name");
					String lastName=myRs.getString("last_name");
					String email=myRs.getString("email");
					// use the studentid during construction
					thestudent=new Student(studentId,firstName,lastName,email);
					return thestudent;
				}else {
					throw new Exception("could not find student id"+studentId);
				}
		   }
		finally {
			// clean up the jdbc objects
			close(myconn,mystmt,myRs);
		}
		
	}

	public void updatestudent(Student student) throws Exception{
		// create jdbc objects
		Connection myconn=null;
		PreparedStatement myStmt=null;
		try {
		// get db connection
		myconn=datasource.getConnection();
		
		// create sql update statement
		String sql="update student   "
				   + "set  first_name=?,  last_name=?,  email=?  "
		           + "where id=? ";
		// prepare statement
		myStmt=myconn.prepareStatement(sql);
		// set params
		myStmt.setString(1,student.getFirstname());
		myStmt.setString(2,student.getLastname());
		myStmt.setString(3,student.getEmail());
		myStmt.setInt(4,student.getId());
		
		// execute sql statement
		myStmt.execute();
		}finally {
			// close jdbc objects
			close(myconn,myStmt,null);
		}
		
	}

	public void deletestudent(String thestudentid)throws Exception {
		// create jdbc objects
		Connection myconn=null;
		PreparedStatement myStmt=null;
		try {
			// convert student to int
			int studentid=Integer.parseInt(thestudentid);
			// get db connection
			myconn=datasource.getConnection();
			// create sql statement for delete student
			String sql="delete from student where id=?";
			//prepare a student
			myStmt=myconn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,studentid);
			// execute sql query
			myStmt.execute();
		}finally {
			// close jdbc objects
			close(myconn,myStmt,null);
		}
	}
} 
