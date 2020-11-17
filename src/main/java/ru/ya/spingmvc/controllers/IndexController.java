package ru.ya.spingmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ya.spingmvc.dao.WowDao;

@Controller
public class IndexController {
    private final WowDao newsDao;

    @Autowired
    public IndexController(WowDao newsDao) {
        this.newsDao = newsDao;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("news",newsDao.index());
        return "/index";
    }
}
