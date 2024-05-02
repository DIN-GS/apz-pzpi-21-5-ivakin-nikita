package nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "plat_group")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "plat_group_id"))

public class PlatGroup extends MilitaryGroup {

    @Column(name = "company_group_id")
    private Integer companyId;

    @Column(name = "plat_commander_id")
    private Integer platCommanderId;
}
