var password = $('#password');
var registerButton = $('#registerButton');
var text = $('#passwordStrengthText');
var value = password.val();
$('#subcategoryLabel').prop('hidden', true);
if (value === "") {
    registerButton.prop('disabled', true);
}
//password length security
password.on('input', function () {
    var value = $(this).val();
    if (value.length < 4 || value === "") {
        text.html("Za krótkie");
        registerButton.prop('disabled', true);
    }
    else {
        text.html("");
        registerButton.prop('disabled', false);
    }

});
//hiding options, depending on which category is chosen
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

