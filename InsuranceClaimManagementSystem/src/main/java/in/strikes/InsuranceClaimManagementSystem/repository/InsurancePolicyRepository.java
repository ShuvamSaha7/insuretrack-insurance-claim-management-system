package in.strikes.InsuranceClaimManagementSystem.repository;

import in.strikes.InsuranceClaimManagementSystem.entity.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy,Integer> {

}
