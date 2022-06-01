package pk.engineeringthesis.laboratoriesmanagementsystem.laboratoryequipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.Laboratory;

import java.util.List;

@Service
@Transactional
public class EquipmentDetailsService {

    @Autowired
    private EquipmentDetailsRepository repo;

    public void save(EquipmentDetails laboratoryEquipment) {
        repo.save(laboratoryEquipment);
    }

    public Integer countEquipmentDetailst(EquipmentDetails equipmentDetails){

        return repo.countEquipmentDetailst(equipmentDetails);
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<EquipmentDetails> listAll(){
        return repo.findAll(Sort.by(Sort.Direction.ASC, "title"));

    }
}
