package com.labwithcommunity.domain.user.dto;

import com.labwithcommunity.domain.user.Technologies;

import java.util.Set;

public record GetLoggedUserDto(
        String username,
        String password
){
}
