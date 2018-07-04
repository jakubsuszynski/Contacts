let password = $('#password');
let registerButton = $('#registerButton');
let text = $('#passwordStrengthText');
let value = password.val();
if (value === "") {
    registerButton.prop('disabled',true);
}

password.on('input', function () {
    let value = $(this).val();
    if (value.length < 4 || value === "") {
        text.html("Za krótkie");
        registerButton.prop('disabled',true);
    }
    else {
        text.html("");
        registerButton.prop('disabled',false);
    }
});

