<%--
  Created by IntelliJ IDEA.
  User: danis_zam
  Date: 12/10/2018
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>


    <link href="../../CSS/home_page.css" rel="stylesheet">
    <link href="../../CSS/background.css" rel="stylesheet">
    <script src="../../JavaScript/background.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>

    <link href="../../CSS/Timeline.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Racing Sans One|Poiret One" rel="stylesheet">
    <link href="../../CSS/creativeMenu.css" rel="stylesheet">
    <script src="../../JavaScript/creativeMenu.js"></script>

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">

    <link type="text/css" href="${pageContext.request.contextPath}../../CSS/cube.css" rel="stylesheet">
    <style>
        .xbet{
            color: #0066FF;
        }
    </style>
</head>
<body>
<div id="gradient"/>

<div class="title" style="margin-left: 1%; padding-top: 1%">
    <h1 style="display: inline;">ZDstyle</h1>
    <p>Here you can create your own look</p>
</div>

<div class="wrap">
    <nav>
        <ul class="primary">
            <li>
                <a href="">Shoes</a>
                <ul class="sub">
                    <li><a href="">Boots</a></li>
                    <li><a href="">Sneakers</a></li>
                    <li><a href="">Slippers</a></li>
                    <li><a href="">Socks</a></li>
                </ul>
            </li>
            <li>
                <a href="">Clothing</a>
                <ul class="sub">
                    <li><a href="">Jeans</a></li>
                    <li><a href="">Jacket</a></li>
                    <li><a href="">Shirts Cat</a></li>
                </ul>
            </li>
            <li>
                <a href="">Accessories</a>
                <ul class="sub">
                    <li><a href="">Bags</a></li>
                    <li><a href="">Scarves</a></li>
                    <li><a href="">Ties</a></li>
                </ul>
            </li>
            <li>
                <a href="">ZDcreator</a>
                <ul class="sub" id="last" >
                    <li><a href="">Create you look</a></li>
                    <li><a href="">Color searcher</a></li>
                </ul>
            </li>
        </ul>
    </nav>
</div>

<div class="container-button" style="right: 2%; top: 1%">
    <a href="/signUp" class="btn btn-4">Join</a>
    <a href="/signIn" class="btn btn-3">Sign in</a>
</div>

<div class="wrapper">
    <div id="experiment">
        <div class="cube">
            <div class="topFace"><div>
                <h2>Adidas
                    <img src="http://obzorski.ru/wp-content/uploads/2016/07/Adidas_logo.png" style="width: 80%;margin-top: 20px">
                </h2>
                <p></p>
            </div></div>
            <div class="leftFace">
                <h2>Fila</h2>
                <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/Fila_logo.svg/1280px-Fila_logo.svg.png"
                     style="width: 80%;margin-top: 20px">
            </div>
            <div class="rightFace">
                <h2>Nike</h2>
                <img src="https://storage.googleapis.com/multi-static-content/previews/artage-io-thumb-f13e6a4c43b8974f106bc5bbee066aca.png"
                     style="width: 80%;margin-top: 20px">
            </div>
        </div>
        <div class="cube two">
            <div class="topFace"><div>
                <h2>Reebok
                    <img src="https://storage.googleapis.com/multi-static-content/previews/artage-io-thumb-fda8f6396ce402e2af881f9097d118ff.png"
                         style="width: 80%; margin-top: 10px"></h2>

            </div></div>
            <div class="leftFace">
                <img src="https://pp.userapi.com/c849428/v849428297/73f67/81xKB_NrA_E.jpg" style="width: 200px; height: 200px; margin-left: -10px; margin-top: -10px">
            </div>
            <a href="buckviza.html">
                <div class="rightFace">
                    <h2>Tommy</h2>
                    <img src="https://i.ebayimg.com/images/g/OJkAAOSw3xJVdiA-/s-l300.jpg"
                         style="width: 80%; margin-top: 10px">
                </div>
            </a>
        </div>
        <div class="cube three">
            <div class="topFace"><div>
                <h2>Asics</h2>
                <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Asics_Logo.svg/1280px-Asics_Logo.svg.png" style="width: 80%; margin-top: 10px">
            </div></div>
            <div class="leftFace">
                <h2>1<span class="xbet">X</span>BET</h2>
                <p>ставь и побеждай</p>
            </div>
            <div class="rightFace">
                <h2>надо текст придумать</h2>

            </div>
        </div>
    </div>
</div>


<a href ="https://github.com/daniszam">
    <div id="footer" class="contact">
        <img src="https://pp.userapi.com/c840627/v840627932/8162e/Tq2NUGdqHog.jpg" alt="Danis Zamaliev" width="25" height="25">
        &copy; danis
    </div>
</a>

</body>
</html>
