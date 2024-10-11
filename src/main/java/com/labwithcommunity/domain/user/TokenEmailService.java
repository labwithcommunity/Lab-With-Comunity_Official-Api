package com.labwithcommunity.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TokenEmailService {

    @CachePut(value = "registerTokens", key = "#email")
    public String createRegisterToken(String email) {
        String token = generateToken();
        log.info("Creating register token for email: {}", email);
        return token;
    }

    @Cacheable(value = "registerTokens", key = "#email")
    public String getRegisterToken(String email) {
        log.info("Retrieving register token for email: {}", email);
        return null;
    }

    @CacheEvict(value = "registerTokens", key = "#email")
    public void invalidateRegisterToken(String email) {
        log.info("Token for email {} has been invalidated", email);
    }

    private String generateToken() {
        return java.util.UUID.randomUUID().toString();
    }
}
