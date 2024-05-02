package nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "brigade_group")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "brigade_group_id"))
public class BrigadeGroup extends MilitaryGroup {

    @Column(name = "brigade_commander_id")
    private Integer brigadeCommanderId;
}
