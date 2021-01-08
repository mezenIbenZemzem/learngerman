package com.example.learngerman.model;

import com.google.gson.annotations.SerializedName;

public class ResponseApiAdjective {

    @SerializedName("adjective")
    private Adjective adjective;

    public Adjective getAdjective() {
        return adjective;
    }

    public void setAdjective(Adjective adjective) {
        this.adjective = adjective;
    }
}
