<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/css/custom.css">
    <title>Login Page</title>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1 id="pageHeader">Login</h1>
    </div>
    <form action="login" method="POST">
        <div class="row">
            <div class="col-lg-4">
                <div class="form-group">
                    <label class="control-label" for="username">Username:</label>
                    <input type="text" class="form-control" name="username" id="username">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4">
                <div class="form-group">
                    <label class="control-label" for="pass">Password:</label>
                    <input type="password" class="form-control" name="pass" id="pass">
                </div>
            </div>
        </div>
        <br>
        <div class="form-group">
            <button type="submit" name="submit" class="btn btn-primary">Login</button>
        </div>
        <div class="row">
            <div class="col-lg-4">
                <p class="text-danger">
                    <% String error = (String) request.getAttribute("error");
                        if (error != null) {
                            out.print(error);
                        }
                    %>
                </p>
            </div>
        </div>
    </form>
</div>
</body>
</html>
