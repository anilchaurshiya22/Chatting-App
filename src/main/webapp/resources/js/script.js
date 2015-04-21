$(function () {
    $("#chat-messages").hide();
    $("#new-chat").show();
    console.log("ready!");
    $("#new-message").on("click", function () {
        $("#chat-messages").hide();
        $("#new-chat").show();
        friends = {
        };
    });

    $('#to').chosen();
    
    $('#message').on("keydown", function(e){
        if(e.keyCode === 13) {
            $.ajax({
               url: baseUrl + 'chat/new-chat',
                type: 'POST',
                data: {
                    friends: $('#to').val(),
                    message: $('#message').val()
                },
                success: function(data) {
                    console.log(data);
                }
            });
            return false;
        }
    });
});