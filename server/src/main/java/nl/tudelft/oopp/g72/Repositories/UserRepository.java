package nl.tudelft.oopp.g72.Repositories;

import nl.tudelft.oopp.g72.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
}
