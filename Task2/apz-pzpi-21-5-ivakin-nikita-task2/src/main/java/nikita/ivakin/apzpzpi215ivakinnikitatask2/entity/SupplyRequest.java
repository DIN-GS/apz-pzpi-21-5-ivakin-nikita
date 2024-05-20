package nikita.ivakin.apzpzpi215ivakinnikitatask2.entity;

import jakarta.persistence.*;
import lombok.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Status;

import java.time.LocalDate;

@Entity
@Table(name = "supply_request", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SupplyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Integer requestId;

    private Integer executiveGroupId;

    private Integer executiveCommanderId;

    private Integer commanderId;

    private Integer militaryGroupId;

    private Integer seniorMilitaryGroupId;

    @Enumerated(EnumType.STRING)
    private Role roleOfCommander;

    @Enumerated(EnumType.STRING)
    private Role roleOfExecutiveCommander;

    private LocalDate dateOfRequest;

    private LocalDate executionСomplitionDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "resources_request_id", unique = true)
    private ResourcesRequest resourcesRequestId;


}
