
<%-- 
    Document   : index
    Created on : Nov 28, 2016; March 6, 2017
    Author     : Carly Francescut
--%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap for Presentation -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Custom CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/twits.css" />
        
        <title>Twits Central</title>
    </head>
    <body>
        ${navbar}
    <div class="container-fluid main-background">
        <div class="row">
            <div class="col-sm-3"></div>
            <div class="col-sm-6 img-rounded">
            <h1>Welcome to Twits!</h1>
            <h3>Current User? Log in!</h3>
            <form class="form form-horizontal" action="${pageContext.request.contextPath}/login" method="POST" commandName="user">
            <div class="form-group form-group-sm">
                <label for="username" class="control-label">Username:</label><input type="text" name="username" id="username" class="form-control">
                <label for="password" class="control-label">Password:</label><input type="password" name="password" id="username" class="form-control">
                <input type="hidden" name="action" value="login">
                <br/>
                <input type="submit" name="submit" class="btn btn-default" value="Log in">    
            </div>
            </form>
            ${error}
            <h3>Don't have an account?</h3>
            <p>New user? <a href="${pageContext.request.contextPath}/register">Register now!</a></p>
            </div>
            
            <div class="col-sm-3"></div>
            </div>
        </div>
    </body>
</html>
