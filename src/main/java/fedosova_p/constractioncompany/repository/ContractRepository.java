package fedosova_p.constractioncompany.repository;

import fedosova_p.constractioncompany.model.Apartment;
import fedosova_p.constractioncompany.model.Client;
import fedosova_p.constractioncompany.model.Contract;
import fedosova_p.constractioncompany.model.Employee;
import fedosova_p.constractioncompany.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long>  {

    List<Contract> findByApartment(Apartment apartment);
    List<Contract> findByClient(Client client);
    Contract findOneByClient(Client client);
    List<Contract> findByEmployee(Employee employee);
    List<Contract> findByStatus(Status status);
}
