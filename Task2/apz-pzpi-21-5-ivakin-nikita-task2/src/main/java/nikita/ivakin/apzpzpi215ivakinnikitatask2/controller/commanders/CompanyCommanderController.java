package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.PlatGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.MilitaryGroup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/com-com")
public class CompanyCommanderController {

    @GetMapping("/company-military-groups")
    public ResponseEntity<List<MilitaryGroup>> getCompanyMilitaryGroups() {
        List<MilitaryGroup> companyMilitaryGroups = new ArrayList<>();
        return new ResponseEntity<>(companyMilitaryGroups, HttpStatus.OK);
    }

    @PostMapping("/create/plat")
    public ResponseEntity<Boolean> createPlat(@RequestBody PlatGroupDTO platGroupDTO) {
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Boolean> assignPlatCommander(@RequestParam Integer platCommanderId) {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
