package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.BattalionGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.BrigadeGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BrigadeGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.MilitaryGroup;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/brig-com")
public class BrigadeCommanderController {

    @GetMapping("/brigade-military-groups")
    public ResponseEntity<List<MilitaryGroup>> getMilitaryGroups() {
        List<MilitaryGroup> militaryGroups = new ArrayList<MilitaryGroup>();
        return new ResponseEntity<>(militaryGroups, HttpStatus.OK);
    }

    @PostMapping("/create/brigade")
    public ResponseEntity<Boolean> createBrigade(@RequestBody BrigadeGroupDTO brigadeGroupDTO) {
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @PostMapping("/create/battalion")
    public ResponseEntity<Boolean> createBattalion(@RequestBody BattalionGroupDTO battalionGroupDTO) {
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @PutMapping("/assign/battalion-commander")
    public ResponseEntity<Boolean> assignBattalionCommander(@RequestParam Integer battalionCommanderId) {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }


}
