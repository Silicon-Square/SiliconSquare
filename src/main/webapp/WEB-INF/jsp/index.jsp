<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(!Security.isAuthorized(Page.HOME, request.getSession()))
        response.sendRedirect(Page.ERROR);
%>

<html>  
<head>
    <title>Silicon Square</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css"
          integrity="sha512-NmLkDIU1C/C88wi324HBc+S2kLhi08PN5GDeUVVVC/BVt/9Izdsc9SVeVfA1UZbY3sHUlDSyRXhCzHfr6hmPPw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="shortcut icon" href="https://resources.siliconsquare.it/logo/favicon.ico?v=1.1.2" type="image/x-icon">
</head>
<%-- <body> --%>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<%@ include file="/WEB-INF/jsp/home.jsp" %>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
<%-- </body> --%>
</html>
