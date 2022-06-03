package pk.engineeringthesis.laboratoriesmanagementsystem.laboratoryequipment;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.Laboratory;
import javax.persistence.*;

@Indexed
@Entity
public class LaboratoryEquipment {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "laboratory_id")
    private Laboratory laboratory;
    @IndexedEmbedded
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "equipment_details_id")
    private EquipmentDetails equipmentDetails;
    @Field
    private String location;
    private String additionalInformation;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

    public EquipmentDetails getEquipmentDetails() {
        return equipmentDetails;
    }

    public void setEquipmentDetails(EquipmentDetails equipmentDetails) {
        this.equipmentDetails = equipmentDetails;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
