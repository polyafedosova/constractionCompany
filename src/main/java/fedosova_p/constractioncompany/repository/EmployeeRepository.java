package fedosova_p.constractioncompany.repository;

import fedosova_p.constractioncompany.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    Employee findByUsername(String username);
    Employee findByPhone(String phone);
    @Query(value = "SELECT e FROM employees e WHERE e.second_name LIKE %?1% AND " +
            "e.first_name LIKE %?2%")
    List<Employee> findByFirstNameLike(String secondName, String firstName);
}
