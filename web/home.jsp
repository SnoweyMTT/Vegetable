<%-- 
    Document   : home
    Created on : Oct 11, 2021, 3:24:59 AM
    Author     : SnoweyMTT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SnoweyMTT's Store</title>
        <style type="text/css">
            header {
                background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
                    url(https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F37%2F2015%2F06%2F16012245%2Ffruits-vegetables-legumes-blue-background-8a6d17df.jpg);
                background-size: cover;
                height: 100vh;
            }
            
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

        <div id="title">
            <h1>Snowey_MTT's Store</h1>
            <h5>Chào mừng đến với gian hàng mua sắm nhỏ</h5>
        </div>
    </header>
</html>
