package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BattalionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BattalionGroupRepository extends JpaRepository<BattalionGroup, Long> {

    Optional<BattalionGroup> findBattalionGroupById(Integer id);
}
