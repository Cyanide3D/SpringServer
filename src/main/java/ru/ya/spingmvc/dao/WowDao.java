package ru.ya.spingmvc.dao;

import org.springframework.stereotype.Component;
import ru.ya.spingmvc.models.WowModel;

import java.util.ArrayList;
import java.util.List;

@Component
public class WowDao {
    private List<WowModel> wow;
    private int WOW_COUNT;

    {
        wow = new ArrayList<>();
        wow.add(new WowModel(++WOW_COUNT,"adin"));
        wow.add(new WowModel(++WOW_COUNT,"dva"));
        wow.add(new WowModel(++WOW_COUNT,"tryi"));
    }

    public List<WowModel> index(){
        return wow;
    }

    public WowModel show(int id){
        return wow.stream().filter(wow->wow.getId()==id).findAny().orElse(null);
    }

    public void save(WowModel wowModel) {
        wowModel.setId(++WOW_COUNT);
        wow.add(wowModel);
    }

    public void update(int id, WowModel updatedPerson) {
        WowModel personToBeUpdated = show(id);

        personToBeUpdated.setName(updatedPerson.getName());
    }

    public void delete(int id) {
        wow.removeIf(p -> p.getId() == id);
    }
}