package nikita.ivakin.apzpzpi215ivakinnikitatask2.dto;

import lombok.Builder;
import lombok.Data;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.LogisticCompanyDTO;

@Data
@Builder
public class SupplyCarDTO {

    private Integer id;

    private Integer tier;

    private Integer supplyRequestId;

    private Integer logisticCompanyId;

    private SupplyRequestDTO supplyRequest;

    private LogisticCompanyDTO logisticCompany;
}
