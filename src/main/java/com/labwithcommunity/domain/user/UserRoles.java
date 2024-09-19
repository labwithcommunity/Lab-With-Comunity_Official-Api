package com.labwithcommunity.domain.user;

public enum UserRoles {

    ADMIN("admin"), MEMBER("member"), USER("user");
    final String role;

    UserRoles(String roles) {
        this.role = roles;
    }
}
