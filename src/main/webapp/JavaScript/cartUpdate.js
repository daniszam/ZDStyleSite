document.addEventListener("DOMContentLoaded", function () {
    $('form').submit(function (event) {
        var formID = $(this).attr('id');
        event.preventDefault();
        ajaxPost(formID);
    });
    


    function ajaxPost(formID) {
        var inputs = [];
        console.log("#"+formID+" :input")
        $("#"+formID+" :input").each(function () {
            console.log(this)
            inputs.push(this)
        });
        console.log(inputs);
        var id = $(inputs[1]).val();
        var name = $(inputs[0]).val();
        var img = $(inputs[2]).val();
        var snack = {
            "name": name,
            "id": id,
            "img": img
        };

        var text = '<div class="item">' +
            '<div style="width: 100px; height: 55px; margin-top: 20px">' +
            '<button class="buy btn-4" value="buy" style="width: 80px" >' +
            '<span>drop</span>' +
            '</button>' +
            '</div>'+

            '<div class="image">' +
           '<img src="'+snack.img+'" alt="пвпв" height="50px" width="50px"/>' +
           '</div>' +

            '<div class="description">' +
            '<span>'+snack.name+'</span>' +
            '</div>' +

            '<div class="total-price">$549</div>' +
            '</div>'

        $.ajax({
            type : "POST",
            url : window.location,
            data : snack,
            success : function(result) {
                $('#shopping').append(text);
                if(result.status == "200"){
                    $("#postResultDiv").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>" +
                        "Post Successfully! <br>" +
                        "---> Customer's Info: FirstName = " +
                        result.data.firstname + " ,LastName = " + result.data.lastname + "</p>");
                }else{
                    console.log(result);
                    $("#postResultDiv").html("<strong>Error</strong>");
                }
                console.log(result);
            },
            error : function(e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });
        resetData();
    }

    function resetData(){
        $("#snack_id").val("");
        $("#snack_name").val("");
    }

});

