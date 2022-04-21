window.onload =  function getCount() {
    $.ajax({
        type : "GET",
        url : "/getcount",
        success : function(data)  {
            $('#doakceptacji').html
            ("Do akceptacji ("+data+")");
        },
        error : function()  {

        }
    });
}

