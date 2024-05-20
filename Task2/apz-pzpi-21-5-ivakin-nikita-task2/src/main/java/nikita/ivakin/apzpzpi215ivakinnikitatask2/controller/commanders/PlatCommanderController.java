package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.PlatGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ResourcesRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.PlatGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders.PlatCommanderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @PostMapping("/update/plat-resources")
    public ResponseEntity<PlatGroup> updatePlatResources(@RequestBody PlatGroupDTO platGroupDTO) {
        PlatGroup platGroup = platCommanderService.updatePlatResources(platGroupDTO);
        return new ResponseEntity<>(platGroup, HttpStatus.OK);
    }

    @PostMapping("/ask/for-resources")
    public ResponseEntity<Boolean> askForResources(@RequestBody ResourcesRequestDTO resourcesRequestDTO) {
        boolean result = platCommanderService.askForResources(resourcesRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
