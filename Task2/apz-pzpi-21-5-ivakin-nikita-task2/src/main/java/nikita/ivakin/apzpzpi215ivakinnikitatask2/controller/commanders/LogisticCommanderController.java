package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/log-com")
public class LogisticCommanderController {

    @GetMapping("/get/demands-of-military-groups")
    public ResponseEntity<Boolean> getDemands() {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
