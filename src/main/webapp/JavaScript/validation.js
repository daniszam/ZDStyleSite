function validate(form_id,email,password) {
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    var address = document.forms[form_id].elements[email].value;
    var password = document.forms[form_id].elements[password].value;
    if(reg.test(address) == false) {
        var checkBox = document.getElementById("checkBox");
        checkBox.textContent("halo");
        console.log("eee")

    }
}