package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.LogisticCommander;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticCommanderRepository extends JpaRepository<LogisticCommander, Long> {


}
