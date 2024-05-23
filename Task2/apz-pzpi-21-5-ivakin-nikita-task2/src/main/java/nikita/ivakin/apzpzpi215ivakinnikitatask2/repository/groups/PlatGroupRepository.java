package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.PlatCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.CompanyGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.PlatGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlatGroupRepository extends JpaRepository<PlatGroup, Long> {

    Optional<PlatGroup> findPlatGroupById(Integer id);

    Optional<PlatGroup> findPlatGroupByPlatCommanderId(PlatCommander platCommanderId);

    ArrayList<PlatGroup> findAll();

    List<PlatGroup> findPlatGroupsByCompanyGroup(CompanyGroup companyGroup);
}
