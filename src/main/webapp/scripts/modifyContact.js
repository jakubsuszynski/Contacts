$("#begin").click(function () {
    $(".toHide").each(function () {
        $(this).prop('hidden', true)
    });
    $("#begin").prop('hidden', true);
    $(".toEdit").each(function () {
        $(this).prop('hidden', false)
    });
});
$("#end").click(function () {
    $(".toHide").each(function () {
        $(this).prop('hidden', false)
    });
    $("#begin").prop('hidden', false);
    $(".toEdit").each(function () {
        $(this).prop('hidden', true)
    });
});
