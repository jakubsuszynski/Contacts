//hiding subcategories, depending on which category is chosen

$('#subcategoryLabel').prop('hidden', true);
$('#business').click(function () {
    $("#defaultSubcategory").prop('disabled', true);
    $('#businessSubcategory').prop('hidden', false).prop('disabled', false);
    $('#privateText').prop('hidden', true).prop('required', false).prop('disabled', true);
    $('#subcategoryLabel').prop('hidden', false);
});
$('#private').click(function () {
    $("#defaultSubcategory").prop('disabled', true);
    $('#businessSubcategory').prop('hidden', true).prop('disabled', true);
    $('#privateText').prop('hidden', false).prop('required', true).prop('disabled', false);
    $('#subcategoryLabel').prop('hidden', false);
});
$('#other').click(function () {
    $("#defaultSubcategory").prop('disabled', false);
    $('#businessSubcategory').prop('hidden', true).prop('disabled', true);
    $('#subcategoryLabel').prop('hidden', true);
    $('#privateText').prop('hidden', true).prop('required', false).prop('disabled', true);
});