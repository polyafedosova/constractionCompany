package fedosova_p.constractioncompany.repository;

import fedosova_p.constractioncompany.model.Employee;
import fedosova_p.constractioncompany.model.enums.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    Employee findByUsername(String username);
    Employee findByPhone(String phone);
    @Query(value = "SELECT e FROM employees e WHERE e.second_name LIKE %?1% AND " +
            "e.first_name LIKE %?2% AND e.middle_name LIKE %?3% AND e.phone LIKE %?4%" +
            "AND e.birth_date between ?5 and ?6 AND e.passport LIKE %?7% AND e.username LIKE ?8 AND e.post = ?9")
    List<Employee> find(String secondName, String firstName, String middleName,
                        String phone, Date start, Date end, String passport, String username, Post post);
}
