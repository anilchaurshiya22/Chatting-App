$(function () {
    var newChat = false;
    $("#chat-messages").hide();
    $("#new-chat").show();
    $('#to').chosen();
    $("#chat-messages").show();
    $("#new-chat").hide();
    
    console.log("ready!");
    $("#new-message").on("click", function () {
        $("#messages").html("");
        $("#chat-messages").hide();
        $("#new-chat").show();
        newChat = true;
    });
    
    $('#message').on("keydown", function(e){
        if(e.keyCode === 13) {
            if(newChat) {
                if($('#to option:selected').length > 0 != "" && $('#message').val() != "") {
                    $.ajax({
                        url: baseUrl + 'chat/new-chat',
                        type: 'POST',
                        data: {
                            friends: $('#to').val(),
                            message: $('#message').val()
                        },
                        success: function(data) {
                            data = JSON.parse(data);
                            if(data.response == "success") {
                                $('.search-choice-close').trigger("click");
                                $("#new-chat").hide();
                                $("#chat-messages").show();
                                $("#chat-messages").attr("chat_id", data.chat_id);
                                var chat_name = data.chat_name;
                                if(data.chat_name.indexOf("/") >= 0) {
                                    chat_name = data.chat_name.replace("/" + name, "").replace(name + "/", "");
                                } else {
                                    chat_name = data.chat_name.replace(", " + name, "").replace(name + ", ", "");
                                }
                                $("#chat-name").text(chat_name);
                                $("#messages").append("<li class=\"list-group-item\"><div class=\"row\"><div class=\"col-sm-2\">"+data.from+"</div><div class=\"col-sm-10\">"+$('#message').val()+"</div></div></li>");
                                $('#message').val("");
                                newChat = false;
                            }
                        }
                    });      
                }
            } else {
                if($('#message').val() != "") {
                    $.ajax({
                        url: baseUrl + 'chat/send-message',
                        type: 'POST',
                        data: {
                            chat_id: $('#chat-messages').attr("chat_id"),
                            message: $('#message').val()
                        },
                        success: function(data) {
                            data = JSON.parse(data);
                            if(data.response == "success") {
                                $("#messages").append("<li class=\"list-group-item\"><div class=\"row\"><div class=\"col-sm-2\">"+data.from+"</div><div class=\"col-sm-10\">"+$('#message').val()+"</div></div></li>");
                                $('#message').val("");
                                $("#chat-area").animate({ scrollTop: $("#chat-area").height() }, "slow");
                            }
                        }
                    });      
                }
            }
            return false;
        }
    });
});