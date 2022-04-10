package pk.engineeringthesis.laboratoriesmanagementsystem.informationdashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InformationDashboardRepository extends JpaRepository<InformationDashboard, Long>{

    @Query("SELECT i from InformationDashboard i where i.isActive =:isActive ORDER BY i.date DESC")
    List<InformationDashboard> findByIsActive(@Param("isActive")Boolean isActive);

}
