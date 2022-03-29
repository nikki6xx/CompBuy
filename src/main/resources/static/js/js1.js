$(document).ready(function() {
    $('#myPasswordConf').change(function() {
        let pass = $("#myPassword").val();
        let pass_rep = $("#myPasswordConf").val();
        if (pass != pass_rep) {
            $("#myPasswordConf").css('border', 'red 1px solid');
            $('#errorConfPass').css('display','block')
        }
    });

});