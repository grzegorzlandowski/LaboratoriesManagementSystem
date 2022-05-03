package pk.engineeringthesis.laboratoriesmanagementsystem.laboratory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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


}
