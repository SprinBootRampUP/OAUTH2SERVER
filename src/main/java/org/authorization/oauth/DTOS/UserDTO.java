package org.authorization.oauth.DTOS;

import lombok.Data;

@Data
public class UserDTO {

    private String userName ;


    private String password;


    private String email;

    private Long phoneNumber;

    private String address;
}
