package pk.engineeringthesis.laboratoriesmanagementsystem.laboratoryequipment;

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Indexed
public class EquipmentDetails {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Field
    private String title;
    @Field
    private String type;
    @Field
    private String description;
    @ContainedIn
    @OneToMany(mappedBy = "equipmentDetails",fetch = FetchType.LAZY)
    private Set<LaboratoryEquipment> laboratoryEquipment = new HashSet<>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<LaboratoryEquipment> getLaboratoryEquipment() {
        return laboratoryEquipment;
    }

    public void setLaboratoryEquipment(Set<LaboratoryEquipment> laboratoryEquipment) {
        this.laboratoryEquipment = laboratoryEquipment;
    }
}
