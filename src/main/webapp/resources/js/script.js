$(function () {
    var newChat = false;
    $('#to').chosen();
    $("#new-chat").hide();
    $("#chat-messages").hide();
    $("#message-box").hide();

    console.log("ready!");
    $("#new-message").on("click", function () {
        $("#messages").html("");
        $("#chat-messages").hide();
        $("#new-chat").show();
        $("#message-box").show();
        newChat = true;
    });

    $('#message').on("keydown", function (e) {
        if (e.keyCode === 13) {
            if (newChat) {
                if ($('#to option:selected').length > 0 != "" && $('#message').val() != "") {
                    $.ajax({
                        url: baseUrl + 'chat/new-chat',
                        type: 'POST',
                        data: {
                            friends: $('#to').val(),
                            message: $('#message').val()
                        },
                        success: function (data) {
                            data = JSON.parse(data);
                            if (data.response == "success") {
                                $('.search-choice-close').trigger("click");
                                $("#new-chat").hide();
                                $("#chat-messages").show();
                                $("#chat-messages").attr("chat_id", data.chat_id);
                                var chat_name = data.chat_name;
                                if (data.chat_name.indexOf("/") >= 0) {
                                    chat_name = data.chat_name.replace("/" + name, "").replace(name + "/", "");
                                } else {
                                    chat_name = data.chat_name.replace(", " + name, "").replace(name + ", ", "");
                                }
                                $("#chat-name").text(chat_name);
                                $("#messages").append("<li message_id=\"" + data.message_id + "\" class=\"list-group-item\"><div class=\"row\"><div class=\"col-sm-2\">" + data.from + "</div><div class=\"col-sm-8\">" + $('#message').val() + "</div><div class=\"col-sm-2\">" + data.date + "</div></div></li>");
                                $('#message').val("");
                                newChat = false;
                            }
                        }
                    });
                }
            } else {
                if ($('#message').val() != "") {
                    $.ajax({
                        url: baseUrl + 'chat/send-message',
                        type: 'POST',
                        data: {
                            chat_id: $('#chat-messages').attr("chat_id"),
                            message: $('#message').val()
                        },
                        success: function (data) {
                            data = JSON.parse(data);
                            if (data.response == "success") {
                                $("#messages").append("<li message_id=\"" + data.message_id + "\" class=\"list-group-item\"><div class=\"row\"><div class=\"col-sm-2\">" + data.from + "</div><div class=\"col-sm-8\">" + $('#message').val() + "</div><div class=\"col-sm-2\">" + data.date + "</div></div></li>");
                                $('#message').val("");
                                $("#chat-area").animate({scrollTop: $("#chat-area").height()}, "slow");
                            }
                        }
                    });
                }
            }
            return false;
        }
    });

    $('.chat').on("click", function () {
        $.ajax({
            url: baseUrl + 'chat/get-chat/' + $(this).attr("chat_id"),
            type: 'get',
            success: function (data) {
                data = JSON.parse(data);
                chat = JSON.parse(data.chat);
                messages = JSON.parse(data.messages);
                if (data.response == "success") {
                    $('.search-choice-close').trigger("click");
                    $("#new-chat").hide();
                    $("#chat-messages").show();
                    $("#chat-messages").attr("chat_id", chat.id);
                    var chat_name = chat.name;
                    if (chat.name.indexOf("/") >= 0) {
                        chat_name = chat.name.replace("/" + name, "").replace(name + "/", "");
                    } else {
                        chat_name = chat.name.replace(", " + name, "").replace(name + ", ", "");
                    }
                    $("#chat-name").text(chat_name);
                    $('#messages').html("");
                    $.each(messages, function (i, message){
                        $("#messages").append("<li message_id=\"" + message.id + "\" class=\"list-group-item\"><div class=\"row\"><div class=\"col-sm-2\">" + message.sender.firstName + " " + message.sender.lastName + "</div><div class=\"col-sm-10\">" + message.message + "</div></div></li>");
                    });
                    $('#message').val("");
                    $("#message-box").show();
                    newChat = false;
                }
            }
        });
    });
});
function checkNewMessage() {
    $.ajax({
        url: baseUrl + 'chat/check-new-messages/' + $("#chat-messages").attr("chat_id") + '/' + $(".list-group-item:last").attr("message_id"),
        type: 'get',
        success: function (data) {
            data = JSON.parse(data);
            messages = JSON.parse(data.messages);
            if (data.response == "success") {
                $.each(messages, function (i, message){
                    $("#messages").append("<li message_id=\"" + message.id + "\" class=\"list-group-item\"><div class=\"row\"><div class=\"col-sm-2\">" + message.sender.firstName + " " + message.sender.lastName + "</div><div class=\"col-sm-10\">" + message.message + "</div></div></li>");
                });
            }
        },
//        complete: function() {
//            setTimeout(checkNewMessage,1000);
//        }
    });
}
setInterval(checkNewMessage, 1000);