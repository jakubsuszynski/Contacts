var password = $('#password');
var telephone = $('#telephone');
var registerButton = $('#registerButton');
var passwordText = $('#passwordStrengthText');
var telephoneText = $('#telephoneText');

registerButton.prop('disabled', true);

//define methods which are checking fields
var checkPassword = function () {
    var passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;
    return passwordPattern.test(password.val());
};


var checkTelephone = function () {
    var numberPattern = /^(?=.*\d)[0-9]{4,}$/;
    return numberPattern.test(telephone.val());
};


var checkRequirements = function () {
    if (checkTelephone() && checkPassword()) {
        registerButton.prop('disabled', false);
        passwordText.html("");
        telephoneText.html("");
    }
    else if (checkTelephone() && !checkPassword()) {
        registerButton.prop('disabled', true);
        passwordText.html("Hasło musi zawierać co najmniej jedną wielką literę i cyfrę oraz składać się z 8 znaków");
        telephoneText.html("");
    }

    else if (!checkTelephone() && checkPassword()) {
        registerButton.prop('disabled', true);
        telephoneText.html("Numer telefonu musi składać się z co najmniej 4 cyfr");
        passwordText.html("");
    }
    else {
        registerButton.prop('disabled', true);
        telephoneText.html("Numer telefonu musi składać się z co najmniej 4 cyfr");
        passwordText.html("Hasło musi zawierać co najmniej jedną wielką literę i cyfrę oraz składać się z 8 znaków")
    }
};



registerButton.prop('disabled', true);
checkRequirements();

$('#password, #telephone').on('input', function () {
    checkRequirements()
});