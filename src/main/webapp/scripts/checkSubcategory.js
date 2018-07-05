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