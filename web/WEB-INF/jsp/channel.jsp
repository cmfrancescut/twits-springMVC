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
        <title>Twits Central: Twits</title>
    </head>
    <body>
    
        ${navbar}
        
      <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3"></div>
                <div class="col-sm-6 img-rounded">
                    <h1><small>Channel: ${channelInfo}</small></h1>
                    
                        ${postTwit}
                        
                    <h3>Twits</h3>
                    <table class="table">
                        <tr><th>Date Posted</th>
                            <th>Twit</th>
                        </tr>
                        
                        <c:forEach var="twits" items="${twits}">
                            <tr>
                                <td>${twits.date}</td>
                                <td>${twits.twitText}</td>
                            </tr>
                        </c:forEach>
                    </table>
                    
                </div>
                <div class="col-sm-3"></div>
                </div>
            </div>          
    </body>
</html>