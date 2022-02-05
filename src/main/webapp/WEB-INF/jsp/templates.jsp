
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>templates</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
  <link rel="stylesheet" href="<%=Page.HOME%>css/templates.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <link rel="shortcut icon" href="https://resources.siliconsquare.it/logo/favicon.ico?v=1.1.2" type="image/x-icon">
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

  <div class="container">
    <div id="template" class="row featurette d-flex">
        <div class="col-md-7 m-auto">
            <h2 class="featurette-heading"> Configuration entry-level</h2>
            <p class="lead">A configuration for those who want to have fun without having problems by making some compromises with performance</p>
        </div>
        <div class=" col-md-5 m-auto">
            <img src="https://www.tuttotech.net/wp-content/uploads/2020/12/pc-build-economica-recensione.jpg" class="shadow mb-5 bg-body rounded img-fluid ">
        </div>
        <div class="col-md-12 text-center"><a class="my-button" href="<%=Page.HOME%>configurator/67" role="button">Vedi la configurazione</a></div>
    </div>

    <hr class="featurette-divider">

    <div id="template" class="row featurette d-flex">
        <div class="col-md-7 order-md-2 m-auto">
            <h2 class="featurette-heading"> Configuration mid-level</h2>
            <p class="lead">Configuration accessible to all, suitable for those who do not want to compromise with performance given its high computing power</p>
        </div>
        <div class=" col-md-5 order-md-1 m-auto">
            <img src="https://www.tuttotech.net/wp-content/uploads/2021/08/build1000euro.jpg" class="shadow mb-5 bg-body rounded img-fluid">
        </div>
        <div class="col-md-12 order-md-3 text-center"><a class="my-button" href="<%=Page.HOME%>configurator/52" role="button">Vedi la configurazione</a></div>
    </div>

    <hr class="featurette-divider">

    <div id="template" class="row featurette d-flex">
        <div class="col-md-7 m-auto">
            <h2 class="featurette-heading"> Configuration top-level</h2>
            <p class="lead">A configuration with a high cost as much as its performance. Its high performance allows those who use it to enjoy an excellent experience</p>
        </div>
        <div class=" col-md-5 m-auto">
            <img src="https://s3b.cashify.in/gpro/uploads/2020/10/23131555/Gaming-PC-Under-20k.jpg" class="shadow mb-5 bg-body rounded img-fluid ">
        </div>
        <div class="col-md-12 text-center"><a class="my-button" href="<%=Page.HOME%>configurator/53   " role="button">Vedi la configurazione</a></div>
    </div>
  </div>

<%@include file="footer.jsp" %>
</body>
</html>
