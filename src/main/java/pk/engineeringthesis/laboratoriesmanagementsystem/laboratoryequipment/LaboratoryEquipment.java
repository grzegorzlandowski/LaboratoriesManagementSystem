package pk.engineeringthesis.laboratoriesmanagementsystem.laboratoryequipment;

import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.Laboratory;

import javax.persistence.*;

@Entity
public class LaboratoryEquipment {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private int quantity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "laboratory_id")
    private Laboratory laboratory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }
}
