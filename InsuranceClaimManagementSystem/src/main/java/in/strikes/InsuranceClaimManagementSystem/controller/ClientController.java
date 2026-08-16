package in.strikes.InsuranceClaimManagementSystem.controller;

import in.strikes.InsuranceClaimManagementSystem.entity.ApiResponse;
import in.strikes.InsuranceClaimManagementSystem.entity.Client;
import in.strikes.InsuranceClaimManagementSystem.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/saveClient/{policyId}")
    public ApiResponse<Client> saveClient(
           @Valid @RequestBody Client client,
            @PathVariable int policyId) {

        return clientService.insertClient(
                client,
                policyId);
    }

    @GetMapping("/getClientById/{clientId}")
    public ApiResponse<Client> getClientById(
            @PathVariable int clientId) {

        return clientService.getClientById(
                clientId);
    }

    @DeleteMapping("/deleteClient/{clientId}")
    public ApiResponse<Client> deleteClient(
            @PathVariable int clientId) {

        return clientService.deleteClientByID(
                clientId);
    }

    @PutMapping("/updateClient/{clientId}")
    public ApiResponse<Client> updateClient(@Valid
            @RequestBody Client client,
            @PathVariable int clientId) {

        return clientService.updateClient(
                client,
                clientId);
    }

    @GetMapping("/displayAllClient")
    public ApiResponse<List<Client>> displayAllClient() {

        return clientService.displayAllClients();
    }
}