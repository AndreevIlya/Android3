package com.example.android3.data.models.sugar;

import com.example.android3.data.entities.User;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

@Table
public class UserInSugar extends SugarRecord {
    private String name;
    private int id;
    private String url;

    public UserInSugar(){}

    public UserInSugar(String name, int id, String url) {
        this.name = name;
        this.id = id;
        this.url = url;
    }

    public UserInSugar(User user){
        name = user.getName();
        id = Integer.parseInt(user.getId());
        url = user.getUrl();
    }

    public User convertToEntity(){
        return new User(name,Integer.toString(id),url);
    }
}
