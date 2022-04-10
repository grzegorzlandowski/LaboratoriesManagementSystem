package pk.engineeringthesis.laboratoriesmanagementsystem.informationdashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InformationDashboardService {

    @Autowired
    private  InformationDashboardRepository repo;

    public List<InformationDashboard> listAll(){
        return repo.findAll();
    }
    public List<InformationDashboard> listAllSortByDateDesc(){
        return repo.findAll((Sort.by(Sort.Direction.DESC, "date")));
    }

    public InformationDashboard get(Long id) {
        return repo.findById(id).get();
    }

    public void save(InformationDashboard informationdashboard) {
        repo.save(informationdashboard);
    }
}
