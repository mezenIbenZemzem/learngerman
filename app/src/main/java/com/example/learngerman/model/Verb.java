package com.example.learngerman.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Verb {

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

