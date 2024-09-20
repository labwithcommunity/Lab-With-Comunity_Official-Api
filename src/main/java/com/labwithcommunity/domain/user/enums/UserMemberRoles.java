package com.labwithcommunity.domain.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserMemberRoles {

    ADMIN("admin"), MEMBER("member"), USER("user");
    private final String role;
}
