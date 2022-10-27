package fedosova_p.constractioncompany.repository;

import fedosova_p.constractioncompany.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
