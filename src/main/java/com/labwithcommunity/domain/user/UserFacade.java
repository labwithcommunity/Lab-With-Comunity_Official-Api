package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.*;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import com.labwithcommunity.domain.user.enums.ProgrammingLanguage;
import com.labwithcommunity.domain.user.enums.TechnologiesForProgrammingLanguage;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class UserFacade {

    private final UserRegistration userRegistration;
    private final UserFinder userFinder;
    private final TechnologyRegistryService technologyRegistryService;

    public UserResponseDto findUserByUsername(String username) {
        return userFinder.findUserByUsername(username);
    }

    public UserCreateResponseDto registerUser(UserCreateDto userCreateDto) {
        return userRegistration.register(userCreateDto);
    }

    public GetLoggedUserDto getLoggedUser(String username) {
        return userFinder.getLoggedUser(username);
    }

//    public boolean addRolesToUser(Set<UserMemberRoles> userMemberRoles, String username){
//        return userRegistration.addRoleToUser(userMemberRoles, username);
//    }

    public UserResponseDto updateTechnologyOfUser(Set<UserTechnologyDto> userTechnologyDto, String username){
        return technologyRegistryService.updateTechnologies(userTechnologyDto, username);
    }

    public void deleteTechnologiesOfUser(String username, ProgrammingLanguage programmingLanguage,
                                         Set<TechnologiesForProgrammingLanguage> technologyToRemove) {
        technologyRegistryService.removeTechnologyFromUser(username, programmingLanguage, technologyToRemove);
    }

    public UserQueryDto getQueryUser(String username) {
        return userFinder.getUserQuery(username);
    }
}
