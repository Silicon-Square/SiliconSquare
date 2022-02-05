<%@ page import="it.siliconsquare.model.configuration.Configuration" %>
<%@ page import="it.siliconsquare.controller.ConfiguratorController" %>
<%@ page import="java.util.List" %>
<%@ page language="java" %>

<%
    if (!Security.isAuthorized(Page.PROFILE, request.getSession()))
        response.sendRedirect(Page.ERROR);
%>


<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="utf-8"/>

    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="<%=Page.HOME%>style.css" rel="stylesheet">

    <script src="https://kit.fontawesome.com/41f83b0fb7.js" crossorigin="anonymous"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <link rel="shortcut icon" href="https://resources.siliconsquare.it/logo/favicon.ico?v=1.1.2" type="image/x-icon">


    <link rel="stylesheet" href="<%=Page.HOME%>css/account-style.css">
    <link rel="stylesheet" href="<%=Page.HOME%>css/post-style.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body>

<%@ include file="/WEB-INF/jsp/header.jsp" %>


<div class="container">
    <div class="content-page">
        <div class="profile-banner" style="background:url(https://bootdey.com/img/Content/bg1.jpg);">
            <div class="col-lg-3 avatar-container">
                <img src="<%= ((User)request.getSession().getAttribute("user")).getAvatar() %>" class="profile-avatar">
            </div>
        </div>
        <div class="row">
            <div class="col-lg-3">
                <div id="user-info">
                    <ul class="list-group user-menu">
                        <li class="list-group-item">
                            <h4><%= ((User) request.getSession().getAttribute("user")).getName() %> <b><%= ((User) request.getSession().getAttribute("user")).getSurname() %>
                            </b></h4>
                            <h5 id="username"><%= ((User) request.getSession().getAttribute("user")).getUsername() %>
                            </h5>
                            <h5 style="background-color:<%=((User)request.getSession().getAttribute("user")).getRole().getColor()%>"
                                class="rounded-pill"><%= ((User) request.getSession().getAttribute("user")).getRole().getRoleName() %>
                            </h5>
                        </li>
                        <li class="list-group-item">
                            <div class="accordion accordion-flush" id="accordionFlushExample">
                                <div class="accordion-item">
                                    <h2 class="accordion-header" id="myConfigurations">
                                        <button class="accordion-button collapsed" type="button"
                                                data-bs-toggle="collapse"
                                                data-bs-target="#flush-myConfigurationsCollapse" aria-expanded="false"
                                                aria-controls="flush-myConfigurationsCollapse">
                                            <%List<Configuration> configurations = ConfiguratorController.getAllConfigByUserId((User) request.getSession().getAttribute("user"));%>
                                            Saved Builds <span
                                                class="badge bg-primary rounded-pill" id="configuration_count"><%=configurations.size()%></span>
                                        </button>
                                    </h2>
                                    <div id="flush-myConfigurationsCollapse" class="accordion-collapse collapse"
                                         aria-labelledby="flush-myConfigurations"
                                         data-bs-parent="#accordionFlushExample">
                                        <div class="accordion-body">
                                            <div class="list-group-item container justify-content-between align-items-center">
                                                <div class="row">
                                                    <div class="col-7">Name</div>
                                                            <div class="col-5">Actions</div>
                                                            <hr>
                                                            <%
                                                                int count = 0;
                                                                for (Configuration configuration : configurations) {
                                                            %>
                                                            <%int id = configuration.getIdConfiguration();%>
                                                            <div id="configuration_<%=id%>" class="col-12 d-flex">
                                                                <div class="col-7">Build <%=id%>
                                                                </div>
                                                                <div class="col-5 d-flex">
                                                                    <div class="m-auto d-flex">
                                                                        <a href="<%=Page.CONFIGURATOR%>/<%=id%>">
                                                                            <button class='item ml-auto mr-2' data-bs-toggle='tooltip'
                                                                                    data-bs-placement='top' title='Edit'>
                                                                                <i class="fas fa-pen"></i>
                                                                            </button>
                                                                        </a>
                                                                        <button class='item ml-2 mr-auto' data-bs-toggle='tooltip'
                                                                                data-bs-placement='top' title='Delete'
                                                                                onclick="deleteConfig(<%=id%>, '<%=Page.CONFIGURATOR%>')"y">
                                                                        <i class="fas fa-trash"></i>
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                                </div>
                                                                <%
                                                                        if (count != configurations.size() - 1)
                                                                            out.println("<hr>");
                                                                        count++;
                                                                    }
                                                                %>
                                                        <%-- </div> --%>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                        </li>

                    </ul>
                </div>
            </div>

            <div class="col-lg-9">

                <div id="new-post" class="tab-content">
                    <div class="user-profile-content">
                        <div id="crate-post" class="position-relative">
                            <div class="container col-md-11 col-lg 12">

                                <form id="form-datas" enctype="multipart/form-data">

                                    <div class="row py-1">
                                        <div class="form-floating">
                                            <input id="new-post-title" name="new-post-title" type="text"
                                                   class="form-control" placeholder=" ">
                                            <label for="new-post-title">Title</label>
                                        </div>
                                    </div>

                                    <div id="attachments-section" class="row py-1 mx-0">
                                        <div id="attachments">

                                        </div>

                                        <div class="hidden">
                                            <input id="picture" name="picture" type="file"/>
                                            <input id="picture-to-edit" name="picture-to-edit" type="text"/>
                                            <input id="poll-opt1" name="poll-opt1" type="text"/>
                                            <input id="poll-opt2" name="poll-opt2" type="text"/>
                                            <input id="post-in-edit" type="text">
                                        </div>
                                    </div>

                                    <div class="row py-1">
                                        <div class="form-floating">
                                            <textarea id="new-post-description" name="new-post-description"
                                                      class="form-control" placeholder=" "></textarea>
                                            <label for="new-post-description">Description</label>
                                        </div>
                                    </div>

                                </form>

                                <div class="row position-relative post-actions">
                                    <div class="position-absolute p-0 col-6 bottom-0 start-0">
                                        <label for="picture">
                                            <a id="btn-add-picture" class="btn btn-sm btn-default"><i
                                                    class="far fa-images"></i></a>
                                        </label>
                                        <!--<a id="btn-add-configuration" class="btn btn-sm btn-default"><i
                                                class="fas fa-desktop"></i></a>-->
                                        <a id="btn-add-poll" class="btn btn-sm btn-default"><i
                                                class="fas fa-balance-scale"></i></a>
                                    </div>
                                    <div id="buttons" class="col-4 col-md-2 position-absolute bottom-0 end-0 p-0">
                                        <!--<button id="publish-post" type="submit" class="btn btn-primary w-100">Pubblica</button>-->
                                        <div id="publish-post" class="my-button m-0">Post</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="allUserPost">

                </div>
            </div>
        </div>
    </div>
</div>


<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="text/javascript" src="<%=Page.HOME%>js/post-utility.js"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
<%@ include file="/WEB-INF/jsp/account-utility.jsp" %>
</body>
</html>
