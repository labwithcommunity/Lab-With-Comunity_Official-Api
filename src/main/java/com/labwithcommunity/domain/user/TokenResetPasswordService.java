package com.labwithcommunity.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TokenResetPasswordService {

    @CachePut(value = "passwordResetTokens", key = "#email")
    public String createPasswordResetToken(String email) {
        String token = generateToken();
        log.info("Creating password reset token for email: {}", email);
        return token;
    }

    @Cacheable(value = "passwordResetTokens", key = "#email")
    public String getPasswordResetToken(String email) {
        log.info("Retrieving password reset token for email: {}", email);
        return null;
    }

    @CacheEvict(value = "passwordResetTokens", key = "#email")
    public void invalidatePasswordResetToken(String email) {
        log.info("Token for email {} has been invalidated", email);
    }

    private String generateToken() {
        return java.util.UUID.randomUUID().toString();
    }
}
