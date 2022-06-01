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

function JSconfirmDeleteLabEquipment(message,href,href2){
    swal(message, {
        buttons: {
            cancel: "Anuluj",
            Stanowisko: "Stanowisko",
            Wyposazenie: "Wyposażenie z Bazy",

        },
    })
        .then((value) => {
            if(value=="Wyposazenie")
            {
                window.location=href;
            }
            if(value=="Stanowisko")
            {
                window.location=href2;
            }
        });
}

function JSAddLabEquipment(message,href,href2){
    swal(message, {
        buttons: {
            cancel: "Anuluj",
            Nowe: "Nowe",
            Baza: "z Bazy",

        },
    })
        .then((value) => {
            if(value=="Baza")
            {
                window.location=href;
            }
            if(value=="Nowe")
            {
                window.location=href2;
            }
        });
}

