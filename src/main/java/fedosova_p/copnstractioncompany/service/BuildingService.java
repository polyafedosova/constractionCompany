package fedosova_p.copnstractioncompany.service;

import fedosova_p.copnstractioncompany.model.Building;
import fedosova_p.copnstractioncompany.repository.BuildingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {

    private BuildingRepository buildingRepository;

    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public List<Building> getAll() {
        return buildingRepository.findAll();
    }
}
