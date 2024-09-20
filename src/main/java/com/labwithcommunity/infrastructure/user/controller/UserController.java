package com.labwithcommunity.infrastructure.user.controller;

import com.labwithcommunity.domain.user.UserFacade;
import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserCreateResponseDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.enums.UserRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserByNickname(@RequestParam String nickname) {
        UserResponseDto userResponseDto = userFacade.findUserByNickname(nickname);
        return ResponseEntity.ok(userResponseDto);
    }

    @PutMapping
    public ResponseEntity<Boolean> addRoleToUser(@RequestParam String nickname, @RequestBody Set<UserRoles> role) {
        boolean isAdded = userFacade.addRolesToUser(role, nickname);
        return ResponseEntity.ok(isAdded);
    }
}
