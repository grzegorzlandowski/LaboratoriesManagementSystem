function sendFile(file, editor) {
    data = new FormData();
    data.append("file", file);
    $.ajax({
        data : data,
        type : "POST",
        url : "/upload",
        cache : false,
        contentType : false,
        processData : false,
        success : function(data, textStatus, xhr)  {
            $(editor).summernote('editor.insertImage', data);
        },
        error : function()  {
            //alert("Podczas dodawania zdjęcia wystąpił problem.");
            swal({
                text: "Podczas dodawania zdjęcia wystąpił problem.",
            });
        }
    });
}
function deleteFile(path) {
    $.ajax({
        data : {path : path},
        type : "POST",
        url : "/deleteimage",
        success : function()  {
            //alert("Zdjęcie poprawnie usunięte.");
            swal({
                text: "Zdjęcie poprawnie usunięte.",
            });
        },
        error : function(data, textStatus, xhr)  {
            alert("Podczas usuwania zdjęcia nastąpił problem.");
            swal({
                text: "Podczas usuwania zdjęcia nastąpił problem.",
            });
        }
    });

}