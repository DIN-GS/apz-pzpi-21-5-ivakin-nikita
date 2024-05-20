package nikita.ivakin.apzpzpi215ivakinnikitatask2.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Role;

@Data
@Builder
public class ResourcesRequestDTO {

    private Integer commanderId;
    private Integer militaryGroupId;
    private Role roleOfCommander;
    private int ammo556x45ArCount;
    private int ammo545x39AkRpkCount;
    private int ammo762x39AkCount;
    private int ammo145KpvtCount;
    private int ammo40mmRpgCount;
    private int ammo40mmGpCount;
    private int ammo762PktCount;
    private int defensiveGrenadesCount;
    private int offensiveGrenadesCount;
    private int bodyArmorCount;
    private int helmetsCount;
    private int riflesCount;
    private int machineGunsCount;
    private int dryRationsCount;
    private int foodCount;
    private int tankCount;
    private int apcCount;
}
