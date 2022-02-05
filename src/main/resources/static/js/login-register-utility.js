const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

const submitBtn = $("#submit");
const loginBtn = $("#login-btn");

const emailField = $("#email");

function hideLostPasswordContainer() {
    const lostPasswordEmail = document.getElementById('lost-password-email').value;
    document.getElementById('login-email').value = lostPasswordEmail;
    lostPasswordContainer.style.display = 'none';
}

signUpButton.addEventListener('click', () => {
    hideLostPasswordContainer();
    container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});

const lostPassword = document.getElementById("lost-password-link");
const login = document.getElementById("login-link");
const lostPasswordContainer = document.getElementById('lost-password');

lostPassword.addEventListener('click', () => {
    const loginEmail = document.getElementById('login-email').value;
    document.getElementById('lost-password-email').value = loginEmail;
    lostPasswordContainer.style.display = 'inline';
});

login.addEventListener('click', () => {
    hideLostPasswordContainer();
});

const alreadyRegistered = document.getElementById('already-registered-link');
const notRegistered = document.getElementById('not-registered-link');

const signUpContainer = document.getElementById('sign-up-container');
const signInContainer = document.getElementById('sign-in-container');

var direction_ = "nothing";

window.addEventListener('resize', function (event) {
    resize();
}, true);

window.addEventListener('load', function (event) {
    resize();
}, true);

function resize() {
    if (document.body.clientWidth < 993) {
        if (direction_ == "nothing") {
            if (container.classList.contains('right-panel-active')) {
                direction_ = "right";

                container.classList.remove('right-panel-active');

                signInContainer.style.display = 'none';
                signUpContainer.style.display = 'inline';
            } else {
                direction_ = "left";

                signUpContainer.style.display = 'none';
                signInContainer.style.display = 'inline';
            }
        }
    } else {
        signInContainer.style.display = 'inline';
        signUpContainer.style.display = 'inline';

        if (direction_ === "right")
            container.classList.add('right-panel-active');

        direction_ = "nothing";
    }
}

alreadyRegistered.addEventListener('click', () => {
    signUpContainer.style.display = 'none';
    signInContainer.style.display = 'inline';

    direction_ = "left";
});

notRegistered.addEventListener('click', () => {
    hideLostPasswordContainer();

    signInContainer.style.display = 'none';
    signUpContainer.style.display = 'inline';

    direction_ = "right";
});

function askToServer(path, data){
    return $.ajax({
        type: "post",
        url: path,
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 800000,
        success: function (data) {
            if(data == null)
                this.error();

            var response = data.split(',');

            if(response[0] === "Error"){
                showPopUp(response[1], "error");
                return;
            }

            window.location.replace(response[1]);
        },
        error: function (e) {
            showPopUp("error during comunication", "error");
        }
    });
}

submitBtn.click(function(event) {
    event.preventDefault();

    if(!validateMail())
        return

    var path = "/register";
    var data = new FormData($("#register-form")[0]);
    $("#register-form")[0].reset();
    askToServer(path, data);
});

loginBtn.click(function(event) {
    event.preventDefault();
    var path = "/login";

    var data = new FormData($("#login-form")[0]);
    $("#login-form")[0].reset();

    askToServer(path, data)
});

function validateMail() {
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(emailField.val()) ||emailField.val() == "") {
        emailField.css("background-color", "#eee");
        return true;
    }

    emailField.css("background-color", "rgba(255, 0, 0, 0.4)");
    return false;
}