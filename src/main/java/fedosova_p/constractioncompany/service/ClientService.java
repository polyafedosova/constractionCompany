package fedosova_p.constractioncompany.service;

import fedosova_p.constractioncompany.model.Client;
import fedosova_p.constractioncompany.model.Employee;
import fedosova_p.constractioncompany.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
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

    public List<Client> find(String secondName, String firstName, String middleName,
                                          String phone, Date start, Date end, String passport) {
        return clientRepository.find(secondName, firstName, middleName, phone,
                start, end, passport);
    }
}
