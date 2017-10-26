<%@ page import="java.util.Map" %>
<%@ page import="java.util.TreeMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Country" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <title>Show Countries</title>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1 id="pageHeader">List of Stored Countries</h1>
    </div>
    <table>
        <thead>
        <th>Country</th>
        <th>Capital</th>
        </thead>
        <tbody>
        <% List<Country> countries = (ArrayList<Country>) request.getAttribute("countries");
            for (Country country : countries) {
        %>
        <tr>
            <td><% out.print(country.getCountryName());%></td>
            <td><% out.print(country.getCapital());%></td>
            <td><% out.print(country.getContinent());%></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
