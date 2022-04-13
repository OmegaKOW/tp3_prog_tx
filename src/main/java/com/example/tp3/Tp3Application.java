package com.example.tp3;

import com.example.tp3.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tp3Application {

    private LibraryService libraryService;

    public static void main(String[] args) {
        SpringApplication.run(Tp3Application.class, args);
    }

}
