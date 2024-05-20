package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.CompanyGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.MilitaryGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders.BattalionCommanderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/bat-com")
@RequiredArgsConstructor
public class BattalionCommanderController {

    private final BattalionCommanderService battalionCommanderService;

    @GetMapping("/battalion-military-groups")
    public ResponseEntity<List<MilitaryGroup>> getBattalionMilitaryGroups(){
        List<MilitaryGroup> battalionMilitaryGroups = new ArrayList<>();
        return new ResponseEntity<>(battalionMilitaryGroups, HttpStatus.OK);
    }

    @PostMapping("/create/company")
    public ResponseEntity<Boolean> createCompany(@RequestBody CompanyGroupDTO companyGroupDTO){
        boolean result = battalionCommanderService.createCompany(companyGroupDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @PutMapping("/assign/company-commander")
    public ResponseEntity<Boolean> assignCompanyCommander(@RequestParam Integer companyCommanderId, @RequestParam Integer companyGroupId) {
        boolean result = battalionCommanderService.assignCompanyCommander(companyCommanderId, companyGroupId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get/battalion-requests")
    public ResponseEntity<List<SupplyRequest>> getBattalionRequests(){
        List<SupplyRequest> supplyRequests = battalionCommanderService.getBattalionRequests();
        return new ResponseEntity<>(supplyRequests, HttpStatus.OK);
    }

    @GetMapping("/get/company-requests")
    public ResponseEntity<List<SupplyRequest>> getCompaniesRequests(){
        List<SupplyRequest> supplyRequests = battalionCommanderService.getCompaniesRequests();
        return new ResponseEntity<>(supplyRequests, HttpStatus.OK);
    }

    @PostMapping("/send/resources-to-company")
    public ResponseEntity<Boolean> sendResources() {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/ask/for-resources")
    public ResponseEntity<Boolean> askForResources(@RequestBody ResourcesRequestDTO resourcesRequestDTO){
        boolean result = battalionCommanderService.askForResource(resourcesRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
