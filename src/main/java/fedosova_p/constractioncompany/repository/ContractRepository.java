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

    @Query("select c from contracts c where c.apartment = ?1")
    Page<Contract> findByApartment(Apartment apartment, Pageable pageable);
    @Query("select c from contracts c where c.client = ?1")
    Page<Contract> findByClient(Client client, Pageable pageable);
    Contract findOneByClient(Client client);
    @Query("select c from contracts c where c.employee = ?1")
    Page<Contract> findByEmployee(Employee employee, Pageable pageable);
    @Query("select c from contracts c where c.status = ?1")
    Page<Contract> findByStatus(Status status, Pageable pageable);
    @Query("select c from contracts c")
    Page<Contract> findAllPage(Pageable pageable);
}
