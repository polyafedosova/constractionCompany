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

    @GetMapping("apartments/{building}")
    public String getApartments(Model model, @PathVariable Building building) {
        List<Apartment> apartmentList = new LinkedList<>(apartmentRepository.findByBuilding(building));
        model.addAttribute("apartments", apartmentList);
        model.addAttribute("building", building);
        return "apartmentsList";
    }

    @GetMapping("editApartment/{apartment}")
    public String getEditApartmentPage(Model model, @PathVariable(required = false) Apartment apartment) {
        model.addAttribute("apartment", apartment);
        return "apartmentEdit";
    }

    @PostMapping("editApartment/{apartment}")
    public String editApartment(Model model, @ModelAttribute Apartment apartment) {
        model.addAttribute("apartment", apartment);
        if (!apartmentService.updateApartment(apartment)) {
            model.addAttribute("message", "Введены некорректные данные");
        } else {
            model.addAttribute("message", "Данные успешно изменены");
        } return "apartmentEdit";
    }

    @PostMapping("building/{building}/addApartment")
    public String addApartment(Model model, @ModelAttribute Apartment apartment,
                              RedirectAttributes redirectAttributes, @PathVariable Building building) {
        apartment.setBuilding(building);
        List<Apartment> listApartments = new LinkedList<>(apartmentService.getAll());
        model.addAttribute("apartment", listApartments);
        if (!apartmentService.isDataCorrectly(apartment)) {
            redirectAttributes.addFlashAttribute("message", "Введены некорректные данные");
            redirectAttributes.addFlashAttribute("apartmentToAdd", apartment);
            return "redirect:/apartments/{building}";
        }
        if (!apartmentService.saveApartment(apartment)) {
            redirectAttributes.addFlashAttribute("message", "Данная квартира уже существует");
            redirectAttributes.addFlashAttribute("apartmentToAdd", apartment);
            return "redirect:/apartments/{building}";
        }
        redirectAttributes.addFlashAttribute("message", "Квартира успешно добавлена");
        return "redirect:/apartments/{building}";
    }

    @GetMapping("deleteApartment/{apartment}")
    public String deleteApartment(@PathVariable Apartment apartment, RedirectAttributes redirectAttributes) {
        if (!apartmentService.deleteApartment(apartment)) {
            redirectAttributes.addFlashAttribute("message", "Неизвестная ошибка");
        } else redirectAttributes.addFlashAttribute("message", "Квартира успешно удалена");
        return "redirect:/apartments/{building}";
    }
}