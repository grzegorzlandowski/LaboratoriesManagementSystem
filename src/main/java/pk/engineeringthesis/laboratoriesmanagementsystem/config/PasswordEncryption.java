package pk.engineeringthesis.laboratoriesmanagementsystem.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncryption {

    public String encodepassword(String pass)
    {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedPassword =encoder.encode(pass);
        return encodedPassword;
    }
}
