<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<title>Student Tracker App</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

       <div id="wrapper">
          <div id="header">
                   <h2>MY UNIVERSITY</h2>
     </div>
</div>

     <div id="container">
          <! --put a new button: Add Student -->
          <input type="button" value="Add Student"
                 onclick="window.location.href='add-student-form.jsp'; return false;"
                 class="add-student-button"
                 />
       <div id="content">
          <table>
            <tr>
               <th>First Name</th>
               <th>Last Name</th>
               <th>Email</th>
               <th>Action</th>
            </tr>
            <c:forEach var="tempStudent" items="${STUDENT_LIST}">
            <tr>
            <!-- set up a link for each student -->
            <c:url var="tempLink"  value="StudentControllerServlet">
                      <c:param name="command"   value="LOAD"/>
                      <c:param name="studentID" value="${tempStudent.id}"/>
             </c:url>
             <!-- set up a link for each student -->
            <c:url var="deletelink"  value="StudentControllerServlet">
                      <c:param name="command"   value="DELETE"/>
                      <c:param name="studentID" value="${tempStudent.id}"/>
             </c:url>
              <td>${tempStudent.firstname} </td>
              <td>${tempStudent.lastname } </td>
              <td>${tempStudent.email} </td>
              <td>
              <a href="${tempLink}">UPDATE</a>
              |
              <a href="${deletelink}"
              onclick="if (!(confirm('Are You Sure You Want To Delete This Student ?'))) return false">
              DELETE</a>
              </td>
            </tr>
            	
            </c:forEach>
           
          </table>
         
         
       </div>
   </div>


</body>
</html>