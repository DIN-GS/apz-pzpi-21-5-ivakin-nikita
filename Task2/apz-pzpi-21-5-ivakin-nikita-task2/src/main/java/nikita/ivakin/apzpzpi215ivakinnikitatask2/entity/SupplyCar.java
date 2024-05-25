package nikita.ivakin.apzpzpi215ivakinnikitatask2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.requests.SupplyRequest;

@Entity
@Table(name = "supply_car", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class SupplyCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String carNumber;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id", unique = true)
    private SupplyRequest supplyRequest;
}
