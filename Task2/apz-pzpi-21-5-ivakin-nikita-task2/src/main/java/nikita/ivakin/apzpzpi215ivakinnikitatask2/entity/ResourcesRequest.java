package nikita.ivakin.apzpzpi215ivakinnikitatask2.entity;

import jakarta.persistence.*;
import lombok.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Role;

import java.time.LocalDateTime;

@Entity
@Table(name = "resources_request", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ResourcesRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resources_request_id")
    private Integer id;

    private Integer commanderId;

    private Integer militaryGroupId;

    @Enumerated(EnumType.STRING)
    private Role roleOfCommander;

    @Column(name = "ammo_556x45ar_count")
    private int ammo556x45ArCount;

    @Column(name = "ammo_545x39ak_rpk_count")
    private int ammo545x39AkRpkCount;

    @Column(name = "ammo_762x39ak_count")
    private int ammo762x39AkCount;

    @Column(name = "ammo_145kpvt_count")
    private int ammo145KpvtCount;

    @Column(name = "ammo_40mm_rpg_count")
    private int ammo40mmRpgCount;

    @Column(name = "ammo_40mm_gp_count")
    private int ammo40mmGpCount;

    @Column(name = "ammo_762pkt")
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

    @Column(name = "exact_time")
    private LocalDateTime exactTime;

}
