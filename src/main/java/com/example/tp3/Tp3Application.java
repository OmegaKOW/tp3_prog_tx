package com.example.tp3;

import com.example.tp3.models.library.Emprunt;
import com.example.tp3.models.library.Livre;
import com.example.tp3.models.users.Client;
import com.example.tp3.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tp3Application implements CommandLineRunner {

    @Autowired
    private LibraryService libraryService;

    public static void main(String[] args) {
        SpringApplication.run(Tp3Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception{
        long c1Id = libraryService.saveClient("Thomas", "104 rue Laforet").getClientID();
        long l1Id = libraryService.saveLivre("Les chats", "Marcel", "Chatown", 3, 1990, 134, "Étude").getDocumentID();


        System.out.println(libraryService.findByIdWithAll(c1Id));
        System.out.println(libraryService.findDocumentWithId(l1Id).get());

        System.out.println(libraryService.getEmprunts(c1Id));

        System.out.println(libraryService.findByIdWithAll(c1Id));

    }
}
