package fedosova_p.copnstractioncompany.repository;

import fedosova_p.copnstractioncompany.model.Apartment;
import fedosova_p.copnstractioncompany.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    List<Apartment> findByBuilding(Building building);
}
