<%-- 
    Document   : dashboard
    Created on : Nov 28, 2016; March 8, 2017
    Author     : Carly Francescut
--%>

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
        
        <title>Twits Central: Dashboard</title>
    </head>
    <body>

        ${navbar}
        
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3"></div>
            <div class="col-sm-6 img-rounded">
                <h3>Welcome ${user}!</h3>
                <div align="right">${adminPanel} <a href="${pageContext.request.contextPath}/logout">Logout</a></div>
                <h3>Channels I'm Following</h3>

                <table class="table">
                    <tr><th>Channel Name</th><th>Read Twits</th><th>Stop Following</th></tr>
                <c:forEach var="channel" items="${following}">
                    <tr>
                        <td>${channel.channelName}</td>
                        <td><a href="${pageContext.request.contextPath}/channel?id=${channel.channelId}">Read channel</a></td>
                        <td><a href="${pageContext.request.contextPath}/Unfollow?id=${channel.channelId}">Unfollow</a></td>
                    </tr>
                </c:forEach>
                </table>
                <h3>Find Channels</h3>
                <form class="form-horizontal" action="${pageContext.request.contextPath}/search" method="POST">
                    <div class="form-group form-group-sm">
                        <label for="channelOwner" class="control-label">Username:</label> <input type="text" class="form-control" name="channelOwner" value="">
                        <label for="channelName" class="control-label">Channel Name:</label> <input type="text" class="form-control" name="channelName" value="">
                        <input type="hidden" name="action" value="searchChannels">
                        <br/>
                        <input type="submit" class="btn btn-default" value="Search">                            
                    </div>
                </form>

                <table class="table">
                    <tr><th>Channel Name</th><th>Owner</th><th>Follow</th></tr>
                    <c:forEach var="searchResults" items="${searchResults}">
                        <tr>
                            <td>${searchResults.channelName}</td> 
                            <td>${searchResults.username}</td>
                            <td><a href="${pageContext.request.contextPath}/Follow?id=${searchResults.channelId}">Follow</a></td>
                        </tr>
                    </c:forEach>
                </table>
                    
                <h3>My Channels</h3>
                <form class="form-horizontal" action="${pageContext.request.contextPath}/addChannel" method="POST">
                    <div class="form-group form-group-sm">
                        <label for="newChannel" class="control-label">Add Channel:</label> <input type="text" class="form-control" name="newChannel">
                        <input type="hidden" name="action" value="newChannel">
                        <br/>
                        <input type="submit" class="btn btn-default" value="Add Channel">
                    </div>
                </form>
                    ${creationMessage}
                
                    <table class="table">
                        <tr><th>Channel Name</th><th>View Twits</th><th>Delete Channel</th></tr>
                        <c:forEach var="ownedChannels" items="${ownedChannels}">
                            <tr>
                                <td>${ownedChannels.channelName}</td>
                                <td><a href="${pageContext.request.contextPath}/channel?id=${ownedChannels.channelId}">View</a></td>
                                <td><a href="${pageContext.request.contextPath}/Delete?id=${ownedChannels.channelId}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>

            </div>
            <div class="col-sm-3"></div>
            </div>
        </div>
    </body>
</html>