package ru.veryevilzed.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

/**
 * Created by zed on 21.08.16.
 */
@SpringBootApplication
@EnableCaching
@EnableSpringHttpSession
public class Application {

    public static void main(final String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
