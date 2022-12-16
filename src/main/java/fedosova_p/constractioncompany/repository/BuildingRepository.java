package fedosova_p.constractioncompany.repository;

import fedosova_p.constractioncompany.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Long> {

    //@Query(value = "SELECT * FROM buildings b", nativeQuery = true)
    //List<Building> findAll();
    @Query(value = "SELECT b FROM buildings b WHERE b.city LIKE %?1% AND " +
            "b.street LIKE %?2% AND b.number LIKE %?3% AND b.name LIKE %?4% " +
            "AND b.constraction_start_date between ?5 and ?6")
    List<Building> find(String city, String street, String number, String name,
                        Date consStart, Date consEnd);

    @Query(value = "SELECT b FROM buildings b WHERE b.city LIKE %?1% AND " +
            "b.street LIKE %?2% AND b.number LIKE %?3% AND b.name LIKE %?4% " +
            "AND b.constraction_start_date between ?5 and ?6 " +
            "AND b.commissioning_date IS NOT NULL")
    List<Building> findIsCommission(String city, String street, String number, String name,
                        Date consStart, Date consEnd);
}
