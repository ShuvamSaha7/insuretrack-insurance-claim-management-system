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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientId")
    private int clientId;

    @NotBlank(message = "Client name is required")
    @Column(name = "clientName", nullable = false)
    private String clientName;

    @NotBlank(message = "Client date of birth is required")
    @Column(name = "clientDateOfBirth", nullable = false)
    private String clientDateOfBirth;

    @NotBlank(message = "Client address is required")
    @Column(name = "clientAddress", nullable = false)
    private String clientAddress;

    @NotNull(message = "Client contact information is required")
    @Positive(message = "Client contact information must be valid")
    @Column(name = "clientContactInformation", nullable = false)
    private Long clientContactInformation;

    @ManyToOne
    @JoinColumn(name = "policyId")
    private InsurancePolicy insurancePolicy;
}