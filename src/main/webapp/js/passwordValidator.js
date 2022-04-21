$(document).ready(function () {
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

// Button wysłania formularza
    $('#submitbtn').click(function () {

        validatePassword();
        validateConfirmPassword();

        if ((passwordError == true) &&
            (confirmPasswordError == true)) {
            return true;
        } else {
            return false;
        }
    });
});