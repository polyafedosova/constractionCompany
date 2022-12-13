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
}
