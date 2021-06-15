package com.example.demo2.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlexDetail {

    private int id;
    private String image;
    private String title;
    private ArrayList<Object> ingredients;
    private ArrayList<Object> steps;
    private String summary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Object> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Object> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Object> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Object> steps) {
        this.steps = steps;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
