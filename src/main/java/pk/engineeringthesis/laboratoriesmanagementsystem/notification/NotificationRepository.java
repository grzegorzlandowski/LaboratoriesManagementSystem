package pk.engineeringthesis.laboratoriesmanagementsystem.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import pk.engineeringthesis.laboratoriesmanagementsystem.informationdashboard.InformationDashboard;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
