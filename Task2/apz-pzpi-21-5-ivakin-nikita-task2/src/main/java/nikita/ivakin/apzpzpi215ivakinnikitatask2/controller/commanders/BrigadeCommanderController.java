package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.BattalionGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.BrigadeGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.LogisticCompanyDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.MilitaryGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders.BrigadeCommanderService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.BattalionGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/brig-com")
@RequiredArgsConstructor
public class BrigadeCommanderController {

    private final BrigadeCommanderService brigadeCommanderService;
    private final BattalionGroupService battalionGroupService;


    @GetMapping("/brigade-military-groups")
    public ResponseEntity<List<MilitaryGroup>> getMilitaryGroups() {
        List<MilitaryGroup> militaryGroups = new ArrayList<>();
        return new ResponseEntity<>(militaryGroups, HttpStatus.OK);
    }

    @PostMapping("/create/brigade")
    public ResponseEntity<Boolean> createBrigade(@RequestBody BrigadeGroupDTO brigadeGroupDTO) {
        boolean result = brigadeCommanderService.createBrigade(brigadeGroupDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/create/logistic-company")
    public ResponseEntity<Boolean> createLogisticCompany(@RequestBody LogisticCompanyDTO logisticCompanyDTO) {
        boolean result = brigadeCommanderService.createLogisticCompany(logisticCompanyDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    //TO DO
    @PostMapping("/create/battalion")
    public ResponseEntity<Boolean> createBattalion(@RequestBody BattalionGroupDTO battalionGroupDTO) {
        boolean result = battalionGroupService.createBattalion(battalionGroupDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @PutMapping("/assign/battalion-commander")
    public ResponseEntity<Boolean> assignBattalionCommander(@RequestParam Integer battalionCommanderId, @RequestParam Integer battalionGroupId) {
        boolean result = battalionGroupService.assignBattalionCommander(battalionCommanderId, battalionGroupId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
