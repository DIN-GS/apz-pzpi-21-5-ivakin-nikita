package nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups;

import jakarta.persistence.*;
import lombok.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.RegimentCommander;

import java.util.List;

@Entity
@Table(name = "regiment_group", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "regiment_group_id"))
public class RegimentGroup extends MilitaryGroup {



    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "regiment_commander_id", unique = true)
    private RegimentCommander regimentCommanderId;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "brigade_group_id", unique = true)
    private BrigadeGroup brigadeGroup;

    @OneToMany(mappedBy = "regimentGroup",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<BattalionGroup> battalionGroups;

}

