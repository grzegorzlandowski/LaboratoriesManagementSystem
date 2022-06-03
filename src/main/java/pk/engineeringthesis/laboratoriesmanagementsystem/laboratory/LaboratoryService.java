package pk.engineeringthesis.laboratoriesmanagementsystem.laboratory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;
import java.util.List;

@Service
@Transactional
public class LaboratoryService {

    @Autowired
    private LaboratoryRepository repo;

    public void save(Laboratory laboratory) {
        repo.save(laboratory);
    }
    public List<Laboratory> listAll(){
        return repo.findAll(Sort.by(Sort.Direction.ASC, "name"));

    }
    public Laboratory get(Long id) {
        return repo.findById(id).get();
    }

    public List<Laboratory> findBySupervisor(User Supervisor){

        return repo.findBySupervisor(Supervisor);
    }


}
