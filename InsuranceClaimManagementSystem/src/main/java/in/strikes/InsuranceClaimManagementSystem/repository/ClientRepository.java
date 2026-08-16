package in.strikes.InsuranceClaimManagementSystem.repository;

import in.strikes.InsuranceClaimManagementSystem.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {

}
