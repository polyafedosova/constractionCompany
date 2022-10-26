package fedosova_p.copnstractioncompany.controller;

import fedosova_p.copnstractioncompany.model.Apartment;
import fedosova_p.copnstractioncompany.model.Building;
import fedosova_p.copnstractioncompany.repository.ApartmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/apartments")
public class ApartmentController {

    private ApartmentRepository apartmentRepository;

    public ApartmentController(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @GetMapping("{building}")
    public String getApartments(Model model, @PathVariable Building building) {
        List<Apartment> apartmentList = new LinkedList<>(apartmentRepository.findByBuilding(building));
        model.addAttribute("apartments", apartmentList);
        model.addAttribute("building", building);
        return "apartmentsList";
    }
}
