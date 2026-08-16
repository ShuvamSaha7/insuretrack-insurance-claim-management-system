package in.strikes.InsuranceClaimManagementSystem.service;

import in.strikes.InsuranceClaimManagementSystem.dao.InsurancePolicyDao;
import in.strikes.InsuranceClaimManagementSystem.entity.ApiResponse;
import in.strikes.InsuranceClaimManagementSystem.entity.InsurancePolicy;
import in.strikes.InsuranceClaimManagementSystem.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsurancePolicyService {

    @Autowired
    private InsurancePolicyDao insurancePolicyDao;

    public ApiResponse<InsurancePolicy> insertInsurancePolicy(
            InsurancePolicy insurancePolicy) {

        if (insurancePolicy == null) {
            throw new IllegalArgumentException(
                    "Insurance policy details cannot be null!"
            );
        }

        InsurancePolicy savedPolicy =
                insurancePolicyDao.insertInsurancePolicy(insurancePolicy);

        ApiResponse<InsurancePolicy> response =
                new ApiResponse<>();

        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage(
                "Insurance policy added successfully!");
        response.setData(savedPolicy);

        return response;
    }

    public ApiResponse<InsurancePolicy> getInsurancePolicyById(
            int insurancePolicyId) {

        InsurancePolicy insurancePolicy =
                insurancePolicyDao.getByInsurancePolicyId(
                        insurancePolicyId);

        if (insurancePolicy == null) {
            throw new ResourceNotFoundException(
                    "Insurance policy not found with ID: "
                            + insurancePolicyId
            );
        }

        ApiResponse<InsurancePolicy> response =
                new ApiResponse<>();

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage(
                "Insurance policy details found!");
        response.setData(insurancePolicy);

        return response;
    }

    public ApiResponse<InsurancePolicy> deleteInsurancePolicyByID(
            int insurancePolicyId) {

        InsurancePolicy insurancePolicy =
                insurancePolicyDao.deleteInsurancePolicy(
                        insurancePolicyId);

        if (insurancePolicy == null) {
            throw new ResourceNotFoundException(
                    "Insurance policy not found with ID: "
                            + insurancePolicyId
            );
        }

        ApiResponse<InsurancePolicy> response =
                new ApiResponse<>();

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage(
                "Insurance policy deleted successfully!");
        response.setData(insurancePolicy);

        return response;
    }

    public ApiResponse<InsurancePolicy> updateInsurancePolicy(
            InsurancePolicy insurancePolicy) {

        if (insurancePolicy == null) {
            throw new IllegalArgumentException(
                    "Insurance policy details cannot be null!"
            );
        }

        InsurancePolicy existingPolicy =
                insurancePolicyDao.getByInsurancePolicyId(
                        insurancePolicy.getInsurancePolicyId());

        if (existingPolicy == null) {
            throw new ResourceNotFoundException(
                    "Insurance policy not found with ID: "
                            + insurancePolicy.getInsurancePolicyId()
            );
        }

        existingPolicy.setInsurancePolicyNumber(
                insurancePolicy.getInsurancePolicyNumber());

        existingPolicy.setInsurancePolicyType(
                insurancePolicy.getInsurancePolicyType());

        existingPolicy.setInsurancePolicyCoverageAmount(
                insurancePolicy.getInsurancePolicyCoverageAmount());

        existingPolicy.setInsurancePolicyPremium(
                insurancePolicy.getInsurancePolicyPremium());

        existingPolicy.setInsurancePolicyStartDate(
                insurancePolicy.getInsurancePolicyStartDate());

        existingPolicy.setInsurancePolicyEndDate(
                insurancePolicy.getInsurancePolicyEndDate());

        InsurancePolicy updatedPolicy =
                insurancePolicyDao.updateInsurancePolicy(
                        existingPolicy);

        ApiResponse<InsurancePolicy> response =
                new ApiResponse<>();

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage(
                "Insurance policy updated successfully!");
        response.setData(updatedPolicy);

        return response;
    }

    public ApiResponse<List<InsurancePolicy>>
    displayAllInsurancePolicies() {

        List<InsurancePolicy> insurancePolicies =
                insurancePolicyDao.displayAllInsurancePolicy();

        ApiResponse<List<InsurancePolicy>> response =
                new ApiResponse<>();

        response.setStatusCode(HttpStatus.OK.value());

        if (insurancePolicies.isEmpty()) {
            response.setMessage(
                    "No insurance policies found!");
        } else {
            response.setMessage(
                    "Insurance policies available!");
        }

        response.setData(insurancePolicies);

        return response;
    }
}