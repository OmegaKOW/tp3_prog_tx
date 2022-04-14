package com.example.tp3.models.users;

import com.example.tp3.models.library.Emprunt;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employe {


    @Id
    @GeneratedValue
    private int id;

    private String user;

    private String password;

    Role role;



    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void viewUserList(Client c){
        if(isAdmin()){
            for(Emprunt emprunt : c.getEmprunts()){
                System.out.println(emprunt.toString());
            }
        }
    }

    private boolean isAdmin(){
        return this.getRole() == Role.ADMIN;
    }
}
