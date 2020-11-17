package ru.ya.spingmvc.dao;

import org.springframework.stereotype.Component;
import ru.ya.spingmvc.models.News;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsDao {
    private List<News> wow;
    private int WOW_COUNT;

    {
        wow = new ArrayList<>();
        wow.add(new News(++WOW_COUNT,"HelloWorld!","Site under construction!"));
        wow.add(new News(++WOW_COUNT,"Wow!","Design too under construction!"));
    }

    public List<News> index(){
        return wow;
    }

    public News show(int id){
        return wow.stream().filter(wow->wow.getId()==id).findAny().orElse(null);
    }

    public News edit(int id){
        News news = wow.stream().filter(wow->wow.getId()==id).findAny().orElse(null);
        if (news == null) return null;
        News newNews = new News(news.getId(),news.getName(),news.getBody().replace("<br>","\n"));
        return newNews;
    }

    public void save(News news) {
        news.setId(++WOW_COUNT);
        wow.add(news);
    }

    public void update(int id, News updatedPerson) {
        News personToBeUpdated = show(id);
        if (updatedPerson == null) return;
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setBody(updatedPerson.getBody().replace("\n","<br>"));
    }

    public void delete(int id) {
        wow.removeIf(p -> p.getId() == id);
    }
}