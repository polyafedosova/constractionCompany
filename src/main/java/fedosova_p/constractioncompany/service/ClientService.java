package fedosova_p.constractioncompany.service;

import fedosova_p.constractioncompany.model.Client;
import fedosova_p.constractioncompany.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<Client> getAll(Pageable pageable) {
        return clientRepository.findAllPage(pageable);
    }

    public Client findById(Long client_ID) {
        if (clientRepository.findById(client_ID).isPresent()) {
            return clientRepository.findById(client_ID).get();
        }
        return null;
    }

    public boolean updateClient(Client client) {
        if (!isDataCorrectly(client)) return false;
        clientRepository.save(client);
        return true;
    }

    public boolean isDataCorrectly(Client client) {
        //TODO
        return true;
    }

    public boolean saveClient(Client client) {
        clientRepository.save(client);
        return true;
    }

    public boolean deleteClient(Client client) {
        if (clientRepository.findById(client.getClient_id()).isPresent()) {
            clientRepository.deleteById(client.getClient_id());
            return true;
        } return false;
    }

    public Page<Client> find(String secondName, String firstName, String middleName,
                             String phone, Date start, Date end, String passport, Pageable pageable) {
        return clientRepository.find(secondName, firstName, middleName, phone,
                start, end, passport, pageable);
    }
}
