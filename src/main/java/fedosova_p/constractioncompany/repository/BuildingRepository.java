package fedosova_p.constractioncompany.repository;

import fedosova_p.constractioncompany.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {

    /*@Query(value = "SELECT b FROM buildings b WHERE b.city LIKE %?1% AND " +
            "b.street LIKE %?2% AND b.number LIKE %?3% AND b.name LIKE %?4% " +
            "AND b.constraction_start_date between ?5 and ?6 AND b.expected_commissioning_date between ?7 and ?8 " +
            "AND if (?9) THEN (b.commissioning_date is not null) else (b.commissioning_date is null) END")
    List<Building> find(String city, String street, String number, String name,
                        Date consStart, Date consEnd, Date expStart, Date expEnd, int b);

     */
}
