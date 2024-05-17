package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.PlatGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlatGroupRepository extends JpaRepository<PlatGroup, Long> {

    Optional<PlatGroup> findPlatGroupById(Integer id);
}
