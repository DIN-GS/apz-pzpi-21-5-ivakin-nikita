package nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.requests;

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

    @Column(name = "brigade_commander_id")
    private Integer brigadeCommanderId;

    @Enumerated(EnumType.STRING)
    private Role roleOfCommander;

    @Enumerated(EnumType.STRING)
    private Role roleOfExecutiveCommander;

    @Column(name = "date_of_request")
    private LocalDate dateOfRequest;

    @Column(name = "date_of_executing")
    private LocalDate dateOfExecuting;

    @Column(name = "delivery_complition_date")
    private LocalDate deliveryComplitionDate;

    @Column(name = "execution_complition_date")
    private LocalDate executionСomplitionDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "resources_request_id", unique = true)
    private ResourcesRequest resourcesRequestId;


}
