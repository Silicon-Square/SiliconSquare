<%@ page import="it.siliconsquare.model.administration.User" %>
<%@ page import="it.siliconsquare.common.redirect.Page" %>
<%@ page import="it.siliconsquare.common.redirect.Action" %>
<%@ page import="it.siliconsquare.common.Security" %>
<%@ page import="it.siliconsquare.controller.UserController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="it" dir="ltr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">


    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css"
          integrity="sha256-2XFplPlrFClt0bIdPgpz8H7ojnk10H69xRqd9+uTShA=" crossorigin="anonymous"/>
<link href="<%=Page.HOME%>style.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <link rel="shortcut icon" href="https://resources.siliconsquare.it/logo/favicon.ico?v=1.1.2" type="image/x-icon">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;500;700;800&display=swap" rel="stylesheet">
</head>
<body> 

<!-- Utility -->
<script type="text/javascript" src="<%=Page.HOME%>js/utility.js"></script>
<script type="text/javascript" src="<%=Page.HOME%>js/users-utility.js"></script>
<c:import url='<%=Page.HOME+"/users/edit-user-form.html"%>'/>


<%
    String banned = "";
request.getSession().setAttribute("user", (User) request.getSession().getAttribute("user")); 
    if(((User) request.getSession().getAttribute("user")) != null && ((User) request.getSession().getAttribute("user")).isBanned())
        banned = "banned-avatar";

    //    User user = (User) request.getSession().getAttribute("user");
    if (Security.isAuthorized(Action.ADMIN_PANEL, request.getSession())) {
%>
<div class="container-fluid bg-dark text-white px-lg-4 px-md-4 px-sm-4 px-3">
    <div class="container">
        <div id="administrator-bar" class="row">
            <div class="col-6 col-md-3">
                <a href="<%=Page.SUGGESTCOMPONENT%>" class="nav-link text-white">
                    <p>
                        <i class="fas fa-cog"></i>
                        Components</p>
                </a>
            </div>

            <div class="col-6 col-md-3">
                <a href="<%=Page.USERS%>" class="nav-link text-white">
                    <p>
                        <i class="fas fa-users"></i>
                        Users</p>
                </a>
            </div>
        </div>

    </div>
</div>
<% } %>
<nav class="navbar navbar-expand-lg bg-light px-lg-5 px-md-5 px-sm-4 px-3" id="main-header">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"><i class="fas fa-bars"></i></span>
    </button>


    <a class="navbar-brand" href="<%=Page.HOME%>"><img
            src="https://resources.siliconsquare.it/homepage/OnlywritingsDefOnlineLinear.png"/></a>

    <div class=" mr-3 order-lg-last d-flex">
        <% if (((User) request.getSession().getAttribute("user")) == null) {%>
        <a class="my-button" href="<%=Page.LOGIN%>">
            Login
        </a>
        <% } else { %>
        <div class="flex-shrink-0 dropdown pt-1">
            <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle <%=banned%>" id="dropdownUser2"
               data-bs-toggle="dropdown" aria-expanded="false">
                <img src="<%=((User) request.getSession().getAttribute("user")).getAvatar()%>"
                     alt="<%=((User) request.getSession().getAttribute("user")).getName()%> <%=((User) request.getSession().getAttribute("user")).getSurname()%> avatar"
                     width="32"
                     height="32" class="rounded-circle">
            </a>
            <div class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownUser2">
                <a class="dropdown-item" href="<%=Page.PROFILE%>"> Hey, 
                    <b><%=((User) request.getSession().getAttribute("user")).getUsername()%>
                    </b></a>
                <hr class="dropdown-divider">
                <a class="dropdown-item" href="#" onclick='editUserPopUp(<%=((User)request.getSession().getAttribute("user")).getIdUser()%>);'><i class="far fa-user-circle"></i> Profile settings</a>
                <% if (Security.isAuthorized(Page.SUGGESTCOMPONENT, (User) request.getSession().getAttribute("user"))) { %>
                <a class="dropdown-item" href="<%=Page.COMPONENT%>"><i class="far fa-comment-dots"></i> Suggest Component</a>
                <%} %>
                <a class="dropdown-item" href="<%=Page.PROFILE%>"><i class="far fa-heart"></i>
                    Saved Configurations</a>
                <hr class="dropdown-divider">
                <a class="dropdown-item" href="<%=Action.LOGOUT.value%>"><i class="fas fa-power-off"></i> Logout</a>
            </div>
        </div>
        <% } %>
    </div>

    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item active"><a href="<%=Page.HOME%>" class="nav-link px-2 link-secondary">Home</a></li>
            <li class="nav-item"><a href="<%=Page.CONFIGURATOR%>" class="nav-link px-2 link-dark">Configurator</a></li>
            <li class="nav-item"><a href="<%=Page.COMPONENT%>" class="nav-link px-2 link-dark">Components</a></li>
            <li class="nav-item"><a href="<%=Page.TEMPLATES%>" class="nav-link px-2 link-dark">Templates</a></li>
            
            <li class="nav-item"><a href="<%=Page.SOCIAL%>" class="nav-link px-2 link-dark">Square</a></li>
        </ul>   

        <ul class="navbar-nav me-2" style="margin-right: 120px">
            <li class="nav-item">
                <form class="col col-lg-auto mb-auto order-lg-last">
                    <%-- <input type="search" class="form-control" placeholder="Search..." aria-label="Search"> --%>
                </form>
            </li>
        </ul>
    </div>
</nav>
</body>
<script>
    var action = new Map();
$(document).ready(function () {
    <%for(it.siliconsquare.model.administration.Action act : UserController.getAllActions()){%>
    action.set('<%=act.getName()%>', '<%=act.getPath()%>');
    <%}%>
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/41f83b0fb7.js" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    var action = new Map();
$(document).ready(function () {
    <%for(it.siliconsquare.model.administration.Action act : UserController.getAllActions()){%>
    action.set('<%=act.getName()%>', '<%=act.getPath()%>');
    <%}%>
    });
</script>

</html>
