<!DOCTYPE html>
<html>

<head>
      <title>Update Student</title>
      <link type="text/css" rel="stylesheet" href="css/style.css">
      <link type="type/css" rel="stylesheet" href="css/add-student-style.css">
      </head>
      
      <body>
         <div id="wrapper">
             <div id="header">
                <h2>MY UNIVERSITY</h2>
             </div>
         </div>
         <div id="container">;
            <h3>Update  Student</h3>
            
            <form action="StudentControllerServlet" method="GET">
                <input type="hidden"  name="command"  value="UPDATE"/>
                <input type="hidden"  name="studentID" value="${THE_STUDENT.id}"/>
                <table>
                   <tbody>
                      <tr>
                          <td><label>First name:</label></td>
                          <td><input type="text" name="firstname"
                                          value="${THE_STUDENT.firstname}" /></td>
                                          
                        </tr>
                        <tr>
                         <td><label>Last name:</label></td>
                         <td><input type="text" name="lastname"
                                           value="${THE_STUDENT.lastname}" /></td>
                        </tr>
                        <tr>
                         <td><label>Email:</label></td>
                         <td><input type="text" name="email"
                                          value="${THE_STUDENT.email}" /></td>
                         </tr>
                         <tr>
                 
                           <td><input type="submit" name="save" class="save" /></td>
                         </tr>
                   </tbody>
                </table>
            </form>
            <div style="clear: both;"?></div>
            <p>
                <a href="StudentControllerServlet">Back to List</a>
            </p>
         </div>
      </body>
 </html>