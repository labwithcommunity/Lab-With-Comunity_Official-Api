package com.labwithcommunity.domain.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRoles {

    ADMIN("admin"), MEMBER("member"), USER("user");
    private final String role;
}
