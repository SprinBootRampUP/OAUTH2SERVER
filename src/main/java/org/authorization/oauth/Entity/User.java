package org.authorization.oauth.Entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @NotEmpty(message = "Username is required")
    @Column(unique = true)
    private String userName ;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    private Long phoneNumber;

    @NotEmpty(message = "Address is required")
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


    @Override
    public String toString() {

        return "" ;
    }

}
