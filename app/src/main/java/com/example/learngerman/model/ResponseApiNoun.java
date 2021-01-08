package com.example.learngerman.model;

import com.google.gson.annotations.SerializedName;

public class ResponseApiNoun {

    @SerializedName("noun")
    private Noun noun;

    public Noun getNoun() {
        return noun;
    }

    public void setNoun(Noun noun) {
        this.noun = noun;
    }
}
