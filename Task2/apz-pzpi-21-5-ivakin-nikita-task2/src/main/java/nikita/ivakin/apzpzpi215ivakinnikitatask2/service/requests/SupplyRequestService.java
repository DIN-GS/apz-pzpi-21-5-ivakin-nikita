package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.requests.SupplyRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SupplyRequestService {

    @Autowired
    private final SupplyRequestRepository supplyRequestRepository;


    public List<SupplyRequest> getSupplyRequestsForPlatByPlatId(Integer id) {
        List<SupplyRequest> supplyRequests = supplyRequestRepository.findSupplyRequestsByMilitaryGroupId(id);
        if (supplyRequests == null || supplyRequests.size() == 0) {
            log.info("There aren't supply requests for plat group with id " + id);
        }
        return supplyRequests;
    }

    public List<SupplyRequest> getSupplyRequestsForCompanyByCompanyId(Integer id) {
        List<SupplyRequest> supplyRequests = supplyRequestRepository.findSupplyRequestsByMilitaryGroupId(id);
        if (supplyRequests == null || supplyRequests.size() == 0) {
            log.info("There aren't supply requests for company group with id " + id);
        }
        return supplyRequests;
    }

    public List<SupplyRequest> getSupplyRequestsForPlatsByCompanyId(Integer id) {
        List<SupplyRequest> supplyRequests = supplyRequestRepository.findSupplyRequestsBySeniorMilitaryGroupId(id);
        if (supplyRequests == null || supplyRequests.size() == 0) {
            log.info("There aren't supply requests for plat groups with company id " + id);
        }
        return supplyRequests;
    }


    @Transactional
    public void save(SupplyRequest supplyRequest) {
        supplyRequestRepository.save(supplyRequest);
    }



}
