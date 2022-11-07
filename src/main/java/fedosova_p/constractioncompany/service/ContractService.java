package fedosova_p.constractioncompany.service;

import fedosova_p.constractioncompany.model.Apartment;
import fedosova_p.constractioncompany.model.Contract;
import fedosova_p.constractioncompany.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    private ContractRepository contractRepository;

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
}
