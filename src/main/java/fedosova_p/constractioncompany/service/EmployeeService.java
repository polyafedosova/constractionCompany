package fedosova_p.constractioncompany.service;

import fedosova_p.constractioncompany.model.Employee;
import fedosova_p.constractioncompany.model.enums.Role;
import fedosova_p.constractioncompany.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmployeeService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username);
        if (employee == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return employee;
    }

    public Page<Employee> getAll(Pageable pageable) {
        return employeeRepository.findAllPage(pageable);
    }

    public Employee findById(Long employee_ID) {
        if (employeeRepository.findById(employee_ID).isPresent()) {
            return employeeRepository.findById(employee_ID).get();
        }
        return null;
    }

    public boolean updateEmployee(Employee employee) {
        if (!isDataCorrectly(employee)) return false;
        employeeRepository.save(employee);
        return true;
    }

    public boolean isDataCorrectly(Employee employee) {
        //TODO
        return true;
    }

    public boolean saveEmployee(Employee employee) {
        Employee employeeFromDB = employeeRepository.findByUsername(employee.getUsername());
        if (employeeFromDB != null) return false;
        employee.addRole(Role.USER);
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
        return true;
    }

    public boolean deleteEmployee(Employee employee) {
        if (employeeRepository.findById(employee.getEmployee_id()).isPresent()) {
            employeeRepository.deleteById(employee.getEmployee_id());
            return true;
        } return false;
    }

    public Page<Employee> find(String secondName, String firstName, String middleName,
                               String phone, Date start, Date end, String passport, Pageable pageable) {
        return employeeRepository.find(secondName, firstName, middleName, phone,
                start, end, passport, pageable);
    }
}
