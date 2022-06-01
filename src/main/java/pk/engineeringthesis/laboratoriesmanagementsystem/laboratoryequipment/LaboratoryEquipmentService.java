package pk.engineeringthesis.laboratoriesmanagementsystem.laboratoryequipment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class LaboratoryEquipmentService {

    @Autowired
    private LaboratoryEquipmentRepository repo;

    public void save(LaboratoryEquipment laboratoryEquipment) {
        repo.save(laboratoryEquipment);
    }

    public LaboratoryEquipment get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
