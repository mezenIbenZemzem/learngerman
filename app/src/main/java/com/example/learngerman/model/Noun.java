package com.example.learngerman.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Noun implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("meaning")
    @Expose
    private String meaning;
    @SerializedName("perfect")
    @Expose
    private String perfect;
    @SerializedName("praeteritum")
    @Expose
    private String praeteritum;
    @SerializedName("article")
    @Expose
    private String article;
    @SerializedName("plural")
    @Expose
    private String plural;
    @SerializedName("comparative")
    @Expose
    private String comparative;
    @SerializedName("superlative")
    @Expose
    private String superlative;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("meaning_learned")
    @Expose
    private Boolean meaningLearned;
    @SerializedName("meanings_accepted")
    @Expose
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

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getPlural() {
        return plural;
    }

    public void setPlural(String plural) {
        this.plural = plural;
    }

    public String getComparative() {
        return comparative;
    }

    public void setComparative(String comparative) {
        this.comparative = comparative;
    }

    public String getSuperlative() {
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

    @Override
    public String toString() {
        return "Noun{" +
                "id=" + id +
                ", base='" + base + '\'' +
                ", meaning='" + meaning + '\'' +
                ", perfect='" + perfect + '\'' +
                ", praeteritum='" + praeteritum + '\'' +
                ", article='" + article + '\'' +
                ", plural='" + plural + '\'' +
                ", comparative='" + comparative + '\'' +
                ", superlative='" + superlative + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", meaningLearned=" + meaningLearned +
                ", meaningsAccepted='" + meaningsAccepted + '\'' +
                '}';
    }
}
