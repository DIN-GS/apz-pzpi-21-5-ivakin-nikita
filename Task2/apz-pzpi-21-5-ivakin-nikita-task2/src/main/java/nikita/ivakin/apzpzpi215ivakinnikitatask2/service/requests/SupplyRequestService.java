package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.requests.SupplyRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class SupplyRequestService {

    @Autowired
    private final SupplyRequestRepository supplyRequestRepository;

    @Transactional
    public void save(SupplyRequest supplyRequest) {
        supplyRequestRepository.save(supplyRequest);
    }

}
