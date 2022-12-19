package fedosova_p.constractioncompany.service;

import fedosova_p.constractioncompany.model.Apartment;
import fedosova_p.constractioncompany.model.Building;
import fedosova_p.constractioncompany.model.enums.Status;
import fedosova_p.constractioncompany.repository.ApartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public Page<Apartment> getAll(Pageable pageable) {
        return apartmentRepository.findAllPage(pageable);
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

    public Page<Apartment> findByBuilding(Building building, Pageable pageable) {
        return apartmentRepository.findByBuilding(building, pageable);
    }

    public Page<Apartment> find(Building building, Status status, String number, String floor, String entrance,
                                String totalAreaStart, String totalAreaEnd, String livingAreaStart, String livingAreaEnd,
                                String numberRooms, Pageable pageable) {
        if (!number.equals("")) return apartmentRepository.findByBuildingAndNumber(building, Integer.parseInt(number), pageable);
        if (!floor.equals("")) return apartmentRepository.findByBuildingAndFloor(building, Integer.parseInt(floor), pageable);
        if (!entrance.equals("")) return apartmentRepository.findByBuildingAndEntrance(building, Integer.parseInt(entrance), pageable);
        if (!numberRooms.equals("")) return apartmentRepository.findByBuildingAndNumber_rooms(building, Integer.parseInt(numberRooms), pageable);
        if (!totalAreaStart.equals("") && !totalAreaEnd.equals(""))
            return apartmentRepository.findByTotal_areaBetween(building, Double.parseDouble(totalAreaStart), Double.parseDouble(totalAreaEnd), pageable);
        if (!livingAreaStart.equals("") && !livingAreaEnd.equals(""))
            return apartmentRepository.findByTotal_areaBetween(building, Double.parseDouble(livingAreaStart), Double.parseDouble(livingAreaEnd), pageable);
        return apartmentRepository.findByStatus(building, status, pageable);
    }
}
