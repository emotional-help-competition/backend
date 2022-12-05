package com.epam.emotionalhelp.model;


public enum EmotionCategory {
    ANGER("Anger"),
    FEAR("Fear"),
    SADNESS("sadness"),
    JOY("Joy");


    private final String name;

    EmotionCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
