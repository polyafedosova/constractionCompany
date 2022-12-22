package fedosova_p.constractioncompany.controller;

import fedosova_p.constractioncompany.lib.PageableLib;
import fedosova_p.constractioncompany.model.Apartment;
import fedosova_p.constractioncompany.model.Building;
import fedosova_p.constractioncompany.model.Employee;
import fedosova_p.constractioncompany.model.enums.Status;
import fedosova_p.constractioncompany.service.ApartmentService;
import fedosova_p.constractioncompany.service.ContractService;
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
public class ApartmentController {

    private final ApartmentService apartmentService;
    private final ContractService contractService;

    public ApartmentController(ApartmentService apartmentService, ContractService contractService) {
        this.apartmentService = apartmentService;
        this.contractService = contractService;
    }

    @GetMapping("buildings/{building}/apartments")
    public String getApartments(Model model, @PathVariable Building building, @AuthenticationPrincipal Employee employee,
                                @PageableDefault(sort = { "apartment_id" }, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Apartment> page = apartmentService.findByBuilding(building, pageable);
        List<Integer> body = PageableLib.getCountPage(page);
        model.addAttribute("building", building);
        model.addAttribute("employee", employee);
        model.addAttribute("page", page);
        model.addAttribute("body", body);
        model.addAttribute("statuses", Status.values());

        model.addAttribute("url", "/buildings/" + building.getBuilding_id() + "/apartments?");
        return "apartmentsList";
    }

    @GetMapping("buildings/{building}/apartments/findApartment")
    public String findApartments(Model model, @PathVariable Building building,
                                 @RequestParam String number, @RequestParam String floor, @RequestParam String entrance,
                                 @RequestParam String status, @RequestParam String totalAreaStart,
                                 @RequestParam String totalAreaEnd, @RequestParam String livingAreaStart,
                                 @RequestParam String livingAreaEnd, @RequestParam String numberRooms,
                                 @PageableDefault(sort = { "apartment_id" }, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Apartment> page = apartmentService.find(building,
                Status.values()[Integer.parseInt(status)], number, floor, entrance, totalAreaStart, totalAreaEnd,
                livingAreaStart, livingAreaEnd, numberRooms, pageable);
        List<Integer> body = PageableLib.getCountPage(page);
        model.addAttribute("building", building);
        model.addAttribute("page", page);
        model.addAttribute("body", body);
        model.addAttribute("url", "/buildings/" + building.getBuilding_id() + "/apartments/findApartment?number=" +
                number + "&floor=" +
                floor + "&entrance=" +
                entrance + "&totalAreaStart=" +
                totalAreaStart + "&totalAreaEnd=" +
                totalAreaEnd + "&livingAreaStart=" +
                livingAreaStart + "&livingAreaEnd=" +
                livingAreaEnd + "&numberRooms=" + numberRooms + "&status=" + status + "&");
        model.addAttribute("number", number);
        model.addAttribute("floor", floor);
        model.addAttribute("entrance", entrance);
        model.addAttribute("totalAreaStart", totalAreaStart);
        model.addAttribute("totalAreaEnd", totalAreaEnd);
        model.addAttribute("livingAreaStart", livingAreaStart);
        model.addAttribute("livingAreaEnd", livingAreaEnd);
        model.addAttribute("numberRooms", numberRooms);
        model.addAttribute("currentStatus", Status.values()[Integer.parseInt(status)]);
        model.addAttribute("statuses", Status.values());
        return "apartmentsList";
    }

    @GetMapping("buildings/{building}/apartments/{apartment}/editApartment")
    public String getEditApartmentPage(Model model, @AuthenticationPrincipal Employee employee,
                                       @PathVariable(required = false) Apartment apartment) {
        model.addAttribute("apartment", apartment);
        model.addAttribute("employee", employee);
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