package pk.engineeringthesis.laboratoriesmanagementsystem.laboratory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LaboratoryService {

    @Autowired
    private LaboratoryRepository repo;

    public void save(Laboratory laboratory) {
        repo.save(laboratory);
    }


}
