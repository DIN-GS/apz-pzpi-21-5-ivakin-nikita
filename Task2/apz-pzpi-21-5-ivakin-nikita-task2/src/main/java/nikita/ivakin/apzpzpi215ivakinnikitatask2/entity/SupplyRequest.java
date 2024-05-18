package nikita.ivakin.apzpzpi215ivakinnikitatask2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Role;

import java.time.LocalDate;

@Entity
@Table(name = "supply_request", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class SupplyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Integer requestId;

    private Integer commanderId;

    private Integer militaryGroupId;

    @Enumerated(EnumType.STRING)
    private Role roleOfCommander;

    private Integer executiveGroupId;

    private Integer executiveCommanderId;

    @Enumerated(EnumType.STRING)
    private Role roleOfExecutiveCommander;

    private LocalDate dateOfRequest;

    private LocalDate executionСomplitionDate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "resources_request_id", unique = true)
    private ResourcesRequest resourcesRequestId;


}
