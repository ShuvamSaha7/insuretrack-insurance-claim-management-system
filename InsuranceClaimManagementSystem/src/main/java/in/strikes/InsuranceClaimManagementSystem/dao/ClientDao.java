package in.strikes.InsuranceClaimManagementSystem.dao;

import in.strikes.InsuranceClaimManagementSystem.entity.Client;
import in.strikes.InsuranceClaimManagementSystem.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientDao {

    @Autowired
    private ClientRepository clientRepository;

    public Client insertClient(Client client) {

        return clientRepository.save(client);
    }

    public Client getByClientId(int clientId) {

        Optional<Client> optional = clientRepository.findById(clientId);

        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    public Client deleteClient(int clientId) {

        Optional<Client> optional = clientRepository.findById(clientId);

        if (optional.isPresent()) {

            clientRepository.deleteById(clientId);

            return optional.get();
        }

        return null;
    }

    public Client updateClient(Client client) {

        return clientRepository.save(client);
    }

    public List<Client> displayAllClient() {

        return clientRepository.findAll();
    }
}