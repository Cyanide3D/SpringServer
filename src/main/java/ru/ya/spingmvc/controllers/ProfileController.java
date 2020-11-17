package ru.ya.spingmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ya.spingmvc.dao.NewsDao;
import ru.ya.spingmvc.services.LoginService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final LoginService loginService;
    private final NewsDao newsDao;

    @Autowired
    public ProfileController(LoginService loginService, NewsDao newsDao) {
        this.loginService = loginService;
        this.newsDao = newsDao;
    }

    @GetMapping("/{id}")
    public String index(@PathVariable("id") int id, Model model, @CookieValue(value = "_tmp", defaultValue = "0") String cookie) {
        model.addAttribute("user", loginService.getUser(id));
        return loginService.searchId(Integer.parseInt(cookie)) ? "profile/profile" : "redirect:/";
    }
}
