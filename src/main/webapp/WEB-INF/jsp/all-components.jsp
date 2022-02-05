<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="it.siliconsquare.common.redirect.Page" %>
<%@ page import="it.siliconsquare.common.redirect.Action" %>
<%@ page import="it.siliconsquare.common.Security" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=Page.TITLE%>Component Categories</title>
    <link rel="stylesheet" type="text/css" href="<%=Page.HOME+"css/component-style.css"%>"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="<%=Page.HOME+"js/component-utility.js"%>"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <link rel="shortcut icon" href="https://resources.siliconsquare.it/logo/favicon.ico?v=1.1.2" type="image/x-icon">
</head>

<% boolean isAuthorized = Security.isAuthorized(Action.EDIT_COMPONENT, (User) request.getSession().getAttribute("user"));
%>
<body onload="loadComponents('<%=Action.ALL_COMPONENT_CATEGORY.value%>','<%=Page.COMPONENT%>','<%=isAuthorized%>')">

<%@include file="header.jsp" %>

<c:import url='<%=Page.HOME+"components/all-components.html"%>'/>

<%@include file="footer.jsp" %>

</body>
</html>
