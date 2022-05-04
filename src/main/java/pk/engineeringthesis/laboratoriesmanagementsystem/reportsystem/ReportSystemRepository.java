package pk.engineeringthesis.laboratoriesmanagementsystem.reportsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;

import java.util.List;


public interface ReportSystemRepository  extends JpaRepository<ReportSystem, Long> {

    @Query("SELECT r FROM ReportSystem r WHERE r.applicant =:User Order BY r.date DESC")
    List<ReportSystem> getUserReports(@Param("User") User users);
    @Query("SELECT r FROM ReportSystem r WHERE r.supervisor =:User Order BY r.date DESC")
    List<ReportSystem> getSupervisorReports(@Param("User") User users);
}
