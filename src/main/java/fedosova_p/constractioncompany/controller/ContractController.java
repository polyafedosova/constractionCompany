package fedosova_p.constractioncompany.controller;

import fedosova_p.constractioncompany.model.Apartment;
import fedosova_p.constractioncompany.model.Building;
import fedosova_p.constractioncompany.model.Client;
import fedosova_p.constractioncompany.model.Contract;
import fedosova_p.constractioncompany.repository.ContractRepository;
import fedosova_p.constractioncompany.service.ContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;
import java.util.List;

@Controller
public class ContractController {

    private final ContractService contractService;
    private final ContractRepository contractRepository;

    public ContractController(ContractService contractService, ContractRepository contractRepository) {
        this.contractService = contractService;
        this.contractRepository = contractRepository;
    }

    @GetMapping("clients/{client}/contracts")
    public String getClientContracts(Model model, @PathVariable Client client) {
        List<Contract> clientsContractsList = new LinkedList<>(contractRepository.findByClient(client));
        model.addAttribute("contracts", clientsContractsList);
        model.addAttribute("client", client);
        return "clientContractsList";
    }

    /*@GetMapping("buildings/{building}/apartments/{apartment}/editApartment")
    public String getEditApartmentPage(Model model, @PathVariable(required = false) Apartment apartment) {
        model.addAttribute("apartment", apartment);
        return "apartmentEdit";
    }

    @PostMapping("buildings/{building}/apartments/{apartment}/editApartment")
    public String editApartment(Model model, @ModelAttribute Apartment apartment) {
        model.addAttribute("apartment", apartment);
        if (!apartmentService.updateApartment(apartment)) {
            model.addAttribute("message", "Введены некорректные данные");
        } else {
            model.addAttribute("message", "Данные успешно изменены");
        } return "apartmentEdit";
    }

    @PostMapping("buildings/{building}/apartments/addApartment")
    public String addApartment(Model model, @ModelAttribute Apartment apartment,
                               RedirectAttributes redirectAttributes, @PathVariable Building building) {
        apartment.setBuilding(building);
        List<Apartment> listApartments = new LinkedList<>(apartmentService.getAll());
        model.addAttribute("apartment", listApartments);
        if (!apartmentService.isDataCorrectly(apartment)) {
            redirectAttributes.addFlashAttribute("message", "Введены некорректные данные");
            redirectAttributes.addFlashAttribute("apartmentToAdd", apartment);
            return "redirect:/buildings/{building}/apartments";
        }
        if (!apartmentService.saveApartment(apartment)) {
            redirectAttributes.addFlashAttribute("message", "Данная квартира уже существует");
            redirectAttributes.addFlashAttribute("apartmentToAdd", apartment);
            return "redirect:/buildings/{building}/apartments";
        }
        redirectAttributes.addFlashAttribute("message", "Квартира успешно добавлена");
        return "redirect:/buildings/{building}/apartments";
    }

    @GetMapping("buildings/{building}/apartments/{apartment}/deleteApartment")
    public String deleteApartment(@PathVariable Apartment apartment, RedirectAttributes redirectAttributes) {
        if (!apartmentService.deleteApartment(apartment)) {
            redirectAttributes.addFlashAttribute("message", "Неизвестная ошибка");
        } else redirectAttributes.addFlashAttribute("message", "Квартира успешно удалена");
        return "redirect:/buildings/{building}/apartments";
    }*/
}
