<%@ page import="it.siliconsquare.model.component.Component" %>
<%@ page import="it.siliconsquare.common.redirect.ComponentCategory" %>
<%@ page import="it.siliconsquare.common.HtmlVisualizer" %>
<%@ page import="it.siliconsquare.common.Security" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>

<html lang="it">
<head>
    <%  
        Component c = (Component) request.getAttribute("component");
        String categoryDisplayName = (String) request.getAttribute("categoryDisplayName");
    %>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title><%=Page.TITLE%><%=c.getTitle()%>
    </title>
    <link rel="shortcut icon" href="https://resources.siliconsquare.it/logo/favicon.ico?v=1.1.2" type="image/x-icon">
</head>

<style>
    td {
        white-space: pre-line;
    }

    .category-list-links {
        text-transform: capitalize;
    }

    figure .img-fluid {
        height: 400px;
    }

</style>


<body>
<script>
    var categoryDisplayName = "<%=categoryDisplayName%>";
</script>
<c:import url='<%=Page.HOME+"components/edit-component.html"%>'/>
<!-- Responsive navbar-->
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<!-- Page content-->
<div class="container mt-5">
    <div class="row">
        <div class="col-lg-8">
            <!-- Post header-->
            <header class="mb-4">
                <!-- Post title-->
                <h1 class="fw-bolder mb-1"><%=c.getTitle()%>
                </h1>
                <!-- Post categories-->
                <a class="badge bg-secondary text-decoration-none link-light category-list-links"
                   href="<%=Page.COMPONENT%>/<%=c.getCategory()%>">
                    <%=HtmlVisualizer.fromSlug(c.getCategory())%>
                </a>
            </header>
            <!-- Preview image figure-->
            <figure class="mb-4"><img class="img-fluid rounded" src="<%=c.getImageLink()%>" alt=<%=c.getTitle()%>/>
            </figure>
            <!-- Post content-->
            <section class="mb-5">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <h3 class="box-title mt-5">Specifications</h3>
                    <div class="table-responsive">
                        <table class="table table-striped table-product">
                            <tbody>
                            <%=HtmlVisualizer.toTableHTML(c.getAllSpecifications(), true)%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </section>

        </div>

        <!-- Side widgets-->
        <div class="col-lg-4">
            <%-- widgets --%>
            <div  style="position: sticky;top: 96px; margin-bottom: 10px;"> 
            <!-- Buy widget-->
            <%if (c.getAmazonLink() != null && c.getAmazonLink() != "") {%>
            <div class="card mb-4">
                <div class="card-header">Buy On Amazon</div>
                <div class="card-body">
                    <a href="<%=c.getAmazonLink()%>"><button class="my-button" type="button">Buy Now
                    </button>
                    </a>
                </div>
            </div>
            <%}%>
            <!-- Categories widget-->
            <div class="card mb-4">
                <div class="card-header">Categories</div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <ul class="list-unstyled mb-0">
                                <%
                                    for (String cat : ComponentCategory.ALL) {
                                        if (cat == c.getCategory()) continue;
                                        String url = Page.COMPONENT + "/" + cat;
                                        out.print("<li class='category-list-links'><a href=\"" + url + "\">" + HtmlVisualizer.fromSlug(cat) + "</a></li>");
                                    } %>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            </div>

        </div>
    </div>
</div>
<!-- Footer-->

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
<script src="<%=Page.HOME%>js/component-utility.js" type="text/javascript"></script>
</body>
</html>
