package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.requests;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyRequestRepository extends JpaRepository<SupplyRequest, Integer> {

    List<SupplyRequest> findSupplyRequestsByMilitaryGroupIdAndRoleOfCommander(Integer militaryGroupId, Role roleOfCommander);

    List<SupplyRequest> findSupplyRequestsBySeniorMilitaryGroupIdAndRoleOfCommander(Integer seniorMilitaryGroupId , Role roleOfCommander);

}
