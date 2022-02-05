<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title></title>
    <link href="https://resources.siliconsquare.it/assets/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=Page.HOME%>Homepage/home.css" rel="stylesheet">

    <link rel="shortcut icon" href="https://resources.siliconsquare.it/logo/favicon.ico?v=1.1.2" type="image/x-icon">
</head>
<body>
<main role="main">
    <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel" style=" ">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
                    aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                    aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                    aria-label="Slide 3"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="3"
                    aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active"
                 style="background-image: url(https://resources.siliconsquare.it/homepage/Configurazione.jpg)">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Configurator</h5>
                    <p>The central part of our site where you can create the build you prefer</p>
                </div>
            </div>
            <div class="carousel-item"
                 style="background-image: url(https://resources.siliconsquare.it/homepage/Template.png)">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Templates</h5>
                    <p>Ready-to-use configurations for every price and power level</p>
                </div>
            </div>
            <div class="carousel-item"
                 style="background-image: url(https://resources.siliconsquare.it/homepage/Social.jpg)">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Square</h5>
                    <p>A platform to interact and compare with the posts of others</p>
                </div>
            </div>
            <div class="carousel-item"
                 style="background-image: url(https://resources.siliconsquare.it/assets/images/siliconsquare_motherboard.jpg)">
                <div class="carousel-caption d-none d-md-block">
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
                data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
                data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>

    <div class="container marketing">

        <div class="row d-flex">
            <div class="container col-lg-4">
                <a href="<%=Page.CONFIGURATOR%>" >
                    <div class="big-button" >
                        <div class="image">
                            <img src="https://resources.siliconsquare.it/homepage/Configurazione.jpg" alt="">
                        </div>
                        <h2>Configurator</h2>
                        <p>You can choose the various components by following our guidelines and our recommended components
                            to bring your ideal setup to life</p>
                    </div>
                </a>
            </div>
            <div class="container col-lg-4">
                <a href="<%=Page.TEMPLATES%>" >
                    <div class="big-button">
                        <div class="image">
                            <img src="https://resources.siliconsquare.it/homepage/Template.png">
                        </div>
                        <h2>Templates</h2>
                        <p>You will have access to predefined configurations where you can take inspiration and transfer them to the configurator 
                            to modify them according to your tastes and needs</p>
                    </div>
                </a>
            </div>
            <div class="container col-lg-4">
                <a href="<%=Page.SOCIAL%>">
                    <div class="big-button">
                        <div class="image">
                            <img src="https://resources.siliconsquare.it/homepage/Social.jpg">
                        </div>
                        <h2>Square</h2>
                        <p>The social part where you can publish posts, share a configuration created by you,
                            carry out surveys and interact with other users of the platform</p>
                    </div>
                </a>
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette d-flex">
            <div class="col-md-7 m-auto">
                <h2 class="featurette-heading"> Easy build, easy meet, easy life</h2>
                <p class="lead">Our aim is to make life easier for those new to the world of computing
                     and still wants to touch and become familiar with this magnificent world. Also on the site
                     you will discover a world of features that will make your life easier and increase your luggage
                     cultural about information technology.</p>
            </div>
            <div class="col-md-5 m-auto">
                <img src="https://resources.siliconsquare.it/homepage/Easyb.jpg"
                     class="shadow mb-5 bg-body rounded img-fluid">
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette d-flex">
            <div class="col-md-7 order-md-2 m-auto">
                <div class="container-fluid">
                    <div class="row">
                        <div class="order-md-12"><a href="<%=Page.ABOUTUS%>" role="button"><div class="my-button">Contact us &raquo; </div></a></div>
                        <h2><div class="featurette-heading d-none d-md-inline">Contact Us</div></h2>
                        <p class="contact lead">You can contact us at any time by sending a ticket by simply entering your email and the message to send.
                         You can ask anything, starting from a simple curiosity, up to a report for a system malfunction.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-5 order-md-1 m-auto">
                <img src="https://resources.siliconsquare.it/homepage/Contactus.jpg"
                     class="shadow mb-5 bg-body rounded img-fluid">
            </div>
        </div>

        <hr class="featurette-divider">

    </div>

</main>

</body>

<script>window.jQuery || document.write('<script src="https://resources.siliconsquare.it/assets/js/vendor/jquery.slim.min.js"><\/script>')</script>
<script src="https://resources.siliconsquare.it/assets/dist/js/bootstrap.bundle.min.js"></script>

</html>
