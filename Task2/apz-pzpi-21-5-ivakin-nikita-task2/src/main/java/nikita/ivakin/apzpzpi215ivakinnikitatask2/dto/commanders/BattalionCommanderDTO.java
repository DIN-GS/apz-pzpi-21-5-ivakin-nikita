package nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.commanders;


import lombok.Builder;
import lombok.Data;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.POST;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.RANK;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Role;

@Builder
@Data
public class BattalionCommanderDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String secondName;

    private RANK rank;

    private POST post;

    private Role role;

    private Integer age;

    private String email;
}
