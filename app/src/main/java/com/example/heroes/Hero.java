package com.example.heroes;

public class Hero {
    private String name;
    private String description;
    private String superpower;
    private String ranking;
    private String image;

    public Hero(String name, String description, String superpower, String ranking, String image) {
        this.name = name;
        this.description = description;
        this.superpower = superpower;
        this.ranking = ranking;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuperpower() {
        return superpower;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return name + '\n' + description + '\n' + superpower + '\n' + ranking + '\n';
    }
}
