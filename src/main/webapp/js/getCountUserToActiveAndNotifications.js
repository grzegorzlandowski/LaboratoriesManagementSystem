function getCount() {
    $.ajax({
        type : "GET",
        url : "/getcount",
        success : function(data)  {
            $('#doakceptacji').html
            ("Do akceptacji ("+data+")");
        },
        error : function()  {
            alert("siema")
        }
    });

}

function showNotifications() {

    $.ajax({
        type : "GET",
        url : '/pokazostatniepowiadomienia',
        dataType : 'json',
        success : function (response) {
                if(response[0]==null){
                    $('#notifakcje').append('<div class="notification-list notification-list--unread">\n' +
                        '                                    <div class="notification-list_detail">\n' +
                        '                                        <p id="message1">Brak Powiadomień</p>\n' +
                        '                                        </div>\n' +
                        '                                       </div>\n');

                }
            $.each(response, function (index, datec) {

                $('#notifakcje').append('<div class="notification-list notification-list--unread">\n' +
                    '                                    <div class="notification-list_detail">\n' +
                    '                                        <p id="message1">'+ datec.message+ '</p>\n' +
                    '                                        <p id="date1"><small>'+datec.date+'</small></p>\n' +
                    '                                        </div>\n' +
                    '                                </div>\n');

            });


        },
        error : function() {
            alert('error');
        }
    });
}

var addFunctionOnWindowLoad = function(callback){
    if(window.addEventListener){
        window.addEventListener('load',callback,false);
    }else{
        window.attachEvent('onload',callback);
    }
}
addFunctionOnWindowLoad(showNotifications());
addFunctionOnWindowLoad(getCount());

