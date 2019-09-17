package com.example.android3.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<String> content = new ArrayList<>();

    public List<String> getAll() {
        return content;
    }

    public List<String> getFromIndex(int index){
        if (index > content.size())return content.subList(content.size() - index - 1,content.size() - 1);
        return content;
    }

    public void addToContent(String str) {
        this.content.add(str);
    }
}
