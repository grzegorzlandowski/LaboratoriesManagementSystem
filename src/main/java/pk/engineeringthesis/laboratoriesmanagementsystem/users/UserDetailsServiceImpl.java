package pk.engineeringthesis.laboratoriesmanagementsystem.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Nie Znaleziono Użytkownika");
        }

        return new MyUserDetails(user);
    }
    public User getUserByUsername(String username)
    {
        User user = userRepository.getUserByUsername(username);
        return user;
    }
    public User getUserById(Long id)
    {
        User user = userRepository.getUserById(id);
        return user;
    }
    public User getUserByEmail(String email)
    {
        User user = userRepository.getUserByEmail(email);
        return user;
    }
    public int userCountByStatus(String status){
        return userRepository.userCountByStatus(status);
    }
    public List<User> getUserByStatus(String status)
    {
        List<User> user = userRepository.getUserByStatus(status);
        return user;
    }
    public void save(User user) {
        userRepository.save(user);
    }
}
