package pk.engineeringthesis.laboratoriesmanagementsystem.informationdashboard;

import org.springframework.beans.factory.annotation.Autowired;
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
}
