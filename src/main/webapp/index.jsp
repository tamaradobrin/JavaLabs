<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <title>Store Country</title>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1 id="pageHeader">Store Country Details</h1>
    </div>
    <form action="storeInfo" method="POST">
        <div class="row">
            <div class="col-lg-4">
                <div class="form-group">
                    <label for="continent" class="control-label">Continent:</label>
                    <select class="form-control" id="continent" name="continent">
                        <%
                            List<String> continents = (ArrayList<String>) request.getAttribute("continents");
                            for (String continent : continents) {
                        %>
                        <option><% out.print(continent);%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4">
                <div class="form-group">
                    <label class="control-label" for="country">Country:</label>
                    <input type="text" class="form-control" name="country" id="country">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4">
                <div class="form-group">
                    <label class="control-label" for="capital">Capital:</label>
                    <input type="text" class="form-control" name="capital" id="capital">
                </div>
            </div>
        </div>
        <br>
        <div class="form-group">
            <button type="submit" name="submit" class="btn btn-primary">Save</button>
        </div>
    </form>
</div>
</body>
</html>
