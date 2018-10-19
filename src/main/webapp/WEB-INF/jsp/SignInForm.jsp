<%--
  Created by IntelliJ IDEA.
  User: danis_zam
  Date: 11/10/2018
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        body {
            font-family: 'Poiret One', cursive; !important;
            background: #e3f2fd !important;
        }


    </style>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://fonts.googleapis.com/css?family=Racing Sans One|Poiret One" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/CSS/signUp.css" rel="stylesheet">

</head>
<body>

<form id="singUp" method="post">
    <label>Sign in</label>
    <div id="wrapper">
        <input id="email" name="email" placeholder="Email" type="text">
        <input id="password" name="password" placeholder="Password" type="password">
    </div>

    <button id="submit" type="submit" value="Sign in">
    <span>
      Sign in
    </span>
    </button>
</form>
<div id="hint"><b>Click on the tabs</b></div>

<div>
    <h1 id="checkBox" style="margin-left: 2%"></h1>
</div>


<nav class="navbar navbar-expand-lg navbar-default fixed-bottom" style="background-color: #e3f2fd; position: fixed;bottom: 2%; left: 2%;" >
    <div class="container-fluid">
        <p><b id="zd" class="text-left" style="color: black;">&copy; 2018 Zamaliev Danis</b></p>
        <a href="/home" class="btn-right" role="button" aria-disabled="true" style="color: black;"><b>To Site</b></a>
    </div>
</nav>

</body>
</html>

