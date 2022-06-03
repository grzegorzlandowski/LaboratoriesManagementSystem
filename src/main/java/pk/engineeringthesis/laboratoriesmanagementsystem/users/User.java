package pk.engineeringthesis.laboratoriesmanagementsystem.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.Laboratory;
import pk.engineeringthesis.laboratoriesmanagementsystem.notification.Notification;
import pk.engineeringthesis.laboratoriesmanagementsystem.reportsystem.ReportMessages;
import pk.engineeringthesis.laboratoriesmanagementsystem.reportsystem.ReportSystem;
import pk.engineeringthesis.laboratoriesmanagementsystem.timetable.Timetable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Indexed
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Field
    private String username;
    private String password;
    private String role;
    private boolean enabled;
    private String status;
    private String firstname;
    private String surname;
    private String email;
    private String telephone;
    private String faculty;
    private String cathedral;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Notification>  notification=new HashSet<Notification>();
    @OneToMany(mappedBy = "supervisorId", fetch = FetchType.LAZY)
    private Set<Laboratory>  laboratory=new HashSet<Laboratory>();
    @OneToMany(mappedBy = "supervisor",fetch = FetchType.LAZY)
    private Set<ReportSystem> supervisorReport = new HashSet<>();
    @OneToMany(mappedBy = "applicant",fetch = FetchType.LAZY)
    private Set<ReportSystem> applicantReport = new HashSet<>();
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<ReportMessages> reportMessage = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Timetable> timetable = new HashSet<>();


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getStatus() {
        return status;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getCathedral() {
        return cathedral;
    }

    public Set<Timetable> getTimetable() {
        return timetable;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setCathedral(String cathedral) {
        this.cathedral = cathedral;
    }

    public void setTimetable(Set<Timetable> timetable) {
        this.timetable = timetable;
    }
}
