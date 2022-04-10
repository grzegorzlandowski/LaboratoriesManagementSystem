function JSconfirm(message,href){
    swal(message, {
        buttons: {
            cancel: "Anuluj",
            Tak: true,
        },
    })
        .then((value) => {
            if(value=="Tak")
            {
                window.location=href;
            }
        });
}