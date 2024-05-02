package nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups;


import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "company_group")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "company_group_id"))
public class CompanyGroup extends MilitaryGroup {

    @Column(name = "battalion_group_id")
    private Integer battalionId;

    @Column(name = "company_commander_id")
    private Integer companyCommanderId;
}
