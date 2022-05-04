package pk.engineeringthesis.laboratoriesmanagementsystem.reportsystem;

import org.hibernate.annotations.GenericGenerator;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.Laboratory;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ReportSystem {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String type;
    private String description;
    private String status;
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "laboratory_id",unique = false)
    private Laboratory laboratory;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "supervisor_id",unique = false)
    private User supervisor;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "applicant_id",unique = false)
    private User applicant;
    @OneToMany(mappedBy = "report",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<ReportMessages> reportMessage = new HashSet<>();

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return FOMATTER.format(this.date);
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public User getSupervisor() {
        return supervisor;
    }

    public User getApplicant() {
        return applicant;
    }

    public Set<ReportMessages> getReportMessage() {
        return reportMessage;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public void setReportMessage(Set<ReportMessages> reportMessage) {
        this.reportMessage = reportMessage;
    }
}
