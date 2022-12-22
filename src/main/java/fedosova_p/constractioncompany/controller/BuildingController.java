package fedosova_p.constractioncompany.controller;

import fedosova_p.constractioncompany.model.Building;
import fedosova_p.constractioncompany.model.Employee;
import fedosova_p.constractioncompany.service.BuildingService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Controller
public class BuildingController {

    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("buildings")
    public String getBuildings(Model model, @AuthenticationPrincipal Employee employee) {
        List<Building> listBuildings = new LinkedList<>(buildingService.getAll());
        model.addAttribute("buildings", listBuildings);
        model.addAttribute("employee", employee);
        return "buildingsList";
    }

    @GetMapping("buildings/findBuilding")
    public String findBuilding(Model model, @ModelAttribute Building building, @RequestParam String dateStart,
                               @RequestParam String dateEnd, @RequestParam(required = false) String isCommission) throws ParseException {
        if (isCommission == null) {
            List<Building> listBuildings = new LinkedList<>(buildingService.find(building.getCity(),
                    building.getStreet(), building.getNumber(), building.getName(),
                    new SimpleDateFormat("yyyy-MM-dd").parse(dateStart), new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd),
                    true));
            model.addAttribute("buildings", listBuildings);
            return "buildingsList";
        }
        List<Building> listBuildings = new LinkedList<>(buildingService.find(building.getCity(),
                building.getStreet(), building.getNumber(), building.getName(),
                new SimpleDateFormat("yyyy-MM-dd").parse(dateStart), new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd),
                false));

        model.addAttribute("buildings", listBuildings);
        return "buildingsList";
    }

    @GetMapping("buildings/{building}/editBuilding")
    public String getEditBuildingPage(Model model, @AuthenticationPrincipal Employee employee,
                                      @PathVariable(required = false) Building building) {
        model.addAttribute("building", building);
        model.addAttribute("employee", employee);
        return "buildingEdit";
    }

    @PostMapping("buildings/{building}/editBuilding")
    public String editBuilding(Model model, @ModelAttribute Building building) {
        model.addAttribute("building", building);
        if (!buildingService.updateBuilding(building)) {
            model.addAttribute("message", "Введены некорректные данные");
        } else {
            model.addAttribute("message", "Данные успешно изменены");
        } return "buildingEdit";
    }

    @PostMapping("buildings/addBuilding")
    public String addBuilding(Model model, @ModelAttribute Building building,
                              RedirectAttributes redirectAttributes) {
        List<Building> listBuildings = new LinkedList<>(buildingService.getAll());
        model.addAttribute("buildings", listBuildings);
        if (!buildingService.isDataCorrectly(building)) {
            redirectAttributes.addFlashAttribute("message", "Введены некорректные данные");
            redirectAttributes.addFlashAttribute("buildingToAdd", building);
            return "redirect:/buildings";
        }
        if (!buildingService.saveBuilding(building)) {
            redirectAttributes.addFlashAttribute("message", "Данное строение уже существует");
            redirectAttributes.addFlashAttribute("buildingToAdd", building);
            return "redirect:/buildings";
        }
        redirectAttributes.addFlashAttribute("message", "Строение успешно добавлено");
        return "redirect:/buildings";
    }

    @GetMapping("buildings/{building}/deleteBuilding")
    public String deleteBuilding(@PathVariable Building building, RedirectAttributes redirectAttributes) {
        if (!buildingService.deleteBuilding(building)) {
            redirectAttributes.addFlashAttribute("message", "Неизвестная ошибка");
        } else redirectAttributes.addFlashAttribute("message", "Строение успешно удалено");
        return "redirect:/buildings";
    }
}