package fedosova_p.constractioncompany.controller;

import fedosova_p.constractioncompany.model.Building;
import fedosova_p.constractioncompany.service.BuildingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;
import java.util.List;

@Controller
public class BuildingController {

    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("building")
    public String getBuildings(Model model) {
        List<Building> listBuildings = new LinkedList<>(buildingService.getAll());
        model.addAttribute("buildings", listBuildings);
        return "buildingsList";
    }

    @GetMapping("editBuilding/{building}")
    public String getEditBuildingPage(Model model, @PathVariable(required = false) Building building) {
        model.addAttribute("building", building);
        return "buildingEdit";
    }

    @PostMapping("editBuilding/{building}")
    public String editBuilding(Model model, @ModelAttribute Building building) {
        model.addAttribute("building", building);
        if (!buildingService.updateBuilding(building)) {
            model.addAttribute("message", "Введены некорректные данные");
        } else {
            model.addAttribute("message", "Данные успешно изменены");
        } return "buildingEdit";
    }

    @PostMapping("building/addBuilding")
    public String addBuilding(Model model, @ModelAttribute Building building,
                              RedirectAttributes redirectAttributes) {
        List<Building> listBuildings = new LinkedList<>(buildingService.getAll());
        model.addAttribute("buildings", listBuildings);
        if (!buildingService.isDataCorrectly(building)) {
            redirectAttributes.addFlashAttribute("message", "Введены некорректные данные");
            redirectAttributes.addFlashAttribute("buildingToAdd", building);
            return "redirect:/building";
        }
        if (!buildingService.saveBuilding(building)) {
            redirectAttributes.addFlashAttribute("message", "Данное строение уже существует");
            redirectAttributes.addFlashAttribute("buildingToAdd", building);
            return "redirect:/building";
        }
        redirectAttributes.addFlashAttribute("message", "Строение успешно добавлено");
        return "redirect:/building";
    }

    @GetMapping("deleteBuilding/{building}")
    public String deleteBuilding(@PathVariable Building building, RedirectAttributes redirectAttributes) {
        if (!buildingService.deleteBuilding(building)) {
            redirectAttributes.addFlashAttribute("message", "Неизвестная ошибка");
        } else redirectAttributes.addFlashAttribute("message", "Строение успешно удалено");
        return "redirect:/building";
    }
}