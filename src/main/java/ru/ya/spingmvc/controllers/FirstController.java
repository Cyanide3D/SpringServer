package ru.ya.spingmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ya.spingmvc.dao.WowDao;
import ru.ya.spingmvc.models.WowModel;

@Controller
@RequestMapping("/wow")
public class FirstController {

    private final WowDao wowDao;

    @Autowired
    public FirstController(WowDao wowDao) {
        this.wowDao = wowDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("wow", wowDao.index());
        return "wow/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("wow", wowDao.show(id));
        return "wow/show";
    }

    @GetMapping("/new")
    public String newWow(Model model) {
        model.addAttribute("wow", new WowModel());
        return "wow/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("wow") WowModel wowModel) {
        wowDao.save(wowModel);
        return "redirect:/wow";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("wow", wowDao.show(id));
        return "wow/edit";
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.POST})
    public String update(@ModelAttribute("wow") WowModel wowModel, @PathVariable("id") int id) {
        wowDao.update(id, wowModel);
        return "redirect:/wow";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        wowDao.delete(id);
        return "redirect:/wow";
    }
}