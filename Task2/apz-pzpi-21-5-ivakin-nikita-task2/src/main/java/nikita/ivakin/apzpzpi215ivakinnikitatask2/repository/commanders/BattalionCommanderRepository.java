package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BattalionCommander;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattalionCommanderRepository extends JpaRepository<BattalionCommander, Long> {

}
