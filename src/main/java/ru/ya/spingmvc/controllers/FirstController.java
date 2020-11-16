package ru.ya.spingmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ya.spingmvc.dao.WowDao;
import ru.ya.spingmvc.models.News;

@Controller
public class FirstController {

    private final WowDao newsDao;

    @Autowired
    public FirstController(WowDao newsDao) {
        this.newsDao = newsDao;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("news",newsDao.index());
        return "/index";
    }

//    @GetMapping("/news/shownews")
//    public String shownews() {
//
//        return "news/shownews";
//    }

    @GetMapping("/news/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("news", newsDao.show(id));
        return "news/show";
    }

    @GetMapping("/news/new")
    public String newWow(Model model) {
        model.addAttribute("news", new News());
        return "news/new";
    }

    @PostMapping("/news")
    public String create(@ModelAttribute("news") News news) {
        newsDao.save(news);
        return "redirect:/";
    }

    @GetMapping("/news/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("news", newsDao.edit(id));
        return "news/edit";
    }

    @RequestMapping(value = "/news/{id}", method = {RequestMethod.PATCH,RequestMethod.POST})
    public String update(@ModelAttribute("news") News news, @PathVariable("id") int id) {
        newsDao.update(id, news);
        return "redirect:/news/" + String.valueOf(id);
    }

    @RequestMapping(value = "/news/{id}/", method = {RequestMethod.DELETE,RequestMethod.POST})
    public String delete(@PathVariable("id") int id) {
        newsDao.delete(id);
        return "redirect:/";
    }
}