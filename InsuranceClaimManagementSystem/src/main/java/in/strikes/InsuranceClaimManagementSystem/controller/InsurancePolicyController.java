package in.strikes.InsuranceClaimManagementSystem.controller;

import in.strikes.InsuranceClaimManagementSystem.entity.ApiResponse;
import in.strikes.InsuranceClaimManagementSystem.entity.InsurancePolicy;
import in.strikes.InsuranceClaimManagementSystem.service.InsurancePolicyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurancePolicy")
public class InsurancePolicyController {

    @Autowired
    private InsurancePolicyService insurancePolicyService;

    @PostMapping("/saveInsurancePolicy")
    public ApiResponse<InsurancePolicy> insertInsurancePolicy(
          @Valid @RequestBody InsurancePolicy insurancePolicy) {

        return insurancePolicyService.insertInsurancePolicy(
                insurancePolicy);
    }

    @GetMapping("/getByInsurancePolicyId/{insurancePolicyId}")
    public ApiResponse<InsurancePolicy> getByInsurancePolicyId(
            @PathVariable int insurancePolicyId) {

        return insurancePolicyService.getInsurancePolicyById(
                insurancePolicyId);
    }

    @DeleteMapping("/deleteInsurancePolicy/{policyId}")
    public ApiResponse<InsurancePolicy> deleteInsurancePolicy(
            @PathVariable int policyId) {

        return insurancePolicyService.deleteInsurancePolicyByID(
                policyId);
    }

    @PutMapping("/updateInsurancePolicy/{insurancePolicyId}")
    public ApiResponse<InsurancePolicy> updateInsurancePolicy(@Valid
            @RequestBody InsurancePolicy insurancePolicy,
            @PathVariable int insurancePolicyId) {

        insurancePolicy.setInsurancePolicyId(insurancePolicyId);

        return insurancePolicyService.updateInsurancePolicy(
                insurancePolicy);
    }

    @GetMapping("/displayAllPolicy")
    public ApiResponse<List<InsurancePolicy>> displayAllPolicy() {

        return insurancePolicyService.displayAllInsurancePolicies();
    }
}