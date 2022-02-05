/**
 * Checks if two password fields contain the same input.
 */
var checkPassword = function () {
    const button = document.getElementById('submit');
    if (document.getElementById('password').value ==
        document.getElementById('confirm-password').value) {
        button.disabled = false;
        document.getElementById('message').innerHTML = '';

    } else {
        button.disabled = true;
        document.getElementById('message').innerHTML = 'Le password non combaciano';
    }
}

/**
 * Shows the password typed into a password fields, by pressing on 'eye icon'.
 * Advice: put this method into the tag <i></i> of the html element.
 * For example: onclick="showPassword('#passwordFieldID',this);
 * @param passwordField - The input field id which includes the password
 * @param icon - The icon/button which triggers the toggle
 */
function showPassword(passwordField, icon) {
    var password = document.querySelector(passwordField);
    const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
    password.setAttribute('type', type);
    icon.classList.toggle('bi-eye'); // toggle the eye / eye slash icon
}

function validatePassword(passwordFieldID, buttonID) {
    const button = document.getElementById(buttonID);
    button.disabled = false;

    const MIN_LENGTH = 8;
    // const passwordField = document.querySelector(passwordFieldID).value,errors=[];
    const passwordField = document.getElementById(passwordFieldID).value, errors = [];
    if (passwordField.length < MIN_LENGTH) {
        errors.push("almeno " + MIN_LENGTH + " caratteri.");
    }
    if (passwordField.search(/[a-z]/i) == -1) {
        errors.push("almeno una lettera.");
    }
    if (passwordField.search(/[0-9]/) == -1) {
        errors.push("almeno un numero.");
    }
    if (errors.length > 0) {
        var initalMessage = "La password deve contenere: <br>";
        var message = document.getElementById('message');
        message.innerHTML = initalMessage + errors.join("<br>");
        message.style.display = "inline";
        button.disabled = true;

    } else
        document.getElementById('message').innerHTML = "";
    //return true;
}
