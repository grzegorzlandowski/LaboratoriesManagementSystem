package pk.engineeringthesis.laboratoriesmanagementsystem.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class NotificationService {

    @Autowired
    private NotificationRepository repo;

    public void save(Notification notification) {
        repo.save(notification);
    }
}
