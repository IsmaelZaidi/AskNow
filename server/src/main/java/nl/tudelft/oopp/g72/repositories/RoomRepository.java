package nl.tudelft.oopp.g72.repositories;

import nl.tudelft.oopp.g72.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("RoomRepository")
public interface RoomRepository extends JpaRepository<Room, Long> {
}
