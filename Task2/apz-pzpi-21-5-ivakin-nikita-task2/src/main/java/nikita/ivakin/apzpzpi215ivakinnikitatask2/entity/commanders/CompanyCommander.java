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
@AttributeOverride(name = "id", column = @Column(name = "company_commander_id"))
public class CompanyCommander extends Commander{

    @Column(name = "battalion_group_id")
    private Integer battalionGroupId;

    @Column(name = "company_group_id")
    private Integer companyGroupId;

}
