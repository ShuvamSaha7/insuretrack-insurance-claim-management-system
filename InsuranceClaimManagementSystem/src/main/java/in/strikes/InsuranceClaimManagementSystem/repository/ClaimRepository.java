package in.strikes.InsuranceClaimManagementSystem.repository;

import in.strikes.InsuranceClaimManagementSystem.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim,Integer> {
}
