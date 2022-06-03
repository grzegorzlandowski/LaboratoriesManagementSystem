package pk.engineeringthesis.laboratoriesmanagementsystem.reportsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ReportMessagesRepository extends JpaRepository<ReportMessages, Long> {

    @Query("SELECT m FROM ReportMessages m WHERE m.report =:Report Order BY m.date DESC")
    List<ReportMessages> getMessagetoReport(@Param("Report") ReportSystem report);
}
