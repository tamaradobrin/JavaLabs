<%@ page import="java.util.Map" %>
<%@ page import="java.util.TreeMap" %><%--
  Created by IntelliJ IDEA.
  User: tamid_000
  Date: 10/16/2017
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Info</title>
</head>
<body>
<table>
    <thead>
    <th>Country</th>
    <th>Capital</th>
    </thead>
    <tbody>
    <% Map<String, String> infoMap = (TreeMap<String, String>) request.getAttribute("infoMap");
        for (String country : infoMap.keySet()) {
    %>
    <tr>
        <td><% out.print(country);%></td>
        <td><% out.print(infoMap.get(country));%></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
