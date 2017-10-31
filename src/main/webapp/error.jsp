<%--
  Created by IntelliJ IDEA.
  User: tamid_000
  Date: 10/31/2017
  Time: 11:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <title>Error</title>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1 id="pageHeader">Error</h1>
    </div>
    <div class="col-lg-12">
        <% String error = (String) request.getAttribute("error");%>
        <% out.print(error); %>
    </div>
</div>
</body>
</html>
