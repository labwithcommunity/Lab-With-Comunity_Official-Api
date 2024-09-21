package com.labwithcommunity.infrastructure.user.controller;

import com.labwithcommunity.domain.user.UserFacade;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.enums.UserMemberRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserFacade userFacade;

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserByUsername(@RequestParam String username) {
        UserResponseDto userResponseDto = userFacade.findUserByUsername(username);
        return ResponseEntity.ok(userResponseDto);
    }

    @PutMapping("/role")
    public ResponseEntity<Boolean> addRoleToUser(@RequestParam String username, @RequestBody Set<UserMemberRoles> role) {
        boolean isAdded = userFacade.addRolesToUser(role, username);
        return ResponseEntity.ok(isAdded);
    }
}
