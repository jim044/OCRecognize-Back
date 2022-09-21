package com.ocrecognize.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private RoleDto roleDto;
}
