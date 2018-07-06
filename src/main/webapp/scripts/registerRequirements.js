var password = $('#password');
var registerButton = $('#registerButton');
var passwordText = $('#passwordStrengthText');
registerButton.prop('disabled', true);

//define methods which are checking fields
var checkPassword = function () {
    var passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;

    return passwordPattern.test(password.val());
};


var checkRequirements = function () {
    if (checkPassword()) {
        registerButton.prop('disabled', false);
        passwordText.html("");
    }
    else {
        registerButton.prop('disabled', true);
        passwordText.html("Hasło musi zawierać co najmniej jedną wielką literę i cyfrę oraz składać się z 8 znaków")
    }
};

registerButton.prop('disabled', true);

$('#password, #telephone').on('input', function () {
    checkRequirements()
});