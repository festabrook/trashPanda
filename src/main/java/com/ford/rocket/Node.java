package com.ford.rocket;

public class Node {

    private String name;
    private String current;
    private String desired;

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrent(){
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getDesired(){
        return desired;
    }

    public void setDesired(String desired) {
        this.desired = desired;
    }
}
