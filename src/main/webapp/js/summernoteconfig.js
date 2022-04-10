$(document).ready(function() {
    $('#summernote').summernote({
        height: 400,
        callbacks: {
            onImageUpload: function(files, editor, welEditable) {
                sendFile(files[0], this);
            },
            onMediaDelete : function(target) {

                deleteFile(target[0].src.split('/').pop());
            }
        }
    });
});