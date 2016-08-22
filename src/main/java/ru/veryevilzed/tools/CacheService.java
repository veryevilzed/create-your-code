package ru.veryevilzed.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by zed on 22.08.16.
 */
@Service
@Slf4j
public class CacheService {

    @CachePut(cacheNames={"cache-service"}, key = "#p0")
    public <T> T save(String key, T value) {
        return value;
    }

    @Cacheable(cacheNames={"cache-service"}, key = "#p0")
    public <T> T get(String key) {
        return null;
    }

    @CacheEvict(cacheNames={"cache-service"}, key = "#p0")
    public void delete(String key) {
    }

    @PostConstruct
    public void postConstruct() {
        log.info("Create CacheService");
    }
}
