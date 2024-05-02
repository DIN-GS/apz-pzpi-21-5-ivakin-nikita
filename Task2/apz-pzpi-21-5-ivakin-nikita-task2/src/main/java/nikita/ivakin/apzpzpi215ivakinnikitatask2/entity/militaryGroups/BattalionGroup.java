package nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "battalion_group")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "battalion_group_id"))
public class BattalionGroup extends MilitaryGroup {

    @Column(name = "regiment_group_id")
    private Integer regimentGroupId;

    @Column(name = "battalion_commander_id")
    private Integer battalionCommanderId;
}
