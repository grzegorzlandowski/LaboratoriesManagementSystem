package pk.engineeringthesis.laboratoriesmanagementsystem.reportsystem;

import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ReportMessages {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;
    private LocalDateTime date;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id")
    private ReportSystem report;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public ReportSystem getReport() {
        return report;
    }

    public User getUser() {
        return user;
    }
}
