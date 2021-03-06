package pk.engineeringthesis.laboratoriesmanagementsystem.laboratory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratoryequipment.LaboratoryEquipment;
import pk.engineeringthesis.laboratoriesmanagementsystem.reportsystem.ReportSystem;
import pk.engineeringthesis.laboratoriesmanagementsystem.timetable.Timetable;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Indexed
public class Laboratory {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Field
    private String name;
    @Field
    private String intended;
    @Field
    private String description;
    private String additionalInformation;
    private Float area;
    private int seats;
    @IndexedEmbedded
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "supervisor_id", nullable = true)
    private User supervisorId;
    @JsonIgnore
    @OneToMany(mappedBy = "laboratory",fetch = FetchType.LAZY)
    private Set<ReportSystem> Report = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "laboratory",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Timetable> timetable = new HashSet<>();
    @IndexedEmbedded
    @OneToMany(mappedBy = "laboratory",fetch = FetchType.LAZY)
    private Set<LaboratoryEquipment> laboratoryEquipment = new HashSet<>();

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Float getArea() {
        return area;
    }

    public int getSeats() {
        return seats;
    }

    public User getSupervisorId() {
        return supervisorId;
    }

    public Set<ReportSystem> getReport() {
        return Report;
    }

    public void setReport(Set<ReportSystem> report) {
        Report = report;
    }

    public Set<Timetable> getTimetable() {
        return timetable;
    }

    public void setTimetable(Set<Timetable> timetable) {
        this.timetable = timetable;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setSupervisorId(User supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Set<LaboratoryEquipment> getLaboratoryEquipment() {
        return laboratoryEquipment;
    }

    public void setLaboratoryEquipment(Set<LaboratoryEquipment> laboratoryEquipment) {
        this.laboratoryEquipment = laboratoryEquipment;
    }
    public String getIntended() {
        return intended;
    }

    public void setIntended(String intended) {
        this.intended = intended;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
