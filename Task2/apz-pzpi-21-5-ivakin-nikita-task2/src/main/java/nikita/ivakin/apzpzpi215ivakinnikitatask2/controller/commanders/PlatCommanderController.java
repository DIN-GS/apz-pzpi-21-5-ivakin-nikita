package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.PlatGroup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/plat-com")
public class PlatCommanderController {

    /*@GetMapping("/get-plat-resources")
    public ResponseEntity<PlatGroup> getPlats(){
        List<PlatGroup> platGroups = new ArrayList<>();
        return new ResponseEntity<>(platGroups, HttpStatus.OK);
    }*/

}
