package ru.veryevilzed.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.session.MapSession;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * Created by zed on 22.08.16.
 */
@Slf4j
@Repository
@Scope("singleton")
public class CacheSessionRepository implements SessionRepository {

    @Autowired
    CacheService cache;


    @Override
    public Session createSession() {
        Session session = new MapSession();
        log.debug("Create new Session:{}", session.getId());
        return session;
    }

    @Override
    public void save(Session session) {
        log.debug("Save session:{}", session.getId());
        cache.save(session.getId(), session);
    }

    @Override
    public Session getSession(String s) {
        return cache.get(s);
    }

    @Override
    public void delete(String s) {
        log.debug("Delete session:{}", s);
        cache.delete(s);
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("Create CacheSessionRepository");
    }
}
