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

    $('#to').textext({
        plugins: 'tags prompt focus autocomplete ajax arrow',
        tagsItems: ['Basic', 'JavaScript', 'PHP', 'Scala'],
        prompt: 'Add one...',
        ajax: {
            url: 'http://localhost:8080/SpringChat/resources/json/dataArray.json',
            dataType: 'json',
            cacheResults: true
        }
    });
});