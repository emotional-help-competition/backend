package com.epam.emotionalhelp.model;

public enum EmotionCategory {
    ANGER("anger"),
    FEAR("fear"),
    SADNESS("sadness"),
    LOVE("love"),
    SURPRISE("surprise"),
    JOY("joy"),
    ACCEPTED("accepted"),
    AFRAID("afraid"),
    SCARED("scared");

    private final String name;

    EmotionCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
