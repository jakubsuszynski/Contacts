var password = $('#password');
var telephone = $('#telephone');
var registerButton = $('#registerButton');
var text = $('#passwordStrengthText');
var telephoneText = $('#telephoneText');

var passwordFlag = false;
var telephoneFlag = false;
registerButton.prop('disabled', true);
// if()
check = function () {
    if (passwordFlag && telephoneFlag) {
        registerButton.prop('disabled', false);
    }
    else {
        registerButton.prop('disabled', true);
    }
};
check(); //todo - repair editing where proper telephone and password has to be 'touched' to unlock the button
//password length security
password.on('input', function () {
    var value = $(this).val();
    var passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;


    if (!passwordPattern.test(value)) {
        text.html("Hasło musi zawierać co najmniej jedną wielką literę i cyfrę oraz składać się z 8 znaków");
        passwordFlag = false;
    }
    else {
        text.html("");
        passwordFlag = true;
    }
    check();


});
//check telephone requirements
telephone.on('input', function () {
    var value = $(this).val();
    var numberPattern = /^(?=.*\d)[0-9]{4,}$/;


    if (!numberPattern.test(value)) {
        telephoneText.html("Numer telefonu musi składać się z przynajmniej 4 cyfr.");
        telephoneFlag = false;

    }
    else {
        telephoneText.html("");
        telephoneFlag = true;
    }

    check();
});


