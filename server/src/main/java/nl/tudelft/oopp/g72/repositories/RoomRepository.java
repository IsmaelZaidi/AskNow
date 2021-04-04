package nl.tudelft.oopp.g72.repositories;

import javax.transaction.Transactional;
import nl.tudelft.oopp.g72.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository("RoomRepository")
public interface RoomRepository extends JpaRepository<Room,Long> {
    @Query("SELECT r FROM Room r WHERE r.joincodeModerator = ?1")
    Room findByJoincodeModerator(String joincodeModerator);

    @Query("SELECT r FROM Room r WHERE r.joincodeStudent = ?1")
    Room findByJoincodeStudent(String joincodeStudent);

    @Query("SELECT r.open FROM Room r WHERE r.joincodeStudent = ?1")
    boolean isOpen(String joincodeStudent);

    @Transactional
    @Modifying
    @Query("UPDATE Room SET open = false WHERE joincodeStudent = ?1")
    void setRoomClosed(String joincodeStudent);
}
