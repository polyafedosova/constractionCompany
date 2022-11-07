package fedosova_p.constractioncompany.controller;

import fedosova_p.constractioncompany.model.Employee;
import fedosova_p.constractioncompany.service.ApartmentService;
import fedosova_p.constractioncompany.service.BuildingService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private BuildingService buildingService;
    private ApartmentService apartmentService;

    public MainController(BuildingService buildingService, ApartmentService apartmentService) {
        this.buildingService = buildingService;
        this.apartmentService = apartmentService;
    }

    @GetMapping("/")
    public String getIndexPage(Model model, @AuthenticationPrincipal Employee employee) {
        model.addAttribute("employee", employee);
        return "index";
    }
}
