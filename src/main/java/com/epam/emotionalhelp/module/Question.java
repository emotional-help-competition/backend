package com.epam.emotionalhelp.module;

public class Question {
    private Integer id;
    private String description;
    private Integer rate;

    public Question(Integer id, String description, Integer rate) {
        this.id = id;
        this.description = description;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
