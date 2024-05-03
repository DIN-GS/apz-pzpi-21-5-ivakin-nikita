package nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups;

import jakarta.persistence.*;
import lombok.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BrigadeCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.LogisticCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.RegimentCommander;

import java.util.List;

@Entity
@Table(name = "brigade_group", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "brigade_group_id"))
public class BrigadeGroup extends MilitaryGroup {


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "brigade_commander_id", unique = true)
    private BrigadeCommander brigadeCommanderId;

    @OneToMany(mappedBy = "brigadeGroup",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<RegimentGroup> regimentGroups;

    @OneToMany(mappedBy = "brigadeGroup",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<LogisticCompany> logisticCompanies;
}


