package com.luv2code.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      @Resource(name="jdbc/web_student_tracker")
	  private DataSource datasource;
   
      /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
       PrintWriter out= response.getWriter();
       response.setContentType("text/plain");
       Connection myconn=null;
       Statement  myStmt=null;
       ResultSet myRs=null;
       try {
    	   // get connection
    	  myconn=datasource.getConnection();
    	  // Create sql statements
    	   String sql="select * from student";
    	  myStmt=myconn.createStatement();
    	  // execute sql query
    	  myRs=myStmt.executeQuery(sql);
    	   // process the result set
    	  while(myRs.next()) {
    		  int id=myRs.getInt("id");
    		  out.println(id);
    	  }
    		  
       }
       catch(Exception e) {
    	  e.getStackTrace();
       }
	}

}
