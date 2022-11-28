package fedosova_p.constractioncompany.controller;

import fedosova_p.constractioncompany.model.Employee;
import fedosova_p.constractioncompany.model.enums.Role;
import fedosova_p.constractioncompany.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class RegistrationController {

    private final EmployeeService employeeService;

    public RegistrationController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addEmployee(Model model, @ModelAttribute Employee employee, @RequestParam String employeeRole) {
        model.addAttribute("employee", employee);
        employee.addRole(Role.values()[Integer.parseInt(employeeRole)]);
        return "registration";
    }

}