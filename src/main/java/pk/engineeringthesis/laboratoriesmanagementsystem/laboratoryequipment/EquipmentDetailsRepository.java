package pk.engineeringthesis.laboratoriesmanagementsystem.laboratoryequipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;




public interface EquipmentDetailsRepository extends JpaRepository<EquipmentDetails, Long> {

    @Query("SELECT count(e) from EquipmentDetails e INNER JOIN e.laboratoryEquipment l where l.equipmentDetails = :equipmentdetails")
    Integer countEquipmentDetailst(@Param("equipmentdetails")EquipmentDetails equipmentDetails);

}
