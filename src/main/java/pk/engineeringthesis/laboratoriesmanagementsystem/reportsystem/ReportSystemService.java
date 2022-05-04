package pk.engineeringthesis.laboratoriesmanagementsystem.reportsystem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.Laboratory;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;
import java.util.List;


@Service
@Transactional
public class ReportSystemService {

    @Autowired
    private ReportSystemRepository repo;


    public void save(ReportSystem reportSystem) {
        repo.save(reportSystem);
    }

    public List<ReportSystem> listAll(){
        return repo.findAll();

    }

    public List<ReportSystem> getUserReports(User users){

        return repo.getUserReports(users);
    }

    public ReportSystem get(Long id) {
        return repo.findById(id).get();
    }

    public List<ReportSystem> getSupervisorReports(User users){

        return repo.getSupervisorReports(users);
    }
}
