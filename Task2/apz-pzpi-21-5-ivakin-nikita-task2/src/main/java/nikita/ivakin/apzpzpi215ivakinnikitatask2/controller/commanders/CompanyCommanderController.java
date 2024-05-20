package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.PlatGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.MilitaryGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders.CompanyCommanderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/com-com")
@RequiredArgsConstructor
public class CompanyCommanderController {

    private final CompanyCommanderService companyCommanderService;

    @GetMapping("/company-military-groups")
    public ResponseEntity<List<MilitaryGroup>> getCompanyMilitaryGroups() {
        List<MilitaryGroup> companyMilitaryGroups = new ArrayList<>();
        return new ResponseEntity<>(companyMilitaryGroups, HttpStatus.OK);
    }

    @PostMapping("/create/plat")
    public ResponseEntity<Boolean> createPlat(@RequestBody PlatGroupDTO platGroupDTO) {
        boolean result = companyCommanderService.createPlat(platGroupDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/assign/plat-commander")
    public ResponseEntity<Boolean> assignPlatCommander(@RequestParam Integer platCommanderId, @RequestParam Integer platGroupId) {
        boolean result = companyCommanderService.assignPlatCommander(platCommanderId, platGroupId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get/company-requests")
    public ResponseEntity<List<SupplyRequest>> getCompanyRequests(){
        List<SupplyRequest> supplyRequests = companyCommanderService.getCompanyRequests();
        return new ResponseEntity<>(supplyRequests, HttpStatus.OK);
    }

    @GetMapping("/get/plat-requests")
    public ResponseEntity<List<SupplyRequest>> getPlatsRequests(){
        List<SupplyRequest> supplyRequests = companyCommanderService.getPlatsRequests();
        return new ResponseEntity<>(supplyRequests, HttpStatus.OK);
    }

    @PostMapping("/send/resources-to-plat")
    public ResponseEntity<Boolean> sendResources() {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/ask/for-resources")
    public ResponseEntity<Boolean> askForResources(@RequestBody ResourcesRequestDTO resourcesRequestDTO){
        boolean result = companyCommanderService.askForResources(resourcesRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
