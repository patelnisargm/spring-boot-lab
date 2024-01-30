package com.nmp.springmvcdemo.dto;

import com.nmp.springmvcdemo.validation.Score;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class Grade {
    @NotBlank(message = "Name can not be blank.")
    private String name;
    @NotBlank(message = "Name can not be blank.")
    private String subject;
    @Score(message = "scroe must be a letter grade.")
    private String score;
    private String id;

    public Grade() {
        this.id = UUID.randomUUID().toString();
    }

    public Grade(String name, String subject, String score) {
        this();
        this.name = name;
        this.subject = subject;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

}
