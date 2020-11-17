package ru.ya.spingmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ya.spingmvc.dao.LoginDao;
import ru.ya.spingmvc.dao.NewsDao;
import ru.ya.spingmvc.models.User;
import ru.ya.spingmvc.services.LoginService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;
    private final LoginDao loginDao;

    @Autowired
    public LoginController(LoginService loginService, LoginDao loginDao) {
        this.loginService = loginService;
        this.loginDao = loginDao;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "login/form";
    }

    @PostMapping("/")
    public String authentication(@ModelAttribute("user") User user, HttpServletResponse response) {
        String view = null;
        if (loginService.authentication(user)) {
            Cookie cookie = new Cookie("_tmp", String.valueOf(loginService.getUser(user.getLogin())));
            cookie.setMaxAge(30 * 60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
            view = "redirect:/";
        } else {
            view = "login/dis";
        }
        return view;
    }

    @GetMapping("/logout")
    public String logout(@ModelAttribute("user") User user, HttpServletResponse response) {
        Cookie cookie = new Cookie("_tmp", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/";
    }
}