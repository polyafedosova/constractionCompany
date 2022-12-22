package fedosova_p.constractioncompany.controller;

import fedosova_p.constractioncompany.lib.PageableLib;
import fedosova_p.constractioncompany.model.Client;
import fedosova_p.constractioncompany.model.Contract;
import fedosova_p.constractioncompany.model.Employee;
import fedosova_p.constractioncompany.model.enums.Status;
import fedosova_p.constractioncompany.model.enums.Type;
import fedosova_p.constractioncompany.service.ApartmentService;
import fedosova_p.constractioncompany.service.ClientService;
import fedosova_p.constractioncompany.service.ContractService;
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

import java.util.List;

@Controller
public class ContractController {

    private final ApartmentService apartmentService;
    private final ClientService clientService;
    private final EmployeeService employeeService;
    private final ContractService contractService;

    public ContractController(ApartmentService apartmentService, ClientService clientService, EmployeeService employeeService, ContractService contractService) {
        this.apartmentService = apartmentService;
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.contractService = contractService;
    }

    @GetMapping("clients/{client}/contracts")
    public String getClientContracts(Model model, @AuthenticationPrincipal Employee employee, @PathVariable Client client,
                                     @PageableDefault(sort = { "contract_id" }, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Contract> page = contractService.findByClient(client, pageable);
        List<Integer> body = PageableLib.getCountPage(page);
        model.addAttribute("page", page);
        model.addAttribute("body", body);
        model.addAttribute("url", "/clients/" + client.getClient_id() + "/contracts?");
        model.addAttribute("client", client);
        model.addAttribute("employee", employee);
        return "contracts";
    }

    @GetMapping("contracts")
    public String getContracts(Model model, @AuthenticationPrincipal Employee employee,
                               @PageableDefault(sort = { "contract_id" }, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Contract> page = contractService.findByEmployee(employee, pageable);
        List<Integer> body = PageableLib.getCountPage(page);
        model.addAttribute("page", page);
        model.addAttribute("body", body);
        model.addAttribute("url", "/contracts?");
        model.addAttribute("employee", employee);
        return "contracts";
    }

    @GetMapping("employees/{employee}/contracts")
    public String getEmployeeContracts(Model model, @PathVariable Employee employee,
                                       @PageableDefault(sort = { "contract_id" }, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Contract> page = contractService.findByEmployee(employee, pageable);
        List<Integer> body = PageableLib.getCountPage(page);
        model.addAttribute("page", page);
        model.addAttribute("body", body);
        model.addAttribute("url", "/employees/" + employee.getEmployee_id() + "/contracts?");
        model.addAttribute("employee", employee);
        return "contracts";
    }


    @GetMapping("contracts/{contract}/deleteContract")
    public String deleteContract(@PathVariable Contract contract, RedirectAttributes redirectAttributes) {
        if (!contractService.deleteContract(contract)) {
            redirectAttributes.addFlashAttribute("message", "Неизвестная ошибка");
        } else redirectAttributes.addFlashAttribute("message", "Контракт успешно удален");
        return "redirect:/contracts";
    }

    @GetMapping("contracts/{contract}/editContract")
    public String getEditEmployeePage(Model model, @AuthenticationPrincipal Employee employee,
                                      @PathVariable(required = false) Contract contract) {
        model.addAttribute("contract", contract);
        model.addAttribute("employee", employee);
        return "contractEdit";
    }

    @PostMapping("contracts/{contract}/editContract")
    public String editContract(Model model, @ModelAttribute Contract contract,
                               @RequestParam String statusC, @RequestParam String typeC) {
        model.addAttribute("contract", contract);
        contract.setStatus(Status.values()[Integer.parseInt(statusC)]);
        contract.setType(Type.values()[Integer.parseInt(typeC)]);
        if (!contractService.updateContract(contract)) {
            model.addAttribute("message", "Введены некорректные данные");
        } else {
            model.addAttribute("message", "Данные успешно изменены");
        } return "contractEdit";
    }

    @PostMapping("contracts/addContracts")
    public String addContracts(Model model, @ModelAttribute Contract contract,
                               @RequestParam String statusC, @RequestParam String typeC,
                               @RequestParam String apartment_id, @RequestParam String client_id,
                               @RequestParam String employee_id) {
        model.addAttribute("contract", contract);
        contract.setStatus(Status.values()[Integer.parseInt(statusC)]);
        contract.setType(Type.values()[Integer.parseInt(typeC)]);
        contract.setApartment(apartmentService.findById(Long.parseLong(apartment_id)));
        contract.setClient(clientService.findById(Long.parseLong(client_id)));
        contract.setEmployee(employeeService.findById(Long.parseLong(employee_id)));
        if (!contractService.saveContract(contract)) model.addAttribute("message", "Введены некоректные данные");
        else model.addAttribute("message", "Данные успешно сохранились");
        return "contracts";
    }
}
