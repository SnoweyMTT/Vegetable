<%-- 
    Document   : viewCartPage
    Created on : Oct 31, 2021, 7:17:30 AM
    Author     : SnoweyMTT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
        <style type="text/css">
            body {
                padding:0px;
                margin:0px;
            }

            #menu {
                width: 100%;
                height: 100px;
                overflow: auto;
                text-align: center;
                background-color: rgb(200, 255, 255);
            }

            #menu ul {
                list-style-type: none;
            }

            #menu ul li{
                display:inline;
                overflow: auto;
            }

            #menu li a {
                display: inline-block;
                color: #6e7085;
                text-align: center;
                padding: 0.8em;
                font-size: 1.5em;
                text-decoration: none;
            }

            #menu li a:hover {
                background-color: rgb(230, 255, 255);
            }

            #title {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                color: white;
                text-align: center;
            }

            h1 {
                text-transform: uppercase;
                margin: 0;
                font-size: 4rem;
                white-space: nowrap;
            }

            h5 {
                color: rgb(230, 255, 255);
                font-size: 2rem;
            }
            table {
                width: 100%;
                border: 1px solid black;
            }
            td {
                text-align: center;
                padding: 15px;
                text-align: left;
            }
        </style>
    </head>
    <header>
        <div id="menu">
            <ul>
                <li>
                    <a href="home.jsp" target="_bank"> Trang chủ </a>
                </li>
                <li>
                    <c:url var="searchLink" value="MainController">
                        <c:param name="action" value="Search"></c:param>
                    </c:url>
                    <a href="${searchLink}" target="_bank"> Rau củ quả </a>
                </li>
                <li>
                    <a href="flowers.jsp" target="_bank"> Hoa tươi </a>
                </li>
                <li>
                    <a href="recipes.jsp" target="_bank"> Công thức nấu ăn </a>
                </li>
                <li>
                    <a href="aboutUs.jsp" target="_bank"> Về chúng tôi </a>
                </li>
                <li>
                    <a href="contactUs.jsp" target="_bank"> Liên hệ </a>
                </li>
                <c:if test="${sessionScope.LOGIN_USER == null}">
                    <li>
                        <a href="loginPage.jsp" target="_bank"> Đăng nhập </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.LOGIN_USER != null}">
                    <li>
                        <c:url var="roleLink" value="MainController">
                            <c:param name="action" value="Role"></c:param>
                        </c:url>
                        <a href="${roleLink}" target="_bank"> Trang cá nhân </a>
                    </li>
                    <li>
                        <c:if test="${sessionScope.LOGIN_USER.roleID eq 'US'}">
                            <c:url var="cartLink" value="MainController">
                                <c:param name="action" value="View"></c:param>
                                <c:param name="userID" value="${sessionScope.LOGIN_USER.userID}"></c:param>
                            </c:url>
                            <a href="${cartLink}" target="_bank"> Giỏ hàng </a>
                        </c:if>
                    </li>
                    <li>
                        <c:url var="logoutLink" value="MainController">
                            <c:param name="action" value="Logout"></c:param>
                            <c:param name="userID" value="${sessionScope.LOGIN_USER.userID}"></c:param>
                        </c:url>
                        <a href="${logoutLink}" target="_bank"> Đăng xuất </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </header>
    <body>
        <c:if test="${sessionScope.LOGIN_USER.roleID eq 'US'}">
            <a href="loginPage.jsp">Go to Login Page</a>
            <c:if test="${empty sessionScope.plistInCart}">
                <p>Your Cart is empty.</p>
            </c:if>
            <c:if test="${not empty sessionScope.plistInCart}">
                <h1>Items of Your Cart</h1>
                <table border="1">        
                    <tr> 
                        <td>No</td>
                        <td>Product</td> 
                        <td>Quantity</td> 
                        <td>Price</td> 
                        <td>Edit</td>
                    </tr>
                    <c:set var="total" value="${0}"/>
                    <c:forEach var="cart" varStatus="counter" items="${sessionScope.plistInCart}">
                        <form action="MainController" method="POST">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    <ul>
                                        <li>Product Name : ${cart.product.productName}</li>
                                        <li><img src="${cart.product.productImage}" style="width:200px;height:200px;"/></li>
                                        <li>Category: ${cart.product.category.categoryName}</li>
                                        <li>Price: ${cart.product.price}</li>
                                        <li>Quantity: ${cart.product.quantity}</li>
                                        <li>Quantity Cart: ${cart.quantity}</li>
                                        <li>Total: ${cart.product.price * cart.quantity}</li>
                                    </ul>
                                </td>
                                <td><input type="number" name="quantity" value="${cart.quantity}" min="1" max="${cart.quantity + cart.product.quantity}"/></td>
                                <td><input type="number" name="price" value="${cart.product.price}" readonly=""/></td>
                                    <c:set var="total" value="${total + cart.product.price * cart.quantity}"/>
                                <td>
                                    <input type="submit" name="action" onclick="return confirm('Remove this item out of your cart?')" value="Remove"/>
                                </td>
                                <td>
                                    <input type="submit" name="action" value="Edit"/>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                    <tr>Total : ${total}</tr>
                </table>


                <br>
                <form action="MainController">
                    <input type="submit" name="action" value="Search">
                </form>
                <br>
                <a href="home.jsp">Home Page</a>
            </c:if>
        </c:if>
    </body>
</html>
