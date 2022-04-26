package pk.engineeringthesis.laboratoriesmanagementsystem.laboratory;

import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;

import javax.persistence.*;

@Entity
public class Laboratory {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private Float area;
    private int seats;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "supervisor_id", nullable = true)
    private User supervisorId;

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
}
