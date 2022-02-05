<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="it.siliconsquare.common.redirect.Page" %>
<%@ page import="it.siliconsquare.common.Security" %>

<%
    if(Security.isAuthorized(Page.PROFILE, request.getSession()))
        response.sendRedirect(Page.HOME);
%>

<!-- style -->
<link rel="stylesheet" href="<%=Page.HOME%>css/login-register-style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css"/>

<!-- utility -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <link rel="shortcut icon" href="https://resources.siliconsquare.it/logo/favicon.ico?v=1.1.2" type="image/x-icon">
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="../js/utility.js" type="text/javascript"></script>

<!-- adaptive size -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<div id="back-to" class="drop" style="cursor: pointer;" onclick="history.back();">
    <i class="fas fa-arrow-circle-left"></i> return home
</div>

<div class="container" id="container">
    <div id="sign-up-container" class="form-container sign-up-container">
        <form id="register-form" method="post">
            <h1>Create an Account</h1>
            <div class="full-name">
                <input type="text" name="name" placeholder="Name" required/>
                <input type="text" name="surname" placeholder="Surname" required/>
            </div>
            <input type="text" name="username" placeholder="Username" required/>
            <input id="email" type="email" name="email" placeholder="Email" required onkeyup="validateMail()" />
            <div class="password">
                <input type="password" id="register-password" name="password" placeholder="Password"
                       onkeyup="validatePassword('register-password','submit')" required/>
                <i class="bi bi-eye-slash" id="toggle-register-password"
                   onclick="showPassword('#register-password',this);"></i></div>
            <span id='message'></span>
            <button id="submit" class="button-submit">Sign Up</button>
            <a id="already-registered-link" href="#">Already registered? <b>Login Now</b></a>
        </form>
    </div>
    <div id="sign-in-container" class="form-container sign-in-container">
        <form id="login-form" method="post">
            <h1>Log In</h1>
            <br>
            <input type="email" id="login-email" name="email" placeholder="Email" required/>
            <div class="password">
                <input type="password" id="login-password" name="password" placeholder="Password" required/>
                <i class="bi bi-eye-slash" id="toggle-login-password"
                   onclick="showPassword('#login-password',this);"></i></div>
            <button id="login-btn" class="button-submit">Login</button>
            <a id="lost-password-link" href="#">Forgot your password?</a>
            <a id="not-registered-link" href="#">Not registered yet? <b>Sign Up Now</b></a>
        </form>
    </div>
    <div id="lost-password" class="form-container sign-in-container">
        <form action="/lost-password" method="POST">
            <h1>Forgot Password</h1>
            <br>
            <input type="email" id="lost-password-email" name="email" placeholder="Email" required/>
            <button class="button-submit">Confirm</button>
            <a id="login-link" href="#">Do you already have an account? <b>Login</b></a>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <h1>Already a member?</h1>
                <p>What are you waiting for? Sign in and keep sharing your silicon ideas with the communitiy.</p>
                <button class="ghost" id="signIn">Login</button>
            </div>
            <div class="overlay-panel overlay-right">
                <h1>Hey, buddy!</h1>
                <p>What are you waiting for? Sign up today and begin your journey with Silicon Square</p>
                <button class="ghost" id="signUp">Sign Up</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<%=Page.HOME%>js/login-register-utility.js"></script>
<script src="../js/authentication-utility.js"></script>