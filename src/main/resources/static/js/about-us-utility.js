var mailTarget = document.getElementById("f-mail-target");
var resetReceiverButton = document.getElementById("reset-receiver");

/* vv dinamic admin card load vv */
window.addEventListener("load", (event) => {
    var data;

    $.ajax({
        type: "POST",
        url: "/getUserByRole/Admin",
        contentType: "text/plain",
        dataType: "json",
        success: function (data) {
            createAdminCards(data);
        },
        error: function (e) {
            console.log("errore durante il caricamento degli admin...");
        },
    });
});

function createAdminCards(data) {
    for (var i = 0; i < data.length; ++i) {
        $("#cardSection").append(
            '<div class="column col-12 col-sm-6 col-md-3">' +
            '<div class="card">' +
            '<img src="' +
            data[i].avatar +
            '" alt="' +
            data[i].name +
            '">' +
            '<div class="container">' +
            "<h2>" +
            data[i].name +
            " " +
            data[i].surname +
            "</h2>" +
            '<p class="title">Admin & Developer</p>' +
            "<p>Some text that describes me lorem ipsum ipsum lorem.</p>" +
            '<p id="hiddenMail' +
            data[i].name +
            '" style="display: none">' +
            data[i].email +
            "</p>" +
            '<a id="contact-' +
            data[i].name +
            '" class="btn btn-primary w-100" href="#" role="button" onclick="setEmail(\'' +
            data[i].name +
            "');\">Contact</a>" +
            "</div>" +
            "</div>" +
            "</div>"
        );
    }
}

/* ^^ dinamic admin card load ^^ */

/* dinamic load issues */

window.addEventListener("load", (event) => {
    var data;

    $.ajax({
        type: "GET",
        url: "/allIssues",
        contentType: "text/plain",
        dataType: "json",
        success: function (data) {
            populateIssueSelection(data);
        },
        error: function (e) {
            console.log("errore durante il caricamento dei topicIssues...");
        },
    });
});

function populateIssueSelection(data) {
    var issues = document.getElementById("f-issue");

    for (var i = 0; i < data.length; i++) {
        const issue = JSON.stringify();

        var opt = document.createElement("option");
        opt.innerHTML = data[i].topic;

        issues.appendChild(opt);
    }
}

/* dinamic load issues */

function setEmail(email) {
    event.preventDefault();

    document.getElementById("contact-us").scrollIntoView();

    mailTarget.value = "to: " + email;

    resetReceiverButton.style.display = "inline";
}

/* vv send issue form vv */

const formName = document.getElementById("f-name");
const formMail = document.getElementById("f-mail");
const formMailTarget = document.getElementById("f-mail-target");
const formIssue = document.getElementById("f-issue");
const formText = document.getElementById("f-text");
const formButtonSubmit = document.getElementById("btnSubmit");

function validateMail() {
    if (
        /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(formMail.value) ||
        formMail.value == ""
    ) {
        formMail.style.border = "none";
        formMail.style.margin = "0px";
        return true;
    } else {
        formMail.style.margin = "-2px";
        formMail.style.border = "solid red 2px";
        return false;
    }
}

formMail.addEventListener("keyup", validateMail);

formButtonSubmit.addEventListener("click", () => {
    if (filledFields()) {
        sendFormData();
    } else {
        Swal.fire({
            position: "top-end",
            icon: "error",
            title: "devi compilare tutti i campi correttamente",
            showConfirmButton: false,
            timer: 1000,
        });
    }
});

function filledFields() {
    return (
        formName.value == "" ||
        formMail.value == "" ||
        formText.value == "" ||
        !validateMail
    );
}

function getAdminMail(target) {
    var targetName = target.split(" ");
    var mail;

    if (targetName[1].includes("@")) {
        mail = targetName[1];
        return mail;
    }

    mail = document.getElementById("hiddenMail" + targetName[1]);
    return mail.innerHTML;
}

function sendFormData() {
    //stop submit the form, we will post it manually.
    event.preventDefault();

    var data = new FormData();

    data.append(formName.id, formName.value);
    data.append(formMail.id, formMail.value);
    data.append("f-mail-target", getAdminMail(formMailTarget.value));
    data.append(formIssue.id, formIssue.value);
    data.append(formText.id, formName.value);

    // disabled the submit button
    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/contact",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 800000,
        success: function (data) {
            $("#btnSubmit").prop("disabled", false);
            successFormInviation();
        },
        error: function (e) {
            errorFormInviation();
            $("#btnSubmit").prop("disabled", false);
        },
    });
}

function errorFormInviation() {
    Swal.fire({
        position: "top-end",
        icon: "error",
        title: "c'è statp un errore durante l'invio",
        showConfirmButton: false,
        timer: 1500,
    });
}

function successFormInviation() {
    formName.value = "";
    formMail.value = "";
    formText.value = "";

    Swal.fire({
        position: "top-end",
        icon: "success",
        title: "il messaggio è stato inviato",
        showConfirmButton: false,
        timer: 1000,
    });
}

/* ^^ send issue form ^^ */
