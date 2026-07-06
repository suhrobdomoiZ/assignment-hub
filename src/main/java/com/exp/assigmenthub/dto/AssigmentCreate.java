package com.exp.assigmenthub.dto;

import com.exp.assigmenthub.model.Difficulty;

public class AssigmentCreate {
    private String title;

    private Difficulty difficulty;
    private Integer maxScore;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }
}
