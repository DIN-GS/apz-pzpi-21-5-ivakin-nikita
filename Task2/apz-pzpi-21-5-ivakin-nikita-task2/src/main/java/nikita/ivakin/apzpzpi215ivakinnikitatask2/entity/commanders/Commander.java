package nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.POST;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.RANK;

@MappedSuperclass
public abstract class Commander {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;

    private String lastName;

    private String secondName;

    private RANK rank;

    private POST post;

    private Integer age;

    private Integer passportNumber;

    private Long idOfGroup;
}