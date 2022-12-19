package fedosova_p.constractioncompany.repository;

import fedosova_p.constractioncompany.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    Employee findByUsername(String username);
    @Query(value = "SELECT e FROM employees e WHERE e.second_name LIKE %?1% AND " +
            "e.first_name LIKE %?2% AND e.middle_name LIKE %?3% AND e.phone LIKE %?4%" +
            "AND e.birth_date between ?5 and ?6 AND e.passport LIKE %?7%")
    Page<Employee> find(String secondName, String firstName, String middleName,
                        String phone, Date start, Date end, String passport, Pageable pageable);

    @Query("select e from employees e")
    Page<Employee> findAllPage(Pageable pageable);
}
