package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.CompanyGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.PlatGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.vlidation.CreateGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.vlidation.UpdateGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ResourcesUpdateResponse;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.MilitaryGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.PlatGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders.CompanyCommanderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/com-com")
@RequiredArgsConstructor
public class CompanyCommanderController {

    private final CompanyCommanderService companyCommanderService;

    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @GetMapping("/get-company-resources")
    public ResponseEntity<CompanyGroupDTO> getCompanyResources() {
        CompanyGroupDTO companyGroupDTO = companyCommanderService.getCompanyGroup();
        return new ResponseEntity<>(companyGroupDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @GetMapping("/company-plat-groups")
    public ResponseEntity<List<PlatGroup>> getCompanyPlatGroups() {
        List<PlatGroup> platGroups = companyCommanderService.getCompanyPlatGroups();
        return new ResponseEntity<>(platGroups, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @PostMapping("/create/plat")
    public ResponseEntity<Boolean> createPlat(@Validated({Default.class, CreateGroup.class})@RequestBody PlatGroupDTO platGroupDTO) {
        boolean result = companyCommanderService.createPlat(platGroupDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @PostMapping("/update/company-resources")
    public ResponseEntity<ResourcesUpdateResponse> updateCompanyResources(@Validated({Default.class, UpdateGroup.class})@RequestBody CompanyGroupDTO companyGroupDTO) {
        ResourcesUpdateResponse resourcesUpdateResponse = companyCommanderService.updateCompanyResources(companyGroupDTO);
        return new ResponseEntity<>(resourcesUpdateResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @PutMapping("/assign/plat-commander")
    public ResponseEntity<Boolean> assignPlatCommander(@RequestParam Integer platCommanderId, @RequestParam Integer platGroupId) {
        boolean result = companyCommanderService.assignPlatCommander(platCommanderId, platGroupId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @GetMapping("/get/company-requests")
    public ResponseEntity<List<SupplyRequest>> getCompanyRequests(){
        List<SupplyRequest> supplyRequests = companyCommanderService.getCompanyRequests();
        return new ResponseEntity<>(supplyRequests, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @GetMapping("/get/plat-requests")
    public ResponseEntity<List<SupplyRequest>> getPlatsRequests(){
        List<SupplyRequest> supplyRequests = companyCommanderService.getPlatsRequests();
        return new ResponseEntity<>(supplyRequests, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @PostMapping("/send/resources-to-plat")
    public ResponseEntity<ResourcesUpdateResponse> sendResources(@RequestParam SupplyRequest supplyRequest) {
        ResourcesUpdateResponse resourcesUpdateResponse = companyCommanderService.sendResourcesToPlat(supplyRequest);
        return new ResponseEntity<>(resourcesUpdateResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @PostMapping("/ask/for-resources")
    public ResponseEntity<Boolean> askForResources(@RequestBody ResourcesRequestDTO resourcesRequestDTO){
        boolean result = companyCommanderService.askForResources(resourcesRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
