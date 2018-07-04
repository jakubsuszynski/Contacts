var password = document.getElementById('password');
var registerButton = document.getElementById('registerButton');
var text = document.getElementById('passwordStrengthText');
var value = password.value;
if (value === "") {
    registerButton.disabled = true;
}

password.addEventListener('input', function () {
    var value = password.value;
    if (value.length < 4 || value === "") {
        text.innerHTML = "(Za krótkie)";
        registerButton.disabled = true;
    }
    else {
        text.innerHTML = "";
        registerButton.disabled = false;
    }
})