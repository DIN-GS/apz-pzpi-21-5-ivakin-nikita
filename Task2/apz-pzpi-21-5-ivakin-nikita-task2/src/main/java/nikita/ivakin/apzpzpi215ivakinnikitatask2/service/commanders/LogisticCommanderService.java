package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.LogisticCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.LogisticCommanderRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests.SupplyRequestService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class LogisticCommanderService {

    private final LogisticCommanderRepository logisticCommanderRepository;
    private final SupplyRequestService supplyRequestService;

    public LogisticCommander getAuthenticatedLogisticCommander() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String brigadeCommanderEmail = authentication.getName();
        return findLogisticCommanderByEmail(brigadeCommanderEmail);
    }

    public List<SupplyRequest> getAllRequests() {
        LogisticCommander logisticCommander = getAuthenticatedLogisticCommander();
        List<SupplyRequest> supplyRequests = supplyRequestService.getSupplyRequestsForBrigadeByBrigadeId(logisticCommander.getBrigadeGroup().getId(), Role.BRIGADE_COMMANDER);
        supplyRequests.addAll(supplyRequestService.getAllSupplyRequestsByBrigadeCommanderId(logisticCommander.getBrigadeCommander().getId()));
        return supplyRequests;
    }

    public LogisticCommander findLogisticCommanderByEmail(String email) {
        Optional<LogisticCommander> tempLogCom = logisticCommanderRepository.findLogisticCommanderByEmail(email);
        if (tempLogCom.isPresent()) {
            return tempLogCom.get();
        } else {
            log.info("Error logistic commander with email" + email  + " doesn't exist.");
        }
        return null;
    }

    @Transactional
    public void save(LogisticCommander logisticCommander){
        logisticCommanderRepository.save(logisticCommander);
    }


    public LogisticCommander findLogisticCommanderById(Integer logisticCommanderId) {
        Optional<LogisticCommander> tempLogCommander = logisticCommanderRepository.findLogisticCommanderById(logisticCommanderId);
        if (tempLogCommander.isPresent()) {
            return tempLogCommander.get();
        } else {
            log.info("Error logistic commander with id" + logisticCommanderId + " doesn't exist.");
        }
        return null;
    }
}
