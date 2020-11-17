package ru.ya.spingmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ya.spingmvc.dao.NewsDao;
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
    public String index(Model model, @CookieValue(value = "_tmp", required = false) String cookie) {
        String view;
        if (cookie != null){
            view = loginService.searchId(Integer.parseInt(cookie)) ? "/authIndex" : "/index";
        } else {
            view = "/index";
        }
        model.addAttribute("news", newsDao.index());
        return view;
    }
}