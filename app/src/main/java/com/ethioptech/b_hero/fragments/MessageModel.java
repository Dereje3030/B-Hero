package com.ethioptech.b_hero.fragments;

public class MessageModel {
    private String imagePath;
    private String Description;
    public MessageModel(String imagePath, String description) {
        this.imagePath = imagePath;
        Description = description;
    }

    public MessageModel() {
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDescription() {
        return Description;
    }
}
