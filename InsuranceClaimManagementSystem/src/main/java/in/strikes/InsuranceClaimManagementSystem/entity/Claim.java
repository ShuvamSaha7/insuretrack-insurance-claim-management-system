package in.strikes.InsuranceClaimManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claimId")
    private int claimId;

    @NotNull(message = "Claim number is required")
    @Positive(message = "Claim number must be greater than zero")
    @Column(name = "claimNumber", nullable = false)
    private Long claimNumber;

    @NotBlank(message = "Claim description is required")
    @Column(name = "claimDescription", nullable = false)
    private String claimDescription;

    @NotBlank(message = "Claim date is required")
    @Column(name = "claimDate", nullable = false)
    private String claimDate;

    @NotBlank(message = "Claim status is required")
    @Column(name = "claimStatus", nullable = false)
    private String claimStatus;

    @NotBlank(message = "Claim amount is required")
    @Column(name = "claimAmount", nullable = false)
    private String claimAmount;

    @ManyToOne
    @JoinColumn(name = "policyId")
    private InsurancePolicy insurancePolicy;
}