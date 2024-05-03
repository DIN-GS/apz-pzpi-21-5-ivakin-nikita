package nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups;


import jakarta.persistence.*;
import lombok.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.LogisticCommander;

@Entity
@Table(name = "logistic_company", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "logistic_company_id"))
public class LogisticCompany extends MilitaryGroup{


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "logistic_commander_id")
    private LogisticCommander logisticCommanderId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "brigade_group_id", unique = true)
    private BrigadeGroup brigadeGroup;


}




