package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.requests.ResourcesRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ResourcesRequestService {

    @Autowired
    private final ResourcesRequestRepository resourcesRequestRepository;

}
