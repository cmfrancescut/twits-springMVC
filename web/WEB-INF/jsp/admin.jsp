<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <!-- Bootstrap for Presentation -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Custom CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/twits.css" />
        <title>Twit Central: Administration</title>
    </head>
<body>

    @{navbar}
    
      <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3"></div>
                <div class="col-sm-6 img-rounded">
                    <h1>Administration</h1>
                    <h3>Add New User</h3>
                    <form class="form-horizontal" action="${pageContext.request.contextPath}/RegisterUser" method="POST">
                        <div class="form form-group-sm">
                            <label for="username" class="control-label">Username: </label><input type="text" class="form-control" name="newUser">
                            <label for="password" class="control-label">Password: </label><input type="password" class="form-control" name="password">
                            <label for="confirm" class="control-label">Confirm: </label><input type="password" class="form-control" name="passConfirm">
                            <br/>
                            <input type="submit" class="btn btn-default" name="submit" value="Register User">
                        </div>
                    </form>
                    <br/>
                    ${error}
                    <br/>
                    <h3>Manage Users</h3>
                    <table class="table">
                        <tr>
                            <th>Username</th>
                            <th>Reset Password</th>
                            <th>Toggle Locked</th>
                            <th>Toggle Admin</th>
                            <th>Delete User</th>
                        </tr>
                        <c:forEach var="userList" items="${userList}">
                            <tr>
                                <td>${userList.username}</td>
                                <td><a href="${pageContext.request.contextPath}/ResetPassword?user=${userList.username}">Reset Password</a></td>
                                <td><a href="${pageContext.request.contextPath}/ToggleLock?user=${userList.username}">Toggle Locked</a></td>
                                <td><a href="${pageContext.request.contextPath}/ToggleAdmin?user=${userList.username}">Toggle Admin</a></td>
                                <td><a href="${pageContext.request.contextPath}/DeleteUser?user=${userList.username}">Delete User</a></td>
                            </tr>
                        </c:forEach>
                        
                    </table>
                    
                </div>
                <div class="col-sm-3"></div>
                </div>
            </div>          
    </body>
</html>