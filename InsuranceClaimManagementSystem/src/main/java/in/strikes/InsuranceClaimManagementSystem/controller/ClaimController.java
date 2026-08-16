package in.strikes.InsuranceClaimManagementSystem.controller;

import in.strikes.InsuranceClaimManagementSystem.entity.ApiResponse;
import in.strikes.InsuranceClaimManagementSystem.entity.Claim;
import in.strikes.InsuranceClaimManagementSystem.service.ClaimService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claim")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @PostMapping("/saveClaim/{policyId}")
    public ApiResponse<Claim> saveClaim(@Valid
            @RequestBody Claim claim,
            @PathVariable int policyId) {

        return claimService.insertClaim(claim, policyId);
    }

    @GetMapping("/getClaimById/{claimId}")
    public ApiResponse<Claim> getClaimById(
            @PathVariable int claimId) {

        return claimService.getClaimById(claimId);
    }

    @DeleteMapping("/deleteClaim/{claimId}")
    public ApiResponse<Claim> deleteClaim(
            @PathVariable int claimId) {

        return claimService.deleteClaimByID(claimId);
    }

    @PutMapping("/updateClaim/{claimId}")
    public ApiResponse<Claim> updateClaim(@Valid
            @RequestBody Claim claim,
            @PathVariable int claimId) {

        return claimService.updateClaim(claim, claimId);
    }

    @GetMapping("/displayAllClaim")
    public ApiResponse<List<Claim>> displayAllClaim() {

        return claimService.displayAllClaims();
    }
}