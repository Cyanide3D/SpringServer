package ru.ya.spingmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ya.spingmvc.dao.NewsDao;
import ru.ya.spingmvc.models.News;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final NewsDao newsDao;

    @Autowired
    public NewsController(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("news", newsDao.show(id));
        return "news/show";
    }

    @GetMapping("/new")
    public String newWow(Model model) {
        model.addAttribute("news", new News());
        return "news/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("news") News news) {
        newsDao.save(news);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
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