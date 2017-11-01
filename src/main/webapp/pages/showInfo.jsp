<%@ page import="model.Country" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="p" uri="../WEB-INF/tlds/record.tld" %>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/css/custom.css">
    <title>Show Countries</title>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1 id="pageHeader">List of Stored Countries</h1>
    </div>
    <table class="table table-striped">
        <thead>
        <th>Country</th>
        <th>Capital</th>
        <th>Continent</th>
        </thead>
        <tbody>
        <% List<Country> countries = (ArrayList<Country>) request.getAttribute("countries");
            for (Country country : countries) {
        %>
        <tr>
            <p:record key="<%=country.getCountryName()%>"/>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
