package fedosova_p.constractioncompany.repository;

import fedosova_p.constractioncompany.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>  {
}
