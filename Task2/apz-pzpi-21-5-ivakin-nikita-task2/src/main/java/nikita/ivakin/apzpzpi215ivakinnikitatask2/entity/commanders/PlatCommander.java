package nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "battalion_commander")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "plat_commander_id"))
public class PlatCommander extends Commander{

    @Column(name = "company_group_id")
    private Integer companyGroupId;

    @Column(name = "plat_group_id")
    private Integer platGroupId;

}