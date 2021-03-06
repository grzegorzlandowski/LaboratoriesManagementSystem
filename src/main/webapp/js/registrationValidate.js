$(document).ready(function () {

    //walidacja nazwy uzytkownika
    $('#usercheck').hide();
    let usernameError = true;
    $('#usernames').keyup(function () {
        validateUsername();
    });

    function validateUsername() {
        let usernameValue = $('#usernames').val();
        if (usernameValue.length == '') {
            $('#usercheck').show();
            usernameError = false;
            return false;
        }
        else if((usernameValue.length < 8)||
            (usernameValue.length > 30)) {
            $('#usercheck').show();
            $('#usercheck').html
            ("Długość nazwy użytkownika powinna wynosić pomiędzy 8 a 30 znaków");
            usernameError = false;
            return false;
        }
        else {
            $('#usercheck').hide();
            usernameError = true;
            return true;
        }
    }

    // Walidacja hasło
    $('#passcheck').hide();
    let passwordError = true;
    $('#password1').keyup(function () {
        validatePassword();
    });
    function validatePassword() {
        let passwordValue =
            $('#password1').val();
        if (passwordValue.length == '') {
            $('#passcheck').show();
            passwordError = false;
            return false;
        }
        if ( passwordValue.length < 8 || !passwordValue.match(/[A-Z]/) || !passwordValue.match(/[A-z]/) || !passwordValue.match(/\d/)) {
            $('#passcheck').show();
            $('#passcheck').html
            ("Hasło musi mieć conajmniej 8 znaków, jedną dużą literę oraz jedną cyfrę");
            passwordError = false;
            return false;
        } else {
            $('#passcheck').hide();
            passwordError = true;
            return true;
        }
    }

    // Walidacja zgodności haseł
    $('#conpasscheck').hide();
    let confirmPasswordError = true;
    $('#password2').keyup(function () {
        validateConfirmPassword();
    });
    function validateConfirmPassword() {
        let confirmPasswordValue =
            $('#password2').val();
        let passwordValue =
            $('#password1').val();
        if (passwordValue != confirmPasswordValue) {
            $('#conpasscheck').show();
            $('#conpasscheck').html(
                "Hasła nie są takie same");
            confirmPasswordError = false;
            return false;
        } else {
            $('#conpasscheck').hide();
            confirmPasswordError = true;
            return true;
        }
    }

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
        validateUsername();
        validatePassword();
        validateConfirmPassword();

        if ((usernameError == true) &&
            (passwordError == true) &&
            (confirmPasswordError == true) &&
            (emailError == true)) {
            return true;
        } else {
            return false;
        }
    });
});