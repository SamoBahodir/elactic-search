package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;
@Slf4j
@SpringBootApplication
@EnableConfigurationProperties
public class SwaggerApplication {

    public static void main(String[] args) {
        var run = SpringApplication.run(SwaggerApplication.class, args);
        initApplication(run.getEnvironment());    }
    private static void initApplication(Environment env) {
        var serverPort = Optional.ofNullable(env.getProperty("server.port")).orElse("8080");
        var hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info(
                env.getProperty("spring.application.name"),
                serverPort,
                hostAddress,
                serverPort,
                env.getActiveProfiles()
        );
    }
//    @Bean
//    CommandLineRunner run(UserService userService) {
//        return args -> {
//            userService.saveRole(new Role(1l, RoleName.ROLE_USER));
//            userService.saveRole(new Role(2l, RoleName.ROLE_MANAGER));
//            userService.saveRole(new Role(3l, RoleName.ROLE_ADMIN));
//            userService.saveRole(new Role(4l, RoleName.ROLE_SUPER_ADMIN));
//
//            userService.saveUser(new User(1l, "bahodir", "bahodir", "1995", new HashSet<>()));
//            userService.saveUser(new User(2l, "java", "java", "1996", new HashSet<>()));
//            userService.saveUser(new User(3l, "samo", "samo", "1997", new HashSet<>()));
//            userService.saveUser(new User(4l, "javohir", "javohir", "1999", new HashSet<>()));
//
//            userService.addRoleToUser("bahodir", RoleName.ROLE_USER);
//            userService.addRoleToUser("bahodir", RoleName.ROLE_MANAGER);
//            userService.addRoleToUser("java", RoleName.ROLE_MANAGER);
//            userService.addRoleToUser("samo", RoleName.ROLE_ADMIN);
//            userService.addRoleToUser("javohir", RoleName.ROLE_SUPER_ADMIN);
//            userService.addRoleToUser("javohir", RoleName.ROLE_ADMIN);
//            userService.addRoleToUser("javohir", RoleName.ROLE_USER);
//        };
//    }
}