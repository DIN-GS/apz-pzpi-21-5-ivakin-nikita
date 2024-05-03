package nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders;

import jakarta.persistence.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.POST;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.RANK;

@MappedSuperclass
public abstract class Commander {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /*@Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "rank")
    private RANK rank;

    @Column(name = "post")
    private POST post;

    @Column(name = "age")
    private Integer age;

    @Column(name = "passport_number")
    private Integer passportNumber;*/

    /*@Column(name = "last_name")
    private Long idOfGroup;*/
}