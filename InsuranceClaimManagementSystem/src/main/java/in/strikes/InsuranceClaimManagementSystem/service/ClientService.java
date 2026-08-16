package in.strikes.InsuranceClaimManagementSystem.service;

import in.strikes.InsuranceClaimManagementSystem.dao.ClientDao;
import in.strikes.InsuranceClaimManagementSystem.dao.InsurancePolicyDao;
import in.strikes.InsuranceClaimManagementSystem.entity.ApiResponse;
import in.strikes.InsuranceClaimManagementSystem.entity.Client;
import in.strikes.InsuranceClaimManagementSystem.entity.InsurancePolicy;
import in.strikes.InsuranceClaimManagementSystem.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private InsurancePolicyDao insurancePolicyDao;

    @Autowired
    private ClientDao clientDao;

    public ApiResponse<Client> insertClient(
            Client client,
            int policyId) {

        if (client == null) {
            throw new IllegalArgumentException(
                    "Client details cannot be null!"
            );
        }

        InsurancePolicy insurancePolicy =
                insurancePolicyDao.getByInsurancePolicyId(policyId);

        if (insurancePolicy == null) {
            throw new ResourceNotFoundException(
                    "Insurance policy not found with ID: " + policyId
            );
        }

        client.setInsurancePolicy(insurancePolicy);

        Client savedClient =
                clientDao.insertClient(client);

        ApiResponse<Client> response =
                new ApiResponse<>();

        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage(
                "Client added successfully!");
        response.setData(savedClient);

        return response;
    }

    public ApiResponse<Client> getClientById(
            int clientId) {

        Client client =
                clientDao.getByClientId(clientId);

        if (client == null) {
            throw new ResourceNotFoundException(
                    "Client not found with ID: " + clientId
            );
        }

        ApiResponse<Client> response =
                new ApiResponse<>();

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage(
                "Client details found!");
        response.setData(client);

        return response;
    }

    public ApiResponse<Client> deleteClientByID(
            int clientId) {

        Client client =
                clientDao.deleteClient(clientId);

        if (client == null) {
            throw new ResourceNotFoundException(
                    "Client not found with ID: " + clientId
            );
        }

        ApiResponse<Client> response =
                new ApiResponse<>();

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage(
                "Client deleted successfully!");
        response.setData(client);

        return response;
    }

    public ApiResponse<Client> updateClient(
            Client client,
            int clientId) {

        if (client == null) {
            throw new IllegalArgumentException(
                    "Client details cannot be null!"
            );
        }

        Client existingClient =
                clientDao.getByClientId(clientId);

        if (existingClient == null) {
            throw new ResourceNotFoundException(
                    "Client not found with ID: " + clientId
            );
        }

        existingClient.setClientName(
                client.getClientName());

        existingClient.setClientDateOfBirth(
                client.getClientDateOfBirth());

        existingClient.setClientContactInformation(
                client.getClientContactInformation());

        existingClient.setClientAddress(
                client.getClientAddress());

        Client updatedClient =
                clientDao.updateClient(existingClient);

        ApiResponse<Client> response =
                new ApiResponse<>();

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage(
                "Client updated successfully!");
        response.setData(updatedClient);

        return response;
    }

    public ApiResponse<List<Client>> displayAllClients() {

        List<Client> clients =
                clientDao.displayAllClient();

        ApiResponse<List<Client>> response =
                new ApiResponse<>();

        response.setStatusCode(HttpStatus.OK.value());

        if (clients.isEmpty()) {
            response.setMessage("No clients found!");
        } else {
            response.setMessage("Clients available!");
        }

        response.setData(clients);

        return response;
    }
}