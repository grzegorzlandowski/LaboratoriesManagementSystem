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
function JSconfirmEdit(message,href,href2){
    swal(message, {
        buttons: {
            cancel: "Anuluj",
            Haslo: "Hasło",
            Daneuzytkownika: "Dane użytkownika",

        },
    })
        .then((value) => {
            if(value=="Daneuzytkownika")
            {
                window.location=href;
            }
            if(value=="Haslo")
            {
                window.location=href2;
            }
        });
}
