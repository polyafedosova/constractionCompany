package fedosova_p.constractioncompany.controller;

import fedosova_p.constractioncompany.model.Apartment;
import fedosova_p.constractioncompany.model.Building;
import fedosova_p.constractioncompany.repository.ApartmentRepository;
import fedosova_p.constractioncompany.service.ApartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;
import java.util.List;

@Controller
public class ApartmentController {

    private ApartmentRepository apartmentRepository;
    private ApartmentService apartmentService;

    public ApartmentController(ApartmentRepository apartmentRepository, ApartmentService apartmentService) {
        this.apartmentRepository = apartmentRepository;
        this.apartmentService = apartmentService;
    }

    @GetMapping("buildings/{building}/apartments")
    public String getApartments(Model model, @PathVariable Building building) {
        List<Apartment> apartmentsList = new LinkedList<>(apartmentRepository.findByBuilding(building));
        model.addAttribute("apartments", apartmentsList);
        model.addAttribute("building", building);
        return "apartmentsList";
    }

    @GetMapping("buildings/{building}/apartments/{apartment}/editApartment")
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
    }
}