<%--
  Created by IntelliJ IDEA.
  User: danis_zam
  Date: 27/10/2018
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>


    <link href="../../CSS/background.css" rel="stylesheet">
    <script src="../../JavaScript/background.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>


    <link href="https://fonts.googleapis.com/css?family=Racing Sans One|Poiret One" rel="stylesheet">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link href="../../CSS/creativeMenu.css" rel="stylesheet">

    <link href="../../CSS/cards.css" rel="stylesheet">
    <script src="../../JavaScript/cart.js"></script>
    <link href="../../CSS/cart.css" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="../../JavaScript/cartUpdate.js"></script>


    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <style>
        body{
            background-color: #8B8B8B;
        }
    </style>

</head>
<body>


<div id="gradient" style="width: auto; height: auto; min-height: 300%"/>

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


<a class="main-item" href="javascript:void(0);" tabindex="1" style="margin-top: 20px" >Cart</a>
<div class="shopping-cart" id="shopping" style="position: relative">

    <div class="title">
        Shopping Bag
    </div>


    <c:forEach var="cart" items="${cart}">
    <div class="item">
        <div style="width: 100px; height: 55px; margin-top: 20px">
            <button class="buy btn-4" value="buy" style="width: 80px" >
                <span>drop</span>
            </button>
        </div>

        <div class="image">
            <img src="${cart.img}" alt="${cart.name}" height="50px" width="50px"/>
        </div>

        <div class="description">
            <span>${cart.name}</span>
        </div>

        <div class="total-price">$549</div>
    </div>
    </c:forEach>

</div>

<div style="padding-left: 100px; padding-top: 100px; padding-right: 100px" >
    <c:forEach var="snacks" items="${products}">
    <div>
        <div class="w3-card-4" style="float: left; margin-left: 20px">
            <img src="${snacks.img}" alt="${snacks.name}">
            <div class="w3-container w3-center" style="float: left">
                <p>${snacks.name}</p>
            </div>
            <form id="form${snacks.id}">
                <input type="hidden" name="snack_name${snacks.id}" id="snack_name${snacks.id}" value="${snacks.name}" />
                <input type="hidden" name="snack_id${snacks.id}" id="snack_id${snacks.id}" value="${snacks.id}" />
                <input type="hidden" name="snack_img${snacks.id}" id="snack_img${snacks.id}" value="${snacks.img}" />
                <button class="buy btn-4" style="margin-left: 70%;" value="buy" type="submit">
                    <span>buy</span>
                </button>
            </form>
        </div>
    </div>
    </c:forEach>

</div>

</body>
</html>
