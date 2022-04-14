package pk.engineeringthesis.laboratoriesmanagementsystem.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);
    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User getUserByEmail(@Param("email") String email);
    @Query("SELECT u FROM User u WHERE u.status = :status")
    public List<User> getUserByStatus(@Param("status") String status);
    @Query("SELECT u FROM User u WHERE u.id = :id")
    public User getUserById(@Param("id")Long id);
    @Query("select count(u.id) FROM User u WHERE u.status= :status")
    public int userCountByStatus(@Param("status") String status);
}
