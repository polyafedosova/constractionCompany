package fedosova_p.constractioncompany.controller;

import fedosova_p.constractioncompany.model.Employee;
import fedosova_p.constractioncompany.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String addEmployee(Model model, Employee employee) {
        model.addAttribute("employee", employee);
        return "registration";
    }

}
