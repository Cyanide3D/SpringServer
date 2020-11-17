package ru.ya.spingmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ya.spingmvc.dao.NewsDao;
import ru.ya.spingmvc.models.User;
import ru.ya.spingmvc.services.LoginService;

@Controller
public class IndexController {
    private final LoginService loginService;
    private final NewsDao newsDao;

    @Autowired
    public IndexController(LoginService loginService, NewsDao newsDao) {
        this.loginService = loginService;
        this.newsDao = newsDao;
    }

    @GetMapping("/")
    public String index(Model model, @CookieValue(value = "_tmp", defaultValue = "0") String cookie) {
        String view;
            if (loginService.searchId(Integer.parseInt(cookie))) {
                view = "/authIndex";
                model.addAttribute("user", loginService.getUser(Integer.parseInt(cookie)));
            } else {
                view = "/index";
            }
        model.addAttribute("news", newsDao.index());
        return view;
    }
}