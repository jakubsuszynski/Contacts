$("#begin").click(function () {
    $(".toHide").each(function () {
        $(this).prop('hidden', true)
    });
    $("#begin").prop('hidden', true);
    $(".toEdit").each(function () {
        $(this).prop('hidden', false)
    });
    $("#saveButton").prop('hidden', false);
});
