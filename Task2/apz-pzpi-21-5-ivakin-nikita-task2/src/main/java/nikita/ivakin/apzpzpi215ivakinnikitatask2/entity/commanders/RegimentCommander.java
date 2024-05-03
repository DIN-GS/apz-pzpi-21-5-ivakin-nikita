package nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders;

import jakarta.persistence.*;
import lombok.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BrigadeGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.RegimentGroup;

import java.util.List;

@Entity
@Table(name = "regiment_commander", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "regiment_commander_id"))
public class RegimentCommander extends Commander{


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "regiment_group_id", unique = true)
    private RegimentGroup regimentGroup;



    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "brigade_commander_id", unique = true)
    private BrigadeCommander brigadeCommander;



    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "brigade_group_id", unique = true)
    private BrigadeGroup brigadeGroup;

    @OneToMany(mappedBy = "regimentCommander",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<BattalionCommander> battalionCommanders;
}
