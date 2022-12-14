package fedosova_p.constractioncompany.service;

import fedosova_p.constractioncompany.model.Building;
import fedosova_p.constractioncompany.repository.BuildingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;

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

    /*public List<Building> find(String city, String street, String number, String name,
                               Date consStart, Date consEnd, Date expStart, Date expEnd, int b) {
        return buildingRepository.find(city, street, number, name, consStart, consEnd, expStart, expEnd, b);
    };*/
}
