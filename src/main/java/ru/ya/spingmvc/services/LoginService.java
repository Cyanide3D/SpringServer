package ru.ya.spingmvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ya.spingmvc.dao.LoginDao;
import ru.ya.spingmvc.models.User;

@Service
public class LoginService {

    @Autowired
    LoginDao loginDao;

    public boolean authentication(User user) {
        User authUser = loginDao.list().stream().filter(user1 ->
                user1.getLogin().equalsIgnoreCase(user.getLogin()) && user1.getPassword().equalsIgnoreCase(user.getPassword())).findAny().orElse(null);
        return authUser == null ? false : true;
    }

    public int getUser(String login) {
        User authUser = loginDao.list().stream().filter(user1 -> user1.getLogin().equalsIgnoreCase(login)).findAny().orElse(null);
        return authUser.getId();
    }

    public boolean searchId(int id){
        User authUser = loginDao.list().stream().filter(user1 -> user1.getId() == id).findAny().orElse(null);
        return authUser == null ? false : true;
    }
}
