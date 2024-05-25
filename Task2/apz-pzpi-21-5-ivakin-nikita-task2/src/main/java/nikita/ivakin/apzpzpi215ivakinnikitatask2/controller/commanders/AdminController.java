package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import lombok.AllArgsConstructor;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.PostDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/create/post")
    public ResponseEntity<Boolean> createPost(@RequestBody PostDTO postDTO) {
        boolean result = adminService.createPost(postDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/create/scanning-device/for-post/{id}")
    public ResponseEntity<Boolean> createScanningDevice(@PathVariable Integer id) {
        boolean result = adminService.createScanningDeviceForPost(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
