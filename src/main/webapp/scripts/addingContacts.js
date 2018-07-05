var password = $('#password');
var telephone = $('#telephone');
var registerButton = $('#registerButton');
var text = $('#passwordStrengthText');
var telephoneText = $('#telephoneText');

var passwordFlag = false;
var telephoneFlag = false;

registerButton.prop('disabled', true);

check = function () {
    if (passwordFlag && telephoneFlag) {
        registerButton.prop('disabled', false);
    }
    else{
        registerButton.prop('disabled', true);
    }
}
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


//hiding subcategories, depending on which category is chosen

$('#subcategoryLabel').prop('hidden', true);
$('#business').click(function () {
    $('#businessSubcategory').prop('hidden', false);
    $('#privateText').prop('hidden', true).prop('required', false);
    $('#subcategoryLabel').prop('hidden', false);
});
$('#private').click(function () {
    $('#businessSubcategory').prop('hidden', true);
    $('#privateText').prop('hidden', false).prop('required', true);
    $('#subcategoryLabel').prop('hidden', false);
});
$('#other').click(function () {
    $('#businessSubcategory').prop('hidden', true);
    $('#subcategoryLabel').prop('hidden', true);
    $('#privateText').prop('hidden', true).prop('required', false);
});

