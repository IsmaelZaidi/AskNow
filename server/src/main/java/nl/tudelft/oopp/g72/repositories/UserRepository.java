package nl.tudelft.oopp.g72.repositories;

import java.util.List;
import nl.tudelft.oopp.g72.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.token = ?1")
    User findByToken(String token);

    @Query("SELECT u FROM User u WHERE u.room.id = ?1")
    List<User> usersInRoom(long roomId);

}
