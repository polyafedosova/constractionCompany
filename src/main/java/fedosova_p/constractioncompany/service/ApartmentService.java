package fedosova_p.constractioncompany.service;

import fedosova_p.constractioncompany.model.Apartment;
import fedosova_p.constractioncompany.model.Building;
import fedosova_p.constractioncompany.repository.ApartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentService {

    private ApartmentRepository apartmentRepository;

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
}
