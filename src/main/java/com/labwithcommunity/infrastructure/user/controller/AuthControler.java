package com.labwithcommunity.infrastructure.user.controller;

import com.labwithcommunity.domain.user.UserFacade;
import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserCreateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/public")
@RequiredArgsConstructor
public class AuthControler {

    private final UserFacade userFacade;

    @PostMapping("/register")
    public ResponseEntity<UserCreateResponseDto> addUser(@RequestBody UserCreateDto userCreateDto) {
        UserCreateResponseDto userCreateResponseDto = userFacade.registerUser(userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreateResponseDto);
    }

    // TODO: implement password reset feature
//    @PostMapping("/request-change-password")
//    }


    // TODO: implement password change feature
//    @PostMapping("/change-password")
//    }

}
