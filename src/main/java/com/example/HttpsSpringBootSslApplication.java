package com.example;

import com.example.model.role.Role;
import com.example.model.role.RoleName;
import com.example.model.user.User;
import com.example.model.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@SpringBootApplication
public class HttpsSpringBootSslApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpsSpringBootSslApplication.class, args);
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
