package com.example.android3.data.database;

import com.example.android3.data.entities.User;
import com.example.android3.data.models.sugar.UserInSugar;

import java.util.List;

public class UserSugarImpl implements DBProvider<User, User> {
    @Override
    public void insert(User data) {
        List<UserInSugar> lUis = UserInSugar.find(UserInSugar.class,"name = ?", data.getName());
        if(lUis.size() != 0) {
            UserInSugar uis = new UserInSugar(data);
            uis.save();
        }
    }

    @Override
    public void update(User data) {
        List<UserInSugar> lUis = UserInSugar.find(UserInSugar.class, "name = ?", data.getName());
        if (lUis.size() == 0) {
            UserInSugar uis = new UserInSugar(data);
            uis.save();
        } else if (lUis.size() == 1){
            UserInSugar uis = new UserInSugar(data);
            uis.save();
        }
        else {
            throw new RuntimeException("Multiple items in the user sugar db with matching names: " + data.getName() + ".");
        }
    }

    @Override
    public void delete(User data) {
        List<UserInSugar> lRis = UserInSugar.find(UserInSugar.class, "name = ?", data.getName());
        for (UserInSugar uis : lRis) uis.delete();
    }

    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public User selectByName(String name) {
        return null;
    }
}
