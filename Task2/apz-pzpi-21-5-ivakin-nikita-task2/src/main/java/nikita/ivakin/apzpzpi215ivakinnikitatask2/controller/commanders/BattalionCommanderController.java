package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.BattalionGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.CompanyGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.vlidation.CreateGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.vlidation.UpdateGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ResourcesUpdateResponse;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.CompanyGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.MilitaryGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders.BattalionCommanderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/bat-com")
@RequiredArgsConstructor
public class BattalionCommanderController {

    private final BattalionCommanderService battalionCommanderService;

    @GetMapping("/battalion-company-groups")
    public ResponseEntity<List<CompanyGroup>> getBattalionCompanyGroups(){
        List<CompanyGroup> battalionMilitaryGroups = battalionCommanderService.getBattalionCompanyGroups();
        return new ResponseEntity<>(battalionMilitaryGroups, HttpStatus.OK);
    }

    @PostMapping("/create/company")
    public ResponseEntity<Boolean> createCompany(@Validated({Default.class, CreateGroup.class}) @RequestBody CompanyGroupDTO companyGroupDTO){
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

    @PostMapping("/update/battalion-resources")
    public ResponseEntity<ResourcesUpdateResponse> updateBattalionResources(@Validated({Default.class, UpdateGroup.class})@RequestBody BattalionGroupDTO battalionGroupDTO) {
        ResourcesUpdateResponse resourcesUpdateResponse = battalionCommanderService.updateBattalionResources(battalionGroupDTO);
        return new ResponseEntity<>(resourcesUpdateResponse, HttpStatus.OK);
    }

    @PostMapping("/send/resources-to-company")
    public ResponseEntity<Boolean> sendResources(@RequestBody SupplyRequest supplyRequest) {
        ResourcesUpdateResponse resourcesUpdateResponse = battalionCommanderService.sendResourcesToCompany(supplyRequest);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/ask/for-resources")
    public ResponseEntity<Boolean> askForResources(@Valid @RequestBody ResourcesRequestDTO resourcesRequestDTO){
        boolean result = battalionCommanderService.askForResource(resourcesRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
