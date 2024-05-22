package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.PlatGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ResourcesUpdateResponse;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.PlatGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders.PlatCommanderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/plat-com")
@RequiredArgsConstructor
public class PlatCommanderController {

    private final PlatCommanderService platCommanderService;

    //Create Service for messages with ask for resources

    @GetMapping("/get-plat-resources")
    public ResponseEntity<PlatGroup> getPlats(){
        PlatGroup platGroup = platCommanderService.getPlatGroup();
        return new ResponseEntity<>(platGroup, HttpStatus.OK);
    }

    //For one Plat to see if it was done
    @GetMapping("/get/plat-requests")
    public ResponseEntity<List<SupplyRequest>> getPlatRequests(){
        List<SupplyRequest> supplyRequests = platCommanderService.getPlatRequests();
        return new ResponseEntity<>(supplyRequests, HttpStatus.OK);
    }

    @PostMapping("/update/plat-resources")
    public ResponseEntity<ResourcesUpdateResponse> updatePlatResources(@RequestBody PlatGroupDTO platGroupDTO) {
        ResourcesUpdateResponse resourcesUpdateResponse = platCommanderService.updatePlatResources(platGroupDTO);
        return new ResponseEntity<>(resourcesUpdateResponse, HttpStatus.OK);
    }

    @PostMapping("/ask/for-resources")
    public ResponseEntity<Boolean> askForResources(@RequestBody ResourcesRequestDTO resourcesRequestDTO) {
        boolean result = platCommanderService.askForResources(resourcesRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
