package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BrigadeGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.CompanyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyGroupRepository extends JpaRepository<CompanyGroup, Long> {

    Optional<CompanyGroup> findCompanyGroupById(Integer id);

    ArrayList<CompanyGroup> findAll();

    List<CompanyGroup> findAllByBattalionGroup(BattalionGroup battalionGroup);
}
