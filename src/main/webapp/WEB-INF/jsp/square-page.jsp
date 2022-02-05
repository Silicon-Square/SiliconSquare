<%@ page language="java" %>

<%
    if(((User)request.getSession().getAttribute("user")) == null)
        response.sendRedirect(Page.LOGIN);
    if(!Security.isAuthorized(Page.SOCIAL, request.getSession()))
        response.sendRedirect(Page.ERROR);
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />

    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="<%=Page.HOME%>style.css" rel="stylesheet">

    <script src="https://kit.fontawesome.com/41f83b0fb7.js" crossorigin="anonymous"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <link rel="shortcut icon" href="https://resources.siliconsquare.it/logo/favicon.ico?v=1.1.2" type="image/x-icon">
    <link rel="stylesheet" href="<%=Page.HOME%>css/post-style.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
        *::-webkit-scrollbar,
        *::-webkit-scrollbar-thumb {
        width: 26px;
        border-radius: 13px;
        background-clip: padding-box;
        border: 10px solid transparent;
        }

        *::-webkit-scrollbar-thumb {        
        box-shadow: inset 0 0 0 10px;
        }

        #news{
            margin: 25px 0;
            padding: 10px;

            position: sticky;
            top: 100px;
            max-height: 500px; 

            border-radius: 25px;
            background-color:;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
        }

        .news-info{
            height:90%;
            margin-bottom:5px;

            overflow: auto;
            background-color:;
        }

        .news-buttons{
            background-color:;
            margin: 0 5px;
            height:10%;

            display: flex;

            text-align: center;
        }

        .news-button{
            background-color:;
            border-radius: 15px;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);

            margin: 0 5px;
            height: 100%;
            width: 30%;

            display: table;
        }

        .news-button:active{
            -moz-box-shadow:    inset 0 0 5px #000000;
            -webkit-box-shadow: inset 0 0 5px #000000;
            box-shadow:         inset 0 0 5px #000000;
        }

        .news-button i{
            display: table-cell;
            vertical-align: middle;
            text-align:center;
        }

        .news-button span{
            display: table-cell;
            vertical-align: middle;
            text-align:center;
        }

        #news-image{
            width:100%;
            max-height: 60%;
            object-fit: cover;

            border-radius: 25px;

            position: sticky;
            top:0;
        }

        @media screen and (max-width: 1008px) {
            #news{
                position: relative;
                top: 0;
            }
        }
    </style>
</head>

<body>
    <%@ include file="/WEB-INF/jsp/header.jsp" %>

    <div class="container">
        <div class="row">
            <!-- news -->
            <div id="news" class="col-lg-3 order-lg-last">
                <div class="news-info">
                    <img id="news-image">
                    <div class="m-2">
                        <h5 id="news-title"></h5>
                        <p id="news-description"></p>
                    </div>
                </div>
                <div class="news-buttons">
                    <div class="news-button" onclick="previousNews()">
                        <i class="fas fa-chevron-left"></i>
                    </div>
                    <a id="news-link" class="news-button w-100" target="_blank"> 
                        <span>More about</span>
                    </a>
                    <div class="news-button" onclick="nextNews()">
                        <i class="fas fa-chevron-right"></i>
                    </div>
                </div>
            </div>
            <!-- news -->
            
            <!-- all post -->
            <div id="all-post" class="col-lg-8">

            </div>
            <!-- all post -->

            <div class="col-lg-1">

            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/footer.jsp" %>

    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script type="text/javascript" src="<%=Page.HOME%>js/post-utility.js"></script>


    <script>
        // vv news vv
        const newsTitle = $("#news-title");
        const newsDescription = $("#news-description");
        const newsimag = $("#news-image");
        const newsLink = $("#news-link");
        
        var newsCount = 0;
        var actualNews = 0;
        var articles;

        // vv news navigation vv
        function nextNews(){
            if(articles == null)
                return;

            actualNews = (actualNews + 1) % newsCount;
            loadArticle(actualNews);
        }

        function previousNews(){
            if(articles == null)
                return;
            
            actualNews--;
            if(actualNews < 0)
                actualNews = newsCount - 1;
            loadArticle(actualNews);
        }
        // ^^ news navigation ^^

        // vv fetch news form server vv
        function fetchNews(){
            var about = "\"cpu\" OR \"gpu\"";
            var data = formatDate();
            var key = "694be707f1e64c91a684f0767c0000f6";
            var language = "it"

            var url = 'https://newsapi.org/v2/everything?' +
                    'q=' + about + '&' +
                    'from=' + data + '&' +
                    'sortBy=popularity&' +
                    'language=' + language + '&' +
                    'apiKey=' + key;

            var req = new Request(url);

            return fetch(req).then((response) =>{
                        return response.json();
                    }).then((data) =>{
                        return data.articles;
                    })
        }

        function getArticles(){
            fetchNews().then((response) =>{
                articles = response;
                if(articles == null || articles == undefined)
                    document.getElementById("news").style.display = "none";
                else {
                    newsCount = Object.keys(articles).length;
                    if(newsCount === 0)
                        document.getElementById("news").style.display = "none";
                   else loadArticle(actualNews);
                }
            })
        }
        // ^^ fetch news form server ^^

        // vv visualize article in page vv
        function loadArticle(actualNews){
            newsTitle.html(articles[actualNews].title);
            newsDescription.html(articles[actualNews].description);
            newsimag.attr("src",articles[actualNews].urlToImage);
            newsLink.attr("href",articles[actualNews].url);
        }
        // ^^ visualize article in page ^^
        // ^^ news ^^

        function formatDate() {
            var d = new Date(),
                month = '' + (d.getMonth() + 1),
                day = '' + d.getDate()-1,
                year = d.getFullYear();

            if (month.length < 2) 
                month = '0' + month;
            if (day.length < 2) 
                day = '0' + day;

            return [year, month, day].join('-')+"";
        }

        const allPostSection = $("#all-post");

        //comuni
        function requestPost(path){
           return $.ajax({
                type: "get",
                url: path,
                processData: false,
                contentType: "application/json",
                dataType: "json",
                cache: false,
                timeout: 800000,
                success: function (datas) {
                    if(datas == null){
                        this.error();
                    }
                },
                error: function (e) {
                    showPopUp("errore nel caricamento dei post");
                    console.log(e);
                }
            })
        }



        $( document ).ready(function() {
            getArticles();
            startLoadingPost();
        });

        // vv load post in page vv
        function startLoadingPost() {
            putPlaceHolder(allPostSection);
            requestPost("/otherUsersPosts").then(function(response){
                if(response.length == 0){
                    showPopUp("Nothing to show", "info");
                    return;
                }
                showPostInPage(response, allPostSection);

                continueLoadingPost(response);
            });
        } 

        function continueLoadingPost(response){
            $("div.load-wraper").remove();
            if(response.length <= 0)
                return;

            putPlaceHolder(allPostSection);
            requestPost("/nextOtherUsersPosts/" + (response[response.length - 1].idPost)).then(function(newResponse){
                showPostInPage(newResponse, allPostSection);

                continueLoadingPost(newResponse);
            });
        }
        // ^^ load post in page ^^

        // vv pop up vv
        function showPopUp(text, type){
            Swal.fire({
                position: 'top-end',
                icon: type,
                title: text,
                showConfirmButton: false,
                timer: 2000
            });
        }
        // ^^ pop up ^^
    </script>
    
</body>
</html>
