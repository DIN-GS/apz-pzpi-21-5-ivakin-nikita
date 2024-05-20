package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.requests;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyRequestRepository extends JpaRepository<SupplyRequest, Integer> {

    List<SupplyRequest> findSupplyRequestsByMilitaryGroupId(Integer id);

    List<SupplyRequest> findSupplyRequestsBySeniorMilitaryGroupId(Integer id);


}
