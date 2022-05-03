package pk.engineeringthesis.laboratoriesmanagementsystem.reportsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReportMessagesService {

    @Autowired
    private ReportMessagesRepository repo;

    public void save(ReportMessages reportMessages) {
        repo.save(reportMessages);
    }
}
