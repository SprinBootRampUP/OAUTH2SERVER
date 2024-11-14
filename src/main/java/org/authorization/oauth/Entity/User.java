package org.authorization.oauth.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

@Column(unique = true)
    private String userName ;


    private String password;

    private String email;
    private Long phoneNumber;
    private String address;

    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(
            name="user_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            }
    )
    private List<Role> roles;

    @Transient
    private List<String> roleNames;

    @Override
    public String toString() {

        return "" ;
    }

}
