package pk.engineeringthesis.laboratoriesmanagementsystem.timetable;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.Laboratory;
import pk.engineeringthesis.laboratoriesmanagementsystem.reportsystem.ReportMessages;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TimetableService {

    @Autowired
    private TimetableRepository repo;

    public void save(Timetable timetable) {
        repo.save(timetable);
    }
    public List<Timetable> isFree(LocalDateTime start, LocalDateTime end, Laboratory laboratory)
    {
        return repo.isFree(start,end,laboratory);
    }
    public List<Timetable> findBetween(LocalDateTime start,LocalDateTime end,Laboratory laboratory){

        return repo.findBetween(start,end,laboratory);
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
