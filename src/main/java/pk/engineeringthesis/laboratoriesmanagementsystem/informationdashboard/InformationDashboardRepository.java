package pk.engineeringthesis.laboratoriesmanagementsystem.informationdashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InformationDashboardRepository extends JpaRepository<InformationDashboard, Long>{
}
