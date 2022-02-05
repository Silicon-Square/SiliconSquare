<%@ page import="java.util.Properties" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.IOException" %>
<%@ page import="it.siliconsquare.logger.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    if(!Security.isAuthorized(Page.ABOUTUS, request.getSession()))
        response.sendRedirect(Page.ERROR);

    Properties properties = new Properties();
    URL applicationProperty = this.getClass().getClassLoader().getResource("application.properties");
    try {
        properties.load(new FileInputStream(applicationProperty.getPath()));
    } catch (IOException e) {
        Logger.getInstance().captureException(e);
    }
    String siteMail = properties.getProperty("spring.mail.username");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Silicon Square - About Us</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%=Page.HOME%>css/about-us-style.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <link rel="shortcut icon" href="https://resources.siliconsquare.it/logo/favicon.ico?v=1.1.2" type="image/x-icon">

</head>
<body>


<%@ include file="header.jsp" %>

<!-- general presentation section -->

<div class="about-section header-image">
    <h1>About Us Page</h1>
    <p>This is us</p>
    <p>We will be happy to assist and help you in your sit experience</p>
</div>

<!-- general presentation section -->

<!-- card info section -->

<div class="container-fluid justify-content-center">
    <div id="cardSection" class="row justify-content-center section">
    </div>

    <!-- card info section -->

    <!-- contact us section -->

    <div id="contact-us" class="row input-container m-auto section">
        <div class="col-12">
            <h1>Contact us</h1>
        </div>

        <div class="col-12">
            <div class="styled-input wide">
                <input id="f-name" type="text" required/>
                <label>Name</label>
            </div>
        </div>
        <div class="col-md-6 col-sm-12">
            <div class="styled-input">
                <input id="f-mail" type="email" required/>
                <label>Your Email</label>
            </div>
        </div>
        <div class="col-md-6 col-sm-12">
            <div class="styled-input" style="float:right;">
                <input id="f-mail-target" class="pe-5" type="text" value="to: <%=siteMail%>" readonly/>
                <button id="reset-receiver" type="button" onclick="setEmail('<%=siteMail%>')"
                        class="btn-close position-absolute top-50 end-0 translate-middle-y me-3"
                        aria-label="Close"></button>
            </div>
        </div>
        <div class="col-12 my-3">
            <select id="f-issue" class="styled-combobox" name="subjects">
            </select>
        </div>
        <div class="col-xs-12">
            <div class="styled-input wide">
                <textarea id="f-text" required></textarea>
                <label>Message</label>
            </div>
        </div>
        <div class="col-xs-12">
            <div id="btnSubmit" class="my-button">Send Message</div>
        </div>
    </div>
    <!-- contact us section -->

</div>


<%@ include file="/WEB-INF/jsp/footer.jsp" %>

<script>
    var mailTarget = document.getElementById('f-mail-target');
    var resetReceiverButton = document.getElementById('reset-receiver');


    /* vv dinamic admin card load vv */
    window.addEventListener('load', (event) => {
        var data;

        $.ajax({
            type: 'POST',
            url: "/getUserByRole/Admin",
            contentType: "text/plain",
            dataType: 'json',
            success: function (data) {
                createAdminCards(data);
            },
            error: function (e) {
                console.log("errore durante il caricamento degli admin...");
            }
        });
    });

    function createAdminCards(data) {

        for (var i = 0; i < data.length; ++i) {
            $('#cardSection').append('<div class="column col-12 col-sm-6 col-md-3">' +
                '<div class="card text-center">' +
                '<img src="' + data[i].avatar + '" alt="' + data[i].name + '">' +
                '<div class="container">' +
                '<h2>' + data[i].name + ' ' + data[i].surname + '</h2>' +
                '<p class="title">Admin & Developer</p>' +
                '<p class=" mb-3">If you want to contact me click the button below</p>' +
                '<p id="hiddenMail'+ data[i].name +'" style="display: none">'+ data[i].email +'</p>' +
                '<a id="contact-' + data[i].name + '" class="contact my-button w-100" href="#" role="button" onclick="setEmail(\'' + data[i].name + '\');">Contact</a>' +
                '</div>' +
                '</div>' +
                '</div>');
        }
    }

    /* ^^ dinamic admin card load ^^ */

    /* dinamic load issues */

    window.addEventListener('load', (event) => {
        var data;

        $.ajax({
            type: 'GET',
            url: "<%=Action.GET_ISSUES.value%>",
            contentType: "text/plain",
            dataType: 'json',
            success: function (data) {
                populateIssueSelection(data);
            },
            error: function (e) {
                console.log("errore durante il caricamento dei topicIssues...");
            }
        });
    });

    function populateIssueSelection(data) {
        var issues = document.getElementById('f-issue');

        for (var i = 0; i < data.length; i++) {
            var opt = document.createElement("option");
            opt.innerHTML = data[i].topic;

            issues.appendChild(opt);
        }
    }

    /* dinamic load issues */

    function setEmail(email) {
        event.preventDefault();

        document.getElementById('contact-us').scrollIntoView();

        mailTarget.value = "to: " + email;

        resetReceiverButton.style.display = 'inline';
    }

    resetReceiverButton.addEventListener('click', () => {
        mailTarget.value = "to: <%=siteMail%>";

        resetReceiverButton.style.display = 'none';
    });

    /* vv send issue form vv */

    const formName = document.getElementById('f-name');
    const formMail = document.getElementById('f-mail');
    const formMailTarget = document.getElementById('f-mail-target');
    const formIssue = document.getElementById('f-issue');
    const formText = document.getElementById('f-text');
    const formButtonSubmit = document.getElementById('btnSubmit');

    function validateMail(){
        if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(formMail.value) || formMail.value == '')
        {
            formMail.style.border = "none";
            formMail.style.margin = "0px";
            return true;
        }else{
            formMail.style.margin = "-2px";
            formMail.style.border = "solid red 2px";
            return false;
        }
    }

    formMail.addEventListener('keyup', validateMail);

    formButtonSubmit.addEventListener('click', () => {
        if(!notFilledFields()) {
            sendFormData()
        }else {
            Swal.fire({
                position: 'top-end',
                icon: 'error',
                title: 'devi compilare tutti i campi correttamente',
                showConfirmButton: false,
                timer: 1000
            });
        }
    });

    function notFilledFields(){
        return (formName.value == '' || formMail.value == '' || formText.value == '' || !validateMail());
    }

    function getAdminMail(target){

        var targetName = target.split(' ');
        var mail;

        if(targetName[1].includes("@")) {
            mail = targetName[1];
            return mail;
        }

        mail = document.getElementById('hiddenMail' + targetName[1] );
        return mail.innerHTML;
    }

    function sendFormData(){
        //stop submit the form, we will post it manually.
        event.preventDefault();

        var data = new FormData;

        data.append(formName.id, formName.value);
        data.append(formMail.id, formMail.value);
        data.append('f-mail-target', getAdminMail(formMailTarget.value));
        data.append(formIssue.id, formIssue.value);
        data.append(formText.id, formText.value);

        // disabled the submit button
        $("#btnSubmit").prop("disabled", true);

                successFormInviation();
        $.ajax({
            type: "POST",
            url: "<%=Action.CONTACT_US.value%>",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 800000,
            success: function (data) {
            },
            error: function (e) {
                // errorFormInviation();
            },complete: function () {
                $("#btnSubmit").prop("disabled", false);
            }
        });
    }

    function errorFormInviation(){
        Swal.fire({
            position: 'top-end',
            icon: 'error',
            title: 'c\'è stato un errore durante l\'invio',
            showConfirmButton: false,
            timer: 1500
        });
    }

    function successFormInviation(){
        formName.value = '';
        formMail.value = '';
        formText.value = '';

        Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'il messaggio è stato inviato',
            showConfirmButton: false,
            timer: 1000
        });
    }

    /* ^^ send issue form ^^ */
</script>

<script src='<%=Page.HOME%>js/about-us-utility.js'></script>

<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</body>
</html>
