package nikita.ivakin.apzpzpi215ivakinnikitatask2.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.PostDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.Post;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ScanningDevice;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.PostCreationException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.ScanningDeviceCreationException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PostService postService;
    private final ScanningDeviceService scanningDeviceService;

    public boolean createPost(PostDTO postDTO) {
        try {
            postService.createPost(postDTO);
            return true;
        } catch (Exception e){
            throw new PostCreationException("Error in creating post in admin service.");
        }

    }

    public boolean createScanningDeviceForPost(Integer id, ScanningDevice scanningDeviceDTO) {
        Post post = postService.findPostById(id);
        try {
            ScanningDevice scanningDevice = ScanningDevice.builder()
                    .post(post).tier(scanningDeviceDTO.getTier()).build();
            scanningDevice = scanningDeviceService.save(scanningDevice);
            post.setScanningDevice(scanningDevice);
            return true;
        } catch (Exception e) {
            throw new ScanningDeviceCreationException("Error in creation scanning device for post with id " + id);
        }

    }

}
