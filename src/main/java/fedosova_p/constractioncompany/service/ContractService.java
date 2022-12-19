package fedosova_p.constractioncompany.service;

import fedosova_p.constractioncompany.model.Apartment;
import fedosova_p.constractioncompany.model.Client;
import fedosova_p.constractioncompany.model.Contract;
import fedosova_p.constractioncompany.model.Employee;
import fedosova_p.constractioncompany.model.enums.Status;
import fedosova_p.constractioncompany.repository.ContractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public Page<Contract> getAll(Pageable pageable) {
        return contractRepository.findAllPage(pageable);
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

    public Page<Contract> findByApartment(Apartment apartment, Pageable pageable) {
        return contractRepository.findByApartment(apartment, pageable);
    }
    public Page<Contract> findByClient(Client client, Pageable pageable) {
        return contractRepository.findByClient(client, pageable);
    }
    public Contract findOneByClient(Client client) {
        return contractRepository.findOneByClient(client);
    }
    public Page<Contract> findByEmployee(Employee employee, Pageable pageable) {
        return contractRepository.findByEmployee(employee, pageable);
    }
    public Page<Contract> findByStatus(Status status, Pageable pageable) {
        return contractRepository.findByStatus(status, pageable);
    }


}
