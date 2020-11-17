package ru.ya.spingmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ya.spingmvc.dao.NewsDao;
import ru.ya.spingmvc.models.News;
import ru.ya.spingmvc.services.LoginService;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final LoginService loginService;
    private final NewsDao newsDao;

    @Autowired
    public NewsController(LoginService loginService, NewsDao newsDao) {
        this.loginService = loginService;
        this.newsDao = newsDao;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @CookieValue(value = "_tmp", defaultValue = "0") String userId) {
        model.addAttribute("news", newsDao.show(id));
        if (!loginService.searchId(Integer.parseInt(userId))) return "news/show";
        model.addAttribute("user", loginService.getUser(Integer.parseInt(userId)));
        return "news/authShow";
    }

    @GetMapping("/new")
    public String newWow(Model model, @CookieValue(value = "_tmp", defaultValue = "0") String id) {
        if (!loginService.searchId(Integer.parseInt(id))) return "redirect:/";
        model.addAttribute("user", loginService.getUser(Integer.parseInt(id)));
        model.addAttribute("news", new News());
        return "news/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("news") News news) {
        newsDao.save(news);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id, @CookieValue(value = "_tmp", defaultValue ="0") String userId) {
        if (!loginService.searchId(Integer.parseInt(userId))) return "redirect:/";
        model.addAttribute("user", loginService.getUser(Integer.parseInt(userId)));
        model.addAttribute("news", newsDao.edit(id));
        return "news/edit";
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH,RequestMethod.POST})
    public String update(@ModelAttribute("news") News news, @PathVariable("id") int id) {
        newsDao.update(id, news);
        return "redirect:/news/" + String.valueOf(id);
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        newsDao.delete(id);
        return "redirect:/";
    }
}