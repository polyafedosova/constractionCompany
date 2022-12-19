package fedosova_p.constractioncompany.repository;

import fedosova_p.constractioncompany.model.Apartment;
import fedosova_p.constractioncompany.model.Client;
import fedosova_p.constractioncompany.model.Contract;
import fedosova_p.constractioncompany.model.Employee;
import fedosova_p.constractioncompany.model.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContractRepository extends JpaRepository<Contract, Long>  {

    Page<Contract> findByApartment(Apartment apartment, Pageable pageable);
    Page<Contract> findByClient(Client client, Pageable pageable);
    Contract findOneByClient(Client client);
    Page<Contract> findByEmployee(Employee employee, Pageable pageable);
    Page<Contract> findByStatus(Status status, Pageable pageable);
    @Query("select c from contracts c")
    Page<Contract> findAllPage(Pageable pageable);
}
