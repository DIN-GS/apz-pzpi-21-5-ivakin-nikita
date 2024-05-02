package nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class MilitaryGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int personnelCount;
    private int ammo556x45Count;
    private int ammo545x39Count;
    private int ammo762x39Count;
    private int ammo127x107Count;
    private int defensiveGrenadesCount;
    private int offensiveGrenadesCount;
    private int bodyArmorCount;
    private int helmetsCount;
    private int riflesCount;
    private int machineGunsCount;
}
