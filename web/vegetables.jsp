<%-- 
    Document   : vegetables
    Created on : Oct 22, 2021, 8:35:12 PM
    Author     : SnoweyMTT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rau Củ Quả của SnoweyMTT's Store</title>
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
        <form action="MainController" method="POST">
            Search <input type="text" name="search" value="${param.search}"/>
            <select name="category">
                <option value="all">All</option>
                <c:forEach var="cat" items="${requestScope.LIST_CATEGORY}">
                    <option value="${cat.categoryName}">${cat.categoryName}</option>
                </c:forEach>
            </select>
            <input type="submit" name="action" value="Search"/>
        </form>
        <c:if test="${sessionScope.LOGIN_USER.roleID eq null || sessionScope.LOGIN_USER.roleID ne 'AD'}">
            <c:if test="${requestScope.LIST_PRODUCTS == null || empty requestScope.LIST_PRODUCTS}">
                <p>There is no product available</p>
            </c:if>
            <c:if test="${requestScope.LIST_PRODUCTS != null}">
                <c:if test="${not empty requestScope.LIST_PRODUCTS}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Product Name</th>
                                <th>Product Image</th>
                                <th>Category Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Buy</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="product" varStatus="counter" items="${requestScope.LIST_PRODUCTS}">
                            <form action="MainController" method="POST">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${product.productName}</td>
                                    <td>
                                        <img src="${product.productImage}" style="width:200px;height:200px;"/>
                                    </td>
                                    <td>${product.category.categoryName}</td>
                                    <td>${product.quantity}</td>
                                    <td>${product.price.stripTrailingZeros().toPlainString()}</td>
                                    <td>
                                        <input type="number" name="quantity" min="1" max="${product.quantity}"/>
                                        <input type="submit" name="action" value="Buy"/>
                                        <input type="hidden" name="productID" value="${product.productID}"/>
                                        <input type="hidden" name="search" value="${param.search}"/>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </c:if>
    </c:if>
    <c:if test="${sessionScope.LOGIN_USER.roleID eq 'AD'}">
        <form action="MainController" method="POST">
            Create a new product <input type="submit" name="action" value="Create"/>
        </form>
        <c:if test="${requestScope.LIST_PRODUCTS == null || empty requestScope.LIST_PRODUCTS}">
            <p>This category has no product</p>
        </c:if>
        <c:if test="${requestScope.LIST_PRODUCTS != null}">
            <c:if test="${not empty requestScope.LIST_PRODUCTS}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Product Name</th>
                            <th>Product Image</th>
                            <th>Category Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" varStatus="counter" items="${requestScope.LIST_PRODUCTS}">
                        <form action="MainController" method="POST">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    <input type="text" name="productName" value="${product.productName}" required=""/>  
                                    ${requestScope.ERROR_UPDATE.productNameError}
                                </td>
                                <td>
                                    <img src="${product.productImage}" style="width:200px;height:200px;"/>
                                    ${requestScope.ERROR_UPDATE.productImageError}
                                </td>
                                <td>
                                    <select name="categoryName">
                                        <c:forEach var="cat" items="${requestScope.LIST_CATEGORY}">
                                            <c:if test="${product.category.categoryName eq cat.categoryName}">
                                                <option value="${cat.categoryName}" selected="selected">${cat.categoryName}</option>
                                            </c:if>
                                            <c:if test="${cat.categoryName ne product.category.categoryName}">
                                                <option value="${cat.categoryName}">${cat.categoryName}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    ${requestScope.ERROR_UPDATE.categoryNameError}
                                </td>
                                <td>
                                    <input type="number" name="quantity" value="${product.quantity}" min="0"/> 
                                    ${requestScope.ERROR_UPDATE.quantityError}
                                </td>
                                <td>
                                    <input type="number" name="price" value="${product.price.stripTrailingZeros().toPlainString()}" min="1000" step="500"/></td>
                                ${requestScope.ERROR_UPDATE.priceError}
                                <td>
                                    <input type="submit" name="action" value="Update"/>
                                    <input type="hidden" name="productID" value="${product.productID}"/>
                                    <input type="hidden" name="productImage" value="${product.productImage}"/>
                                    <input type="hidden" name="search" value="${param.search}"/>
                                </td>
                                <th>
                                    <input type="submit" name="action" value="Delete"/>
                                    <input type="hidden" name="productID" value="${product.productID}"/>
                                    <input type="hidden" name="search" value="${param.search}"/>
                                </th>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:if>
</c:if>
</body>
</html>
