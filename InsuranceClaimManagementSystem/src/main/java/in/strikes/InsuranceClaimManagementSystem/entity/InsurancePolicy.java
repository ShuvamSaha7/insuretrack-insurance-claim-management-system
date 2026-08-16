package in.strikes.InsuranceClaimManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "insurancePolicy")
@Getter
@Setter
public class InsurancePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policyId")
    private int insurancePolicyId;

    @NotBlank(message = "Policy number is required")
    @Column(name = "policyNumber", nullable = false)
    private String insurancePolicyNumber;

    @NotBlank(message = "Policy type is required")
    @Column(name = "policyType", nullable = false)
    private String insurancePolicyType;

    @NotNull(message = "Coverage amount is required")
    @Positive(message = "Coverage amount must be greater than zero")
    @Column(name = "policyCoverageAmount", nullable = false)
    private Long insurancePolicyCoverageAmount;

    @NotBlank(message = "Policy premium is required")
    @Column(name = "policyPremium", nullable = false)
    private String insurancePolicyPremium;

    @NotBlank(message = "Policy start date is required")
    @Column(name = "policyStartDate", nullable = false)
    private String insurancePolicyStartDate;

    @NotBlank(message = "Policy end date is required")
    @Column(name = "policyEndDate", nullable = false)
    private String insurancePolicyEndDate;
}