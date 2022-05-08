package pk.engineeringthesis.laboratoriesmanagementsystem.reportsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;

import java.util.List;


public interface ReportSystemRepository  extends JpaRepository<ReportSystem, Long> {

    @Query("SELECT r FROM ReportSystem r WHERE r.applicant =:User AND r.status <> 'Archiwalne'  Order BY r.date DESC")
    List<ReportSystem> getUserActiveReports(@Param("User") User users);
    @Query("SELECT r FROM ReportSystem r WHERE r.supervisor =:User AND r.status <> 'Archiwalne' Order BY r.date DESC")
    List<ReportSystem> getSupervisorActiveReports(@Param("User") User users);
    @Query("SELECT r FROM ReportSystem r WHERE r.applicant =:User AND r.status='Archiwalne'  Order BY r.date DESC")
    List<ReportSystem> getUserArchivalReports(@Param("User") User users);
    @Query("SELECT r FROM ReportSystem r WHERE r.supervisor =:User AND r.status='Archiwalne' Order BY r.date DESC")
    List<ReportSystem> getSupervisorArchivalReports(@Param("User") User users);
}
