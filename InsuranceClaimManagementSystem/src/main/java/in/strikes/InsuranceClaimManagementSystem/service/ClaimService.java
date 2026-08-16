package in.strikes.InsuranceClaimManagementSystem.service;

import in.strikes.InsuranceClaimManagementSystem.dao.ClaimDao;
import in.strikes.InsuranceClaimManagementSystem.dao.InsurancePolicyDao;
import in.strikes.InsuranceClaimManagementSystem.entity.ApiResponse;
import in.strikes.InsuranceClaimManagementSystem.entity.Claim;
import in.strikes.InsuranceClaimManagementSystem.entity.InsurancePolicy;
import in.strikes.InsuranceClaimManagementSystem.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {

    @Autowired
    private InsurancePolicyDao insurancePolicyDao;

    @Autowired
    private ClaimDao claimDao;

    public ApiResponse<Claim> insertClaim(Claim claim, int policyId) {

        if (claim == null) {
            throw new IllegalArgumentException(
                    "Claim details cannot be null!"
            );
        }

        InsurancePolicy insurancePolicy =
                insurancePolicyDao.getByInsurancePolicyId(policyId);

        if (insurancePolicy == null) {
            throw new ResourceNotFoundException(
                    "Insurance policy not found with ID: " + policyId
            );
        }

        claim.setInsurancePolicy(insurancePolicy);

        Claim savedClaim = claimDao.insertClaim(claim);

        ApiResponse<Claim> response = new ApiResponse<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Claim created successfully!");
        response.setData(savedClaim);

        return response;
    }

    public ApiResponse<Claim> getClaimById(int claimId) {

        Claim claim = claimDao.getByClaimId(claimId);

        if (claim == null) {
            throw new ResourceNotFoundException(
                    "Claim not found with ID: " + claimId
            );
        }

        ApiResponse<Claim> response = new ApiResponse<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Claim found successfully!");
        response.setData(claim);

        return response;
    }

    public ApiResponse<Claim> deleteClaimByID(int claimId) {

        Claim claim = claimDao.deleteClaim(claimId);

        if (claim == null) {
            throw new ResourceNotFoundException(
                    "Claim not found with ID: " + claimId
            );
        }

        ApiResponse<Claim> response = new ApiResponse<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Claim deleted successfully!");
        response.setData(claim);

        return response;
    }

    public ApiResponse<Claim> updateClaim(
            Claim claim,
            int claimId) {

        if (claim == null) {
            throw new IllegalArgumentException(
                    "Claim details cannot be null!"
            );
        }

        Claim existingClaim = claimDao.getByClaimId(claimId);

        if (existingClaim == null) {
            throw new ResourceNotFoundException(
                    "Claim not found with ID: " + claimId
            );
        }

        existingClaim.setClaimNumber(
                claim.getClaimNumber());

        existingClaim.setClaimDate(
                claim.getClaimDate());

        existingClaim.setClaimDescription(
                claim.getClaimDescription());

        existingClaim.setClaimStatus(
                claim.getClaimStatus());

        existingClaim.setClaimAmount(
                claim.getClaimAmount());

        Claim updatedClaim =
                claimDao.updateClaim(existingClaim);

        ApiResponse<Claim> response = new ApiResponse<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Claim updated successfully!");
        response.setData(updatedClaim);

        return response;
    }

    public ApiResponse<List<Claim>> displayAllClaims() {

        List<Claim> claims = claimDao.displayAllClaim();

        ApiResponse<List<Claim>> response = new ApiResponse<>();
        response.setStatusCode(HttpStatus.OK.value());

        if (claims.isEmpty()) {
            response.setMessage("No claims found!");
        } else {
            response.setMessage("Claims available!");
        }

        response.setData(claims);

        return response;
    }
}