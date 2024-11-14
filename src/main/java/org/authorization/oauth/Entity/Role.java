package org.authorization.oauth.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Role {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private  RoleEnum name;


@ManyToMany(mappedBy = "roles" ,cascade = CascadeType.PERSIST)
    private List<User> users;



}
