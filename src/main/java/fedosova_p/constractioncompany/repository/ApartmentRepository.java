package fedosova_p.constractioncompany.repository;

import fedosova_p.constractioncompany.model.Apartment;
import fedosova_p.constractioncompany.model.Building;
import fedosova_p.constractioncompany.model.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    @Query("select a from apartments a where a.building = ?1")
    Page<Apartment> findByBuilding(Building building, Pageable pageable);

    @Query(value = "SELECT a FROM apartments a join contracts c on a.apartment_id = c.apartment.apartment_id " +
            "WHERE a.building = ?1 AND c.status = ?2")
    Page<Apartment> findByStatus(Building building, Status status, Pageable pageable);
    @Query("select a from apartments a where a.building = ?1 and a.number = ?2")
    Page<Apartment> findByBuildingAndNumber(Building building, int number, Pageable pageable);
    @Query("select a from apartments a where a.building = ?1 and a.floor = ?2")
    Page<Apartment> findByBuildingAndFloor(Building building, int floor, Pageable pageable);
    @Query("select a from apartments a where a.building = ?1 and a.entrance = ?2")
    Page<Apartment> findByBuildingAndEntrance(Building building, int entrance, Pageable pageable);
    @Query(value = "SELECT a FROM apartments a " +
            "WHERE a.building = ?1 and a.number_rooms = ?2")
    Page<Apartment> findByBuildingAndNumber_rooms(Building building, int numberRooms, Pageable pageable);
    @Query(value = "SELECT a FROM apartments a " +
            "WHERE a.building = ?1 and a.total_area between ?2 AND ?3")
    Page<Apartment> findByTotal_areaBetween(Building building, double start, double end, Pageable pageable);
    @Query(value = "SELECT a FROM apartments a " +
            "WHERE a.building = ?1 and a.living_area between ?2 AND ?3")
    Page<Apartment> findByLiving_areaBetween(Building building, double start, double end, Pageable pageable);

    @Query("select a from apartments a")
    Page<Apartment> findAllPage(Pageable pageable);
}
