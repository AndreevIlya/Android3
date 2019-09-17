package com.example.android3.Model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<String> content = new ArrayList<>();

    public List<String> getAll() {
        return content;
    }

    public String getByIndex(int index){
        return content.get(index);
    }

    public void addToContent(String str) {
        this.content.add(str);
    }
}
