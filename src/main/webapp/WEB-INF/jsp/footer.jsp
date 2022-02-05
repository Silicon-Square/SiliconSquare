<%@ page import="java.sql.Time" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="it.siliconsquare.common.redirect.Page" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <title>Silicon Square</title>

</head>
<body>
<hr>

<footer class="bg-dark-gradient footer">
    <div class="footer-top">
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-lg-3 my-4 col-12 col">
                    <div class="text-center">
                        <div class="mb-4">
                            <img src="https://resources.siliconsquare.it/logo/headerLogo.png" title="" alt="">
                        </div>
                        <div class="text-black-65 mb-4">Made by <br> Silicon Square Developers</div>
                        <div class="nav footer-social-icon d-flex justify-content-center">
                            <a href="https://www.linkedin.com/company/silicon-square">
                                <i class="fab fa-linkedin-in"></i>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-lg-3 my-4 ">
                    <ul class="list-unstyled black-link footer-links ">
                        <li><h5 class="text-black h6 mb-4 ">About</h5></li>
                        <li class="mb-2">
                            <a href="<%=Page.ABOUTUS%>">About Us</a>
                        </li>
                        <li class="mb-2">
                            <a href="<%=Page.SOCIAL%>">Community</a>
                        </li>
                    </ul>
                </div>
                <div class="col-sm-6 col-lg-3 my-4">
                    <ul class="list-unstyled black-link footer-links">
                        <li><h5 class="text-black h6 mb-4">Support</h5></li>
                        <li class="mb-2">
                            <a href="<%=Page.ABOUTUS%>">Help</a>
                        </li>
                        <li class="mb-2">
                            <a href="mailto:support@siliconsquare.it">Email Support</a>
                        </li>
                    </ul>
                </div>
                <div class="col-sm-6 col-lg-3 my-4">
                    <ul class="list-unstyled black-link footer-links">
                        <li><h5 class="text-black h6 mb-4">Contact</h5></li>
                        <li class="mb-2">
                            <a href="mailto:info@siliconsquare.it">Email Info</a>
                        </li>
                        <li class="mb-2 ">
                            <a href="<%=Page.ABOUTUS%>">Help Center</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-bottom footer-border-top light py-3">
        <div class="container text-center">
            <p class="m-0">&copy; 2021-<%=Calendar.getInstance().get(Calendar.YEAR)%> copyright</p>
        </div>
    </div>
</footer>
</body>
</html>
