package com.cybercity.application.dtos;

import com.cybercity.application.entities.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String userName;
    private String email;
    private String phone;
    private String password;
    private Set<UserType> userType;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
