package fedosova_p.constractioncompany.service;

import com.sun.xml.bind.v2.TODO;
import fedosova_p.constractioncompany.model.Apartment;
import fedosova_p.constractioncompany.model.Building;
import fedosova_p.constractioncompany.repository.BuildingRepository;
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

    public Building findById(Long building_ID) {
        if (buildingRepository.findById(building_ID).isPresent()) {
            return buildingRepository.findById(building_ID).get();
        }
        return null;
    }

    public boolean updateBuilding(Building building) {
        if (!isDataCorrectly(building)) return false;
        buildingRepository.save(building);
        return true;
    }

    public boolean isDataCorrectly(Building building) {
        //TODO
        return true;
    }

    public boolean saveBuilding(Building building) {
        buildingRepository.save(building);
        return true;
    }

    public boolean deleteBuilding(Building building) {
        if (buildingRepository.findById(building.getBuilding_id()).isPresent()) {
            buildingRepository.deleteById(building.getBuilding_id());
            return true;
        } return false;
    }
}
