package com.labwithcommunity.infrastructure.user.controller;

import com.labwithcommunity.domain.user.UserFacade;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.dto.UserTechnologyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
class UserController {

    private final UserFacade userFacade;

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserByUsername(@RequestParam String username) {
        UserResponseDto userResponseDto = userFacade.findUserByUsername(username);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/get-current-user")
    public ResponseEntity<UserResponseDto> getCurrentUser(@AuthenticationPrincipal UserDetails principal) {
        String username = principal.getUsername();
        UserResponseDto userResponseDto = userFacade.findUserByUsername(username);
        return ResponseEntity.ok(userResponseDto);
    }

    @PutMapping("/technologies")
    public ResponseEntity<UserResponseDto> updateTechnologies(
            @AuthenticationPrincipal UserDetails principal,
            @RequestBody Set<UserTechnologyDto> userTechnologyDto) {
        String username = principal.getUsername();
        UserResponseDto updatedUserResponseDto = userFacade.updateTechnologyOfUser(userTechnologyDto, username);
        return ResponseEntity.ok(updatedUserResponseDto);
    }

//    @PutMapping("/role")
//    public ResponseEntity<Boolean> addRoleToUser(@RequestParam String username, @RequestBody Set<UserMemberRoles> role) {
//        boolean isAdded = userFacade.addRolesToUser(role, username);
//        return ResponseEntity.ok(isAdded);
//    }
}
