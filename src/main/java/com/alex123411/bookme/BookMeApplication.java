package com.alex123411.bookme;

import com.alex123411.bookme.auth.AuthenticationService;
import com.alex123411.bookme.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.alex123411.bookme.user.Role.ADMIN;
import static com.alex123411.bookme.user.Role.MANAGER;

import java.util.Arrays;

//@SpringBootApplication(
//        scanBasePackageClasses = {
//        // List of packages outside the "bookme" package, for example "com.alex123411.utils"
//        }
//)
@SpringBootApplication
public class BookMeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookMeApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("admin@mail.com")
                    .password("password")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getAccessToken());

            var manager = RegisterRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("manager@mail.com")
                    .password("password")
                    .role(MANAGER)
                    .build();
            System.out.println("Manager token: " + service.register(manager).getAccessToken());

        };
    }

}
