package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.dto.UserTechnologyDto;
import com.labwithcommunity.domain.user.enums.ProgrammingLanguage;
import com.labwithcommunity.domain.user.enums.TechnologiesForProgrammingLanguage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
@RequiredArgsConstructor
@Slf4j
class TechnologyRegistryService {

    private final UserFinder userService;

    @Transactional
    public void removeTechnologyFromUser(String username, ProgrammingLanguage programmingLanguage,
                                         Set<TechnologiesForProgrammingLanguage> technologyToRemove) {
        UserEntity user = userService.getUserEntity(username);
        user.removeTechnologyFromUser(programmingLanguage, technologyToRemove);
        log.info("Technology deleted for user: {}", user.getId());
    }

    @Transactional
    public UserResponseDto updateTechnologies(Set<UserTechnologyDto> userTechnologyDto, String username) {
        UserEntity user = userService.getUserEntity(username);
        Set<Technologies> existingTechnologies = user.getTechnologies();
        for (UserTechnologyDto technologyDto : userTechnologyDto) {
            Technologies newTechnology = new Technologies(
                    technologyDto.getProgrammingLanguage(),
                    technologyDto.getUserTechnologyForProgrammingLanguages()
            );
            boolean technologyExists = existingTechnologies.stream()
                    .anyMatch(existingTech -> existingTech.getProgrammingLanguage()
                            .equals(newTechnology.getProgrammingLanguage()));

            if (technologyExists) {
                existingTechnologies.stream()
                        .filter(existingTech ->
                                existingTech.getProgrammingLanguage()
                                        .equals(newTechnology.getProgrammingLanguage()))
                        .findFirst()
                        .ifPresent(existingTech ->
                                existingTech.getUserTechnologyForProgrammingLanguages()
                                        .addAll(newTechnology.getUserTechnologyForProgrammingLanguages()));
            } else {
                existingTechnologies.add(newTechnology);
            }
        }
        user.setTechnologies(existingTechnologies);
        return UserMapper.mapToUserResponseDto(user);
    }
}
