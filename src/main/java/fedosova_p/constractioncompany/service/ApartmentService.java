package fedosova_p.constractioncompany.service;

import fedosova_p.constractioncompany.model.Apartment;
import fedosova_p.constractioncompany.model.Building;
import fedosova_p.constractioncompany.model.enums.Status;
import fedosova_p.constractioncompany.repository.ApartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public List<Apartment> getAll() {
        return apartmentRepository.findAll();
    }
    public Apartment findById(Long apartments_ID) {
        if (apartmentRepository.findById(apartments_ID).isPresent()) {
            return apartmentRepository.findById(apartments_ID).get();
        }
        return null;
    }

    public boolean updateApartment(Apartment apartment) {
        if (!isDataCorrectly(apartment)) return false;
        apartmentRepository.save(apartment);
        return true;
    }

    public boolean isDataCorrectly(Apartment apartment) {
        //TODO
        return true;
    }

    public boolean saveApartment(Apartment apartment) {
        apartmentRepository.save(apartment);
        return true;
    }

    public boolean deleteApartment(Apartment apartment) {
        if (apartmentRepository.findById(apartment.getApartment_id()).isPresent()) {
            apartmentRepository.deleteById(apartment.getApartment_id());
            return true;
        } return false;
    }

    public List<Apartment> findByBuilding(Building building) {
        return apartmentRepository.findByBuilding(building);
    }

    public List<Apartment> find(Building building, Status status, String number, String floor, String entrance,
                                String totalAreaStart, String totalAreaEnd, String livingAreaStart, String livingAreaEnd,
                                String numberRooms) {
        if (!number.equals("")) return apartmentRepository.findByBuildingAndNumber(building, Integer.parseInt(number));
        if (!floor.equals("")) return apartmentRepository.findByBuildingAndFloor(building, Integer.parseInt(floor));
        if (!entrance.equals("")) return apartmentRepository.findByBuildingAndEntrance(building, Integer.parseInt(entrance));
        if (!numberRooms.equals("")) return apartmentRepository.findByBuildingAndNumber_rooms(building, Integer.parseInt(numberRooms));
        if (!totalAreaStart.equals("") && !totalAreaEnd.equals(""))
            return apartmentRepository.findByTotal_areaBetween(building, Double.parseDouble(totalAreaStart), Double.parseDouble(totalAreaEnd));
        if (!livingAreaStart.equals("") && !livingAreaEnd.equals(""))
            return apartmentRepository.findByTotal_areaBetween(building, Double.parseDouble(livingAreaStart), Double.parseDouble(livingAreaEnd));
        return apartmentRepository.findByStatus(building, status);
    }
}
