package pk.engineeringthesis.laboratoriesmanagementsystem.laboratoryequipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.Laboratory;

@Service
@Transactional
public class LaboratoryEquipmentService {

    @Autowired
    private LaboratoryEquipmentRepository repo;

    public void save(LaboratoryEquipment laboratoryEquipment) {
        repo.save(laboratoryEquipment);
    }
}
