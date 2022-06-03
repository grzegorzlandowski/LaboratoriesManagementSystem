package pk.engineeringthesis.laboratoriesmanagementsystem.timetable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.Laboratory;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;
import java.time.LocalDateTime;
import java.util.List;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    //@Query("SELECT t FROM Timetable t WHERE (t.start BETWEEN :start AND :end OR t.end BETWEEN :start AND :end) AND t.laboratory = :laboratory")
    @Query("from Timetable t where not(t.end <= :start or t.start >= :end) AND t.laboratory = :laboratory")
    List<Timetable> isFree(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("laboratory")Laboratory laboratory);
    @Query("from Timetable t where not(t.end < :from or t.start > :to) AND t.laboratory = :laboratory AND t.confirmed = true")
    List<Timetable> findBetween(@Param("from") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime start, @Param("to") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,@Param("laboratory")Laboratory laboratory);
    @Query("SELECT t FROM Timetable t INNER JOIN t.laboratory l WHERE  l.supervisorId = :supervisor AND t.confirmed = false")
    List<Timetable> confirmTimetable(@Param("supervisor") User supervisor);
}
