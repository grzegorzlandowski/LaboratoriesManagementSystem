package pk.engineeringthesis.laboratoriesmanagementsystem.reportsystem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class ReportSystemService {

    @Autowired
    private ReportSystemRepository repo;


    public void save(ReportSystem reportSystem) {
        repo.save(reportSystem);
    }
}
