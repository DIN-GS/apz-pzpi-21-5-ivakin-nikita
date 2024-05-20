package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ResourcesRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.requests.ResourcesRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ResourcesRequestService {

    @Autowired
    private final ResourcesRequestRepository resourcesRequestRepository;

    @Transactional
    public void save(ResourcesRequest resourcesRequest) {
        resourcesRequestRepository.save(resourcesRequest);
    }


    public ResourcesRequest findResourcesRequestByCommanderIdAndMilitaryGroupId(Integer commanderId, Integer militaryGroupId) {
        Optional<ResourcesRequest> tempResReq = resourcesRequestRepository.findResourcesRequestByCommanderIdAndMilitaryGroupId(commanderId, militaryGroupId);
        if (tempResReq.isPresent()) {
            return tempResReq.get();
        } else {
            log.info("Error resources request with commander id " +commanderId+ " and military group id " + militaryGroupId+ " doesn't exist.");
        }
        return null;
    }
}
