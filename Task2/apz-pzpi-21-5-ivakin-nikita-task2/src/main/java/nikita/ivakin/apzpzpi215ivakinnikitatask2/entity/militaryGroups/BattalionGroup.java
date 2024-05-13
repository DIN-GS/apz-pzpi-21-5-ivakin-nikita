package nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups;

import jakarta.persistence.*;
import lombok.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BattalionCommander;

import java.util.List;

@Entity
@Table(name = "battalion_group", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "battalion_group_id"))
public class BattalionGroup extends MilitaryGroup {


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "battalion_commander_id", unique = true)
    private BattalionCommander battalionCommanderId;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "brigade_group_id", unique = true)
    private BrigadeGroup brigadeGroup;

    @OneToMany(mappedBy = "battalionGroup",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<CompanyGroup> companyGroups;

}
