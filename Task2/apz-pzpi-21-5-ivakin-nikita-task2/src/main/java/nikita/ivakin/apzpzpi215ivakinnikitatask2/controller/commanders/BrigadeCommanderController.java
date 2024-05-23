package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.BattalionGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.BrigadeGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.LogisticCompanyDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ResourcesUpdateResponse;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders.BrigadeCommanderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/brig-com")
@RequiredArgsConstructor
public class BrigadeCommanderController {

    private final BrigadeCommanderService brigadeCommanderService;


    @GetMapping("/brigade-battalion-groups")
    public ResponseEntity<List<BattalionGroup>> getBattalionGroups() {
        List<BattalionGroup> battalionGroups = brigadeCommanderService.getBrigadeBattalionGroups();
        return new ResponseEntity<>(battalionGroups, HttpStatus.OK);
    }

    @PostMapping("/create/brigade")
    public ResponseEntity<Boolean> createBrigade(@RequestBody BrigadeGroupDTO brigadeGroupDTO) {
        boolean result = brigadeCommanderService.createBrigade(brigadeGroupDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/update/brigade-resources")
    public ResponseEntity<ResourcesUpdateResponse> updateBrigadeResources(@RequestBody BrigadeGroupDTO brigadeGroupDTO) {
        ResourcesUpdateResponse resourcesUpdateResponse = brigadeCommanderService.updateBrigadeResources(brigadeGroupDTO);
        return new ResponseEntity<>(resourcesUpdateResponse, HttpStatus.OK);
    }

    @PostMapping("/create/logistic-company")
    public ResponseEntity<Boolean> createLogisticCompany(@RequestBody LogisticCompanyDTO logisticCompanyDTO) {
        boolean result = brigadeCommanderService.createLogisticCompany(logisticCompanyDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    //TO DO
    @PostMapping("/create/battalion")
    public ResponseEntity<Boolean> createBattalion(@RequestBody BattalionGroupDTO battalionGroupDTO) {
        boolean result = brigadeCommanderService.createBattalion(battalionGroupDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @PutMapping("/assign/battalion-commander")
    public ResponseEntity<Boolean> assignBattalionCommander(@RequestParam Integer battalionCommanderId, @RequestParam Integer battalionGroupId) {
        boolean result = brigadeCommanderService.assignBattalionCommander(battalionCommanderId, battalionGroupId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get/brigade-requests")
    public ResponseEntity<List<SupplyRequest>> getBrigadeRequests(){
        List<SupplyRequest> supplyRequests = brigadeCommanderService.getBrigadeRequests();
        return new ResponseEntity<>(supplyRequests, HttpStatus.OK);
    }

    @GetMapping("/get/battalion-requests")
    public ResponseEntity<List<SupplyRequest>> getBattalionsRequests(){
        List<SupplyRequest> supplyRequests = brigadeCommanderService.getBattalionRequests();
        return new ResponseEntity<>(supplyRequests, HttpStatus.OK);
    }

    @PostMapping("/send/resources-to-battalion")
    public ResponseEntity<ResourcesUpdateResponse> sendResources(@RequestBody SupplyRequest supplyRequest) {
        ResourcesUpdateResponse resourcesUpdateResponse = brigadeCommanderService.sendResourcesToBattalion(supplyRequest);
        return new ResponseEntity<>(resourcesUpdateResponse, HttpStatus.OK);
    }

    @PostMapping("/ask/for-resources")
    public ResponseEntity<Boolean> askForResources(@RequestBody ResourcesRequestDTO resourcesRequestDTO){
        boolean result = brigadeCommanderService.askForResources(resourcesRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
