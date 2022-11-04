package fedosova_p.constractioncompany.controller;

import fedosova_p.constractioncompany.model.Building;
import fedosova_p.constractioncompany.service.ApartmentService;
import fedosova_p.constractioncompany.service.BuildingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class MainController {

    private BuildingService buildingService;
    private ApartmentService apartmentService;

    public MainController(BuildingService buildingService, ApartmentService apartmentService) {
        this.buildingService = buildingService;
        this.apartmentService = apartmentService;
    }
}
