package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.requests;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ResourcesRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourcesRequestRepository extends JpaRepository<ResourcesRequest, Integer> {

    Optional<ResourcesRequest> findResourcesRequestByCommanderIdAndMilitaryGroupId(Integer commanderId, Integer militaryGroupId);

}
