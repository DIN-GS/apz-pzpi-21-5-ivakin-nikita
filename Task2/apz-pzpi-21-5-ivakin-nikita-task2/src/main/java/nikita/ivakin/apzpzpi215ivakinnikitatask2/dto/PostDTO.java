package nikita.ivakin.apzpzpi215ivakinnikitatask2.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ScanningDevice;

@Data
@Builder
public class PostDTO {

    private Integer id;

    private String location;

    private Integer scanningDeviceId;

    private ScanningDevice scanningDevice;
}
