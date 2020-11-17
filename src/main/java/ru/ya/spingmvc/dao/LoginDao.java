package ru.ya.spingmvc.dao;

import org.springframework.stereotype.Component;
import ru.ya.spingmvc.models.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginDao {
    int USER_ID;
    List<User> users = new ArrayList<>();

    public LoginDao() {
        users.add(new User(++USER_ID,"Cyainide","Cyanide3D","123qwe123"));
    }

    public List<User> list(){
        return users;
    }
}
