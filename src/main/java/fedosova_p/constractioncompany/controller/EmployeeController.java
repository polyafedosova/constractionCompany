package fedosova_p.constractioncompany.controller;

import fedosova_p.constractioncompany.model.Employee;
import fedosova_p.constractioncompany.model.enums.Post;
import fedosova_p.constractioncompany.model.enums.Role;
import fedosova_p.constractioncompany.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("employees")
    public String getEmployee(Model model) {
        List<Employee> listEmployees = new LinkedList<>(employeeService.getAll());
        model.addAttribute("employees", listEmployees);
        return "employeesList";
    }

    //@RequestParam String post_em, @RequestParam(required = false) String isAdmin
    @GetMapping("employees/findEmployee")
    public String findEmployee(Model model, @ModelAttribute Employee employee, @RequestParam String dateStart,
                               @RequestParam String dateEnd, @RequestParam String post_em) throws ParseException {
        List<Employee> listEmployees = new LinkedList<>(employeeService.find(employee.getSecond_name(),
                employee.getFirst_name(), employee.getMiddle_name(), employee.getPhone(),
                new SimpleDateFormat("yyyy-MM-dd").parse(dateStart), new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd),
                employee.getPassport(), employee.getUsername(), (Post.values()[Integer.parseInt(post_em)])));
        model.addAttribute("employees", listEmployees);
        return "employeesList";
    }

    @GetMapping("employees/{employee}/editEmployee")
    public String getEditEmployeePage(Model model, @PathVariable(required = false) Employee employee) {
        model.addAttribute("employee", employee);
        return "employeeEdit";
    }

    @PostMapping("employees/{employee}/editEmployee")
    public String editEmployee(Model model, @ModelAttribute Employee employee,
                               @RequestParam String post_em, @RequestParam(required = false) String isAdmin) {
        model.addAttribute("employee", employee);
        employee.setPost(Post.values()[Integer.parseInt(post_em)]);
        if (isAdmin != null) employee.addRole(Role.ADMIN);
        if (!employeeService.updateEmployee(employee)) {
            model.addAttribute("message", "Введены некорректные данные");
        } else {
            model.addAttribute("message", "Данные успешно изменены");
        } return "employeeEdit";
    }

    @GetMapping("employees/{employee}/deleteEmployee")
    public String deleteEmployee(@PathVariable Employee employee, RedirectAttributes redirectAttributes) {
        if (!employeeService.deleteEmployee(employee)) {
            redirectAttributes.addFlashAttribute("message", "Неизвестная ошибка");
        } else redirectAttributes.addFlashAttribute("message", "Сотрудник успешно удален");
        return "redirect:/employees";
    }

    @GetMapping("employees/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("employees/registration")
    public String addEmployee(Model model, @ModelAttribute Employee employee,
                              @RequestParam String post_em, @RequestParam(required = false) String isAdmin,
                              RedirectAttributes redirectAttributes) {
        model.addAttribute("employee", employee);
        employee.setPost(Post.values()[Integer.parseInt(post_em)]);
        if (isAdmin != null) employee.addRole(Role.ADMIN);
        if (!employeeService.isDataCorrectly(employee)) {
            redirectAttributes.addFlashAttribute("message", "Введены некорректные данные");
            redirectAttributes.addFlashAttribute("employeeToAdd", employee);
            return "redirect:/employees";
        }
        if (!employeeService.saveEmployee(employee)) {
            redirectAttributes.addFlashAttribute("message", "Данный сотрудник уже существует");
            redirectAttributes.addFlashAttribute("employeeToAdd", employee);
            return "redirect:/employees";
        }
        redirectAttributes.addFlashAttribute("message", "Сотрудник успешно добавлен");
        return "redirect:/employees";
    }
}