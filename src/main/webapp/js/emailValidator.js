$(document).ready(function () {

    $('#emailcheck').hide();
// Walidacja Email
    const email =
        document.getElementById('email');
    email.addEventListener('blur', ()=>{
        let regex =
            /^([_\-\.0-9a-zA-Z]+)@([_\-\.0-9a-zA-Z]+)\.([a-zA-Z]){2,7}$/;
        let s = email.value;
        if(regex.test(s)){
            email.classList.remove(
                'is-invalid');
            $('#emailcheck').hide();
            emailError = true;
        }
        else{
            email.classList.remove(
                'is-invalid');
            $('#emailcheck').show();
            $('#emailcheck').html(
                "Adres E-mail nieprawidłowy");
            emailError = false;
        }
    })

// Button wysłania formularza
    $('#submitbtn').click(function () {
        if ((emailError == true)) {
            return true;
        } else {
            return false;
        }
    });
});