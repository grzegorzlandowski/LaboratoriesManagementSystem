package pk.engineeringthesis.laboratoriesmanagementsystem.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
     User getUserByUsername(@Param("username") String username);
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User getUserByEmail(@Param("email") String email);
    @Query("SELECT u FROM User u WHERE u.status = :status")
     List<User> getUserByStatus(@Param("status") String status);
    @Query("SELECT u FROM User u WHERE u.id = :id")
     User getUserById(@Param("id")Long id);
    @Query("select count(u.id) FROM User u WHERE u.status= :status")
    int userCountByStatus(@Param("status") String status);
    @Query("SELECT u from User u where u.status='Zaakceptowane' AND u.role ='ROLE_EMPLOYEE' OR u.role ='ROLE_SUPERVISOR'")
    List<User> findEmployeeAndSupervisor();
    @Query("SELECT u from User u where u.status='Zaakceptowane' AND u.role ='ROLE_SUPERVISOR'")
    List<User> findSupervisor();
}
