package nl.tudelft.oopp.g72.repositories;

import nl.tudelft.oopp.g72.models.Room;
import nl.tudelft.oopp.g72.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("RoomRepository")
public interface RoomRepository extends JpaRepository<Room, Long> {
}
