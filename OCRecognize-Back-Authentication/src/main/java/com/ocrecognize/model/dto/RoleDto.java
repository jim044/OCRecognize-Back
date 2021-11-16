package com.ocrecognize.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private Long id;

    private String roleName;

    private List<UserDto> userDtoList;
}
