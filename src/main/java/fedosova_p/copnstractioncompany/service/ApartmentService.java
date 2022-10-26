package fedosova_p.copnstractioncompany.service;

import fedosova_p.copnstractioncompany.model.Apartment;
import fedosova_p.copnstractioncompany.repository.ApartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class ApartmentService {

    private ApartmentRepository apartmentRepository;

    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public Apartment findById(Long apartments_ID) {
        if (apartmentRepository.findById(apartments_ID).isPresent()) {
            return apartmentRepository.findById(apartments_ID).get();
        }
        return null;
    }
}
