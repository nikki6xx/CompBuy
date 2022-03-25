$("#myPassword").passwordValidation({
    "confirmField":"#myConfirmPassword"
},function (element,valid,match,failedCases){
    $("#errors").html("<pre>" + failedCases.join("\n") + "<pre>");
    if (valid) $(element).css("border","2px solid green");
    if(!valid) $(element).css("border","2px solid red");
    if(valid && match) $("#myConfirmPassword").css("border","2px solid green");
    if(!valid || !match) $("#myConfirmPassword").css("border","2px solid red");
});