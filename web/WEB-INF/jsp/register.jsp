<%-- 
    Document   : register
    Created on : Mar 8, 2017, 1:45:19 PM
    Author     : Carly Francescut 000710713
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
        <title>Twits Central: Register</title>
    </head>
    <body>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3"></div>
                <div class="col-sm-6 img-rounded">
                    <h3>Register Now!</h3>
                    <form class="form-horizontal" action="${pageContext.request.contextPath}/registerUser" method="POST">
                        <div class="form-group form-group-sm">
                            <label for="username" class="control-label">Username:</label><input type="text" name="username" class="form-control">
                            <label for="password" class="control-label">Password:</label><input type="password" name="password" class="form-control">
                            <label for="passconfirm" class="control-label">Confirm:</label><input type="password" name="passConfirm" class="form-control">
                            <br/>
                            <input type="submit" name="submit" class="btn btn-default" value="Register">                            
                        </div>
                    </form>
                        <br/><p><a href="index"><span class="glyphicon glyphicon-arrow-left"></span> Go home</a></p>
                    <br/>
                    ${error}
                </div>
                <div class="col-sm-3"></div>
                </div>
            </div>
        </body>
    </html>