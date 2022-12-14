package fedosova_p.constractioncompany.controller;

import fedosova_p.constractioncompany.lib.PageableLib;
import fedosova_p.constractioncompany.model.Employee;
import fedosova_p.constractioncompany.model.enums.Post;
import fedosova_p.constractioncompany.model.enums.Role;
import fedosova_p.constractioncompany.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("employees")
    public String getEmployee(Model model, @AuthenticationPrincipal Employee employee,
                              @PageableDefault(sort = { "employee_id" }, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Employee> page = employeeService.getAll(pageable);
        List<Integer> body = PageableLib.getCountPage(page);
        model.addAttribute("page", page);
        model.addAttribute("body", body);
        model.addAttribute("url", "/employees?");
        model.addAttribute("employee", employee);
        return "employeesList";
    }

    @GetMapping("employees/findEmployee")
    public String findEmployee(Model model, @ModelAttribute Employee employee, @RequestParam String dateStart,
                               @RequestParam String dateEnd,
                               @PageableDefault(sort = { "employee_id" }, direction = Sort.Direction.DESC) Pageable pageable) throws ParseException {
        Page<Employee> page = employeeService.find(employee.getSecond_name(),
                employee.getFirst_name(), employee.getMiddle_name(), employee.getPhone(),
                new SimpleDateFormat("yyyy-MM-dd").parse(dateStart), new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd),
                employee.getPassport(), pageable);
        List<Integer> body = PageableLib.getCountPage(page);
        model.addAttribute("page", page);
        model.addAttribute("body", body);
        model.addAttribute("url", "/employees/findEmployee?second_name=" +
                employee.getSecond_name() + "&first_name=" +
                employee.getFirst_name() + "&middle_name=" +
                employee.getMiddle_name() + "&phone=" +
                employee.getPhone() + "&dateStart=" + dateStart +
                "&dateEnd=" + dateEnd + "&passport=" + employee.getPassport() + "&");
        model.addAttribute("employeeToFind", employee);
        model.addAttribute("dateStart", dateStart);
        model.addAttribute("dateEnd", dateEnd);
        return "employeesList";
    }

    @GetMapping("employees/{employee}/editEmployee")
    public String getEditEmployeePage(Model model, @AuthenticationPrincipal Employee employeeIn,
                                      @PathVariable(required = false) Employee employee) {
        model.addAttribute("employee", employee);
        model.addAttribute("employeeIn", employeeIn);
        return "employeeEdit";
    }

    @PostMapping("employees/{employee}/editEmployee")
    public String editEmployee(Model model, @ModelAttribute Employee employee,
                               @RequestParam String post_em, @RequestParam(required = false) String isAdmin) {
        model.addAttribute("employee", employee);
        employee.setPost(Post.values()[Integer.parseInt(post_em)]);
        if (isAdmin != null) employee.addRole(Role.ADMIN);
        if (!employeeService.updateEmployee(employee)) {
            model.addAttribute("message", "?????????????? ???????????????????????? ????????????");
        } else {
            model.addAttribute("message", "???????????? ?????????????? ????????????????");
        } return "employeeEdit";
    }

    @GetMapping("employees/{employee}/deleteEmployee")
    public String deleteEmployee(@PathVariable Employee employee, RedirectAttributes redirectAttributes) {
        if (!employeeService.deleteEmployee(employee)) {
            redirectAttributes.addFlashAttribute("message", "?????????????????????? ????????????");
        } else redirectAttributes.addFlashAttribute("message", "?????????????????? ?????????????? ????????????");
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
            redirectAttributes.addFlashAttribute("message", "?????????????? ???????????????????????? ????????????");
            redirectAttributes.addFlashAttribute("employeeToAdd", employee);
            return "redirect:/employees";
        }
        if (!employeeService.saveEmployee(employee)) {
            redirectAttributes.addFlashAttribute("message", "???????????? ?????????????????? ?????? ????????????????????");
            redirectAttributes.addFlashAttribute("employeeToAdd", employee);
            return "redirect:/employees";
        }
        redirectAttributes.addFlashAttribute("message", "?????????????????? ?????????????? ????????????????");
        return "redirect:/employees";
    }
}