package pk.engineeringthesis.laboratoriesmanagementsystem.notification;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Notification {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime date;
    private Boolean isRead;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {

        DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return FOMATTER.format(this.date);
    }

    public Boolean getRead() {
        return isRead;
    }

    public User getUser() {
        return user;
    }
}
