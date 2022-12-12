package fedosova_p.constractioncompany.service;

import fedosova_p.constractioncompany.model.*;
import fedosova_p.constractioncompany.model.enums.Status;
import fedosova_p.constractioncompany.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public List<Contract> getAll() {
        return contractRepository.findAll();
    }
    public Contract findById(Long contract_ID) {
        if (contractRepository.findById(contract_ID).isPresent()) {
            return contractRepository.findById(contract_ID).get();
        }
        return null;
    }

    public boolean updateContract(Contract contract) {
        if (!isDataCorrectly(contract)) return false;
        contractRepository.save(contract);
        return true;
    }

    public boolean isDataCorrectly(Contract contract) {
        //TODO
        return true;
    }

    public boolean saveContract(Contract contract) {
        contractRepository.save(contract);
        return true;
    }

    public boolean deleteContract(Contract contract) {
        if (contractRepository.findById(contract.getContract_id()).isPresent()) {
            contractRepository.deleteById(contract.getContract_id());
            return true;
        } return false;
    }

    public List<Contract> getDoneContracts() {
        return contractRepository.findByStatus(Status.done);
    }

    public List<Apartment> getApartments(Building building) {
        List<Apartment> apartments = new LinkedList<>();
        for (Contract contract : getDoneContracts()) {
            if (Objects.equals(building.getBuilding_id(), contract.getApartment().getBuilding().getBuilding_id())) {
                apartments.add(contract.getApartment());
            }
        }
        return apartments;
    }

    public List<Contract> findByApartment(Apartment apartment) {
        return contractRepository.findByApartment(apartment);
    }
    public List<Contract> findByClient(Client client) {
        return contractRepository.findByClient(client);
    }
    public Contract findOneByClient(Client client) {
        return contractRepository.findOneByClient(client);
    }
    public List<Contract> findByEmployee(Employee employee) {
        return contractRepository.findByEmployee(employee);
    }
    public List<Contract> findByStatus(Status status) {
        return contractRepository.findByStatus(status);
    }
}
