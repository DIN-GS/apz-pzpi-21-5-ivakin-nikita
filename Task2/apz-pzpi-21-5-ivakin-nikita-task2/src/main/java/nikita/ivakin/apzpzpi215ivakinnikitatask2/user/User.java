package nikita.ivakin.apzpzpi215ivakinnikitatask2.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.POST;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.RANK;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.token.Token;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user", schema = "project")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_seq_generator")
    @SequenceGenerator(name = "user_seq_generator", sequenceName = "project._user_id_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "email")
    private String email;

    @Column(name = "passport_number")
    private String passport_number;

    @Column(name = "password")
    private String password;

    @Column(name = "rank")
    private RANK rank;

    @Column(name = "post")
    private POST post;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}