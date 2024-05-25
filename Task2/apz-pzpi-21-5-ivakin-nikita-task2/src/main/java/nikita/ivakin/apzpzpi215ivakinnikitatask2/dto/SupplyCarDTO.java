package nikita.ivakin.apzpzpi215ivakinnikitatask2.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.LogisticCompanyDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.LogisticCompany;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.requests.SupplyRequest;

@Data
@Builder
public class SupplyCarDTO {

    private Integer id;

    private String carNumber;

    private Integer supplyRequestId;

    private Integer logisticCompanyId;

    private SupplyRequestDTO supplyRequest;

    private LogisticCompanyDTO logisticCompany;
}
