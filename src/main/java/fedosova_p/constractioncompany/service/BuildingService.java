package fedosova_p.constractioncompany.service;

import com.sun.xml.bind.v2.TODO;
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

    public boolean updateBuilding(Building building) {
        if (!isDataCorrectly(building)) return false;
        buildingRepository.save(building);
        return true;
    }

    private boolean isDataCorrectly(Building building) {
        //TODO
        return true;
    }
}
