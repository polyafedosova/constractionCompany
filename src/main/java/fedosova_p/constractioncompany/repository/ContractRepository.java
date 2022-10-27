package fedosova_p.constractioncompany.repository;

import fedosova_p.constractioncompany.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long>  {
}
