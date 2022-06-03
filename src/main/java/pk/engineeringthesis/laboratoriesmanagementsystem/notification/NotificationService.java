package pk.engineeringthesis.laboratoriesmanagementsystem.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;
import java.util.List;

@Service
@Transactional
public class NotificationService {

    @Autowired
    private NotificationRepository repo;

    public void save(Notification notification) {
        repo.save(notification);
    }

    public List<Notification> listAll(){
        return repo.findAll();

    }
    public List<Notification> getactivenotificationbyuser(User user){

        return repo.getactivenotificationbyuser(user);
    }
    public List <Notification> getactivenotificationbyuserlimit5(Long id){

        return repo.getactivenotificationbyuserlimit5(id);
    }
    public Notification get(Long id) {
        return repo.findById(id).get();
    }
    public List<Notification> getarchivalnotificationbyuser(User user){

        return repo.getarchivalnotificationbyuser(user);
    }


}
