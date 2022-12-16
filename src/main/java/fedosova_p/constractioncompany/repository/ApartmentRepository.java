package fedosova_p.constractioncompany.repository;

import fedosova_p.constractioncompany.model.Apartment;
import fedosova_p.constractioncompany.model.Building;
import fedosova_p.constractioncompany.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    List<Apartment> findByBuilding(Building building);

    @Query(value = "SELECT a FROM apartments a join contracts c on a.apartment_id = c.apartment.apartment_id " +
            "WHERE a.building = ?1 AND c.status = ?2")
    List<Apartment> findByStatus(Building building, Status status);
    List<Apartment> findByBuildingAndNumber(Building building, int number);
    List<Apartment> findByBuildingAndFloor(Building building, int floor);
    List<Apartment> findByBuildingAndEntrance(Building building, int entrance);
    @Query(value = "SELECT a FROM apartments a " +
            "WHERE a.building = ?1 and a.number_rooms = ?2")
    List<Apartment> findByBuildingAndNumber_rooms(Building building, int numberRooms);
    @Query(value = "SELECT a FROM apartments a " +
            "WHERE a.building = ?1 and a.total_area between ?2 AND ?3")
    List<Apartment> findByTotal_areaBetween(Building building, double start, double end);
    @Query(value = "SELECT a FROM apartments a " +
            "WHERE a.building = ?1 and a.living_area between ?2 AND ?3")
    List<Apartment> findByLiving_areaBetween(Building building, double start, double end);
}
