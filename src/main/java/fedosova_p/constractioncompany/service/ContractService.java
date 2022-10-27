package fedosova_p.constractioncompany.service;

import fedosova_p.constractioncompany.repository.ContractRepository;
import org.springframework.stereotype.Service;

@Service
public class ContractService {

    private ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }
}
