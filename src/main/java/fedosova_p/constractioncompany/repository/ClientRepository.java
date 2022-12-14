package fedosova_p.constractioncompany.repository;

import fedosova_p.constractioncompany.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface ClientRepository extends JpaRepository<Client, Long>  {

    @Query(value = "SELECT c FROM clients c WHERE c.second_name LIKE %?1% AND " +
            "c.first_name LIKE %?2% AND c.middle_name LIKE %?3% AND c.phone LIKE %?4%" +
            "AND c.birth_date between ?5 and ?6 AND c.passport LIKE %?7%")
    Page<Client> find(String secondName, String firstName, String middleName,
                      String phone, Date start, Date end, String passport, Pageable pageable);

    @Query("select c from clients c")
    Page<Client> findAllPage(Pageable pageable);
}
