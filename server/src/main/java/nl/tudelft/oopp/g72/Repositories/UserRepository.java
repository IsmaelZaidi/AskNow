package nl.tudelft.oopp.g72.repositories;

import nl.tudelft.oopp.g72.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
}
