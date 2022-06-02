package pk.engineeringthesis.laboratoriesmanagementsystem.laboratory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;

import java.util.List;


public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {

    @Query("SELECT l from Laboratory l where l.supervisorId = :supervisorId")
    List<Laboratory> findBySupervisor(@Param("supervisorId") User Supervisor);

}
