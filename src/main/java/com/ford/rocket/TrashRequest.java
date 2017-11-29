package com.ford.rocket;

import java.util.ArrayList;

public class TrashRequest {

    private ArrayList<Node> nodes = new ArrayList<>();
    private ArrayList<Food> foods = new ArrayList<>();

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }
}
