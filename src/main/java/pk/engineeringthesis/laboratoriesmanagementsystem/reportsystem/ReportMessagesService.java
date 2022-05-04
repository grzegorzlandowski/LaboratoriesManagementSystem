package pk.engineeringthesis.laboratoriesmanagementsystem.reportsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ReportMessagesService {

    @Autowired
    private ReportMessagesRepository repo;

    public void save(ReportMessages reportMessages) {
        repo.save(reportMessages);
    }
    public List<ReportMessages> getMessagetoReport(ReportSystem report)
    {
        return repo.getMessagetoReport(report);
    }
}
