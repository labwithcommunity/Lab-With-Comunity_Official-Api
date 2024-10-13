package com.labwithcommunity.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class TokenEmailService {

    private final CacheManager cacheManager;

    public String createRegisterToken(String email) {
        String token = Long.toHexString(System.currentTimeMillis());
        Cache cache = cacheManager.getCache("registerTokens");
        if (cache != null) {
            cache.put(token, email);
        }
        log.info("Generated token for email {}", email);
        return token;
    }

    @Cacheable(value = "registerTokens", key = "#token")
    public String getEmailByToken(String token) {
        log.info("Retrieving email for token {}", token);
        Cache cache = cacheManager.getCache("registerTokens");
        if (cache != null) {
            Cache.ValueWrapper valueWrapper = cache.get(token);
            return valueWrapper != null ? (String) valueWrapper.get() : null;
        }
        return null;
    }

    public void invalidateRegisterToken(String email) {
        log.info("Invalidating token for email {}", email);
        Cache cache = cacheManager.getCache("registerTokens");
        if (cache!= null) {
            cache.evict(email);
        }
    }
}
