package com.labwithcommunity.infrastructure.user.controller;

import com.labwithcommunity.domain.user.UserFacade;
import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserCreateResponseDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.enums.UserMemberRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserFacade userFacade;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserCreateResponseDto> addUser(@RequestBody UserCreateDto userCreateDto) {
        UserCreateResponseDto userCreateResponseDto = userFacade.registerUser(userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreateResponseDto);
    }

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserByNickname(@RequestParam String nickname) {
        UserResponseDto userResponseDto = userFacade.findUserByNickname(nickname);
        return ResponseEntity.ok(userResponseDto);
    }

    @PutMapping("/role")
    public ResponseEntity<Boolean> addRoleToUser(@RequestParam String nickname, @RequestBody Set<UserMemberRoles> role) {
        boolean isAdded = userFacade.addRolesToUser(role, nickname);
        return ResponseEntity.ok(isAdded);
    }
}
