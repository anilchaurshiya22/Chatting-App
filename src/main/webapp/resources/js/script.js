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
        $("#chat-messages").attr("chat_id", "0");
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
                                
                                messages = JSON.parse(data.messages);
                                $('#messages').html("");
                                $.each(messages, function (i, message){
                                    $("#messages").append("<li message_id=\"" + message.id + "\" class=\"list-group-item\"><div class=\"row\"><div class=\"col-sm-2\">" + message.sender.firstName + " " + message.sender.lastName + "</div><div class=\"col-sm-8\">" + message.message + "</div><div class=\"col-sm-2\">" + message.sentDate + "</div></div></li>");
                                });
                                $('#message').val("");
                                
                                if($('#chat-list li a[chat_id="' + data.chat_id + '"]').length > 0) {
                                    $('#chat-list li').removeClass("active");
                                    $('#chat-list li a[chat_id="' + data.chat_id + '"]').parent('li').addClass("active");
                                } else {
                                    $('#chat-list li').removeClass("active");
                                    chathtml = '<li class="active"><a href="javascript:;" class="chat" chat_id="'+data.chat_id+'">'+
                                        '<span  style="font-weight: bold;">'+chat_name+'</span><br/>'+data.date+
                                    '</a></li>';
                                    $('#chat-list').prepend(chathtml);
                                }
                                
                                
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

    $(document).on("click", '.chat', function () {
        $.ajax({
            url: baseUrl + 'chat/get-chat/' + $(this).attr("chat_id"),
            type: 'get',
            success: function (data) {
                chat = JSON.parse(data.chat);
                messages = JSON.parse(data.messages);
                if (data.response == "success") {
                    $(this).parent('li').removeClass('chatalert');
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
                        $("#messages").append("<li message_id=\"" + message.id + "\" class=\"list-group-item\"><div class=\"row\"><div class=\"col-sm-2\">" + message.sender.firstName + " " + message.sender.lastName + "</div><div class=\"col-sm-8\">" + message.message + "</div><div class=\"col-sm-2\">" + message.sentDate + "</div></div></li>");
                    });
                    $('#message').val("");
                    $("#message-box").show();
                    $("#chat-area").animate({scrollTop: $("#chat-area").height()}, "slow");
                    $("#chat-list a").each(function(){
                        if($(this).attr("chat_id") == $("#chat-messages").attr("chat_id")) {
                            $(this).parent('li').addClass('active');
                        } else {
                            $(this).parent('li').removeClass('active');
                        }
                    });
                    newChat = false;
                }
            }
        });
    });
    function checkNewMessage() {
        var chatId = $("#chat-messages").attr("chat_id");
        if(chatId != "0") {
            $.ajax({
                url: baseUrl + 'chat/check-new-messages/' + $("#chat-messages").attr("chat_id") + '/' + $(".list-group-item:last").attr("message_id") + '/' + $("#chat-list li:first a").attr("chat_id"),
                type: 'get',
                success: function (data) {
                    messages = JSON.parse(data.messages);
                    if (data.response == "success") {
                        $.each(messages, function (i, message){
                            $("#messages").append("<li message_id=\"" + message.id + "\" class=\"list-group-item\"><div class=\"row\"><div class=\"col-sm-2\">" + message.sender.firstName + " " + message.sender.lastName + "</div><div class=\"col-sm-8\">" + message.message + "</div><div class=\"col-sm-2\">" + message.sentDate + "</div></div></li>");
                        });
                        $("#chat-area").animate({scrollTop: $("#chat-area").height()}, "slow");

                        if(data.chats != "noChat") {
                            chats = JSON.parse(data.chats);

                            chathtml = "";
                            $.each(chats, function (i, chat){
                                if (chat.name.indexOf("/") >= 0) {
                                    chat_name = chat.name.replace("/" + name, "").replace(name + "/", "");
                                } else {
                                    chat_name = chat.name.replace(", " + name, "").replace(name + ", ", "");
                                }
                                liclass = parseInt($("#chat-messages").attr("chat_id")) == chat.id ? "active" : "";
                                chathtml += '<li class="' + liclass + '"><a href="javascript:;" class="chat" chat_id="'+chat.id+'">'+
                                    '<span  style="font-weight: bold;">'+chat_name+'</span><br/>'+chat.lastUpdated+
                                '</a></li>';
                                $('#chat-list').html(chathtml);
                            });
                        }
                        updatedChatIds = JSON.parse(data.updatedChatIds);
                        $('.chat').each(function(){
                            var chatId = parseInt($(this).attr("chat_id"));
                            if($.inArray(chatId, updatedChatIds) >= 0)
                                $(this).parent('li').addClass('chatalert');
                        });
                    }

                }
            });
        } else {
            var lastId = $("#chat-list li").length > 0 ? $("#chat-list li:first a").attr("chat_id") : 0;
            $.ajax({
                url: baseUrl + 'chat/check-new-alert/' + lastId,
                type: 'get',
                success: function (data) {
                    if (data.response == "success") {
                        if(data.chats != "noChat") {
                            chats = JSON.parse(data.chats);

                            chathtml = "";
                            $.each(chats, function (i, chat){
                                if (chat.name.indexOf("/") >= 0) {
                                    chat_name = chat.name.replace("/" + name, "").replace(name + "/", "");
                                } else {
                                    chat_name = chat.name.replace(", " + name, "").replace(name + ", ", "");
                                }
                                liclass = parseInt($("#chat-messages").attr("chat_id")) == chat.id ? "active" : "";
                                chathtml += '<li class="' + liclass + '"><a href="javascript:;" class="chat" chat_id="'+chat.id+'">'+
                                    '<span  style="font-weight: bold;">'+chat_name+'</span><br/>'+chat.lastUpdated+
                                '</a></li>';
                                $('#chat-list').html(chathtml);
                            });
                        }
                        updatedChatIds = JSON.parse(data.updatedChatIds);
                        $('.chat').each(function(){
                            var chatId = parseInt($(this).attr("chat_id"));
                            if($.inArray(chatId, updatedChatIds) >= 0)
                                $(this).parent('li').addClass('chatalert');
                        });
                    }

                }
            });
        }
    }
    setInterval(checkNewMessage, 1000);
});