package pk.engineeringthesis.laboratoriesmanagementsystem.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

        @Query("SELECT z FROM Notification z WHERE z.user =:User AND z.isRead=false Order BY z.date DESC")
        List<Notification> getactivenotificationbyuser(@Param("User") User users);
        @Query(value="SELECT * FROM notification WHERE user_id =:User AND  is_read=false Order BY  date DESC limit 5",nativeQuery = true)
        List<Notification> getactivenotificationbyuserlimit5(@Param("User") Long id);
        @Query("SELECT z FROM Notification z WHERE z.user =:User AND z.isRead=true Order BY z.date DESC")
        List<Notification> getarchivalnotificationbyuser(@Param("User") User users);
}
