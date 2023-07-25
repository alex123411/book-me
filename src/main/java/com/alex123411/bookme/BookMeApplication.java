package com.alex123411.bookme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
