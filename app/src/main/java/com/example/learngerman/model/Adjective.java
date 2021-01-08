package com.example.learngerman.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Adjective implements Serializable {
    private int id;
    private String base;
    private String meaning;
    private String perfect;
    private String praeteritum;
    private String article;
    private String plural;
    private String comparative;
    private String superlative;
    private String createdAt;
    private String updatedAt;
    private Boolean meaningLearned = false;
    private String meaningsAccepted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getPerfect() {
        return perfect;
    }

    public void setPerfect(String perfect) {
        this.perfect = perfect;
    }

    public String getPraeteritum() {
        return praeteritum;
    }

    public void setPraeteritum(String praeteritum) {
        this.praeteritum = praeteritum;
    }

    public Object getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Object getPlural() {
        return plural;
    }

    public void setPlural(String plural) {
        this.plural = plural;
    }

    public Object getComparative() {
        return comparative;
    }

    public void setComparative(String comparative) {
        this.comparative = comparative;
    }

    public Object getSuperlative() {
        return superlative;
    }

    public void setSuperlative(String superlative) {
        this.superlative = superlative;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getMeaningLearned() {
        return meaningLearned;
    }

    public void setMeaningLearned(Boolean meaningLearned) {
        this.meaningLearned = meaningLearned;
    }

    public String getMeaningsAccepted() {
        return meaningsAccepted;
    }

    public void setMeaningsAccepted(String meaningsAccepted) {
        this.meaningsAccepted = meaningsAccepted;
    }


}
