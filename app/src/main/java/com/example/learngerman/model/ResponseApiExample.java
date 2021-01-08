package com.example.learngerman.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseApiExample implements Serializable {

    @SerializedName("example")
    private Example example;

    public Example getExample() {
        return example;
    }

    public void setExample(Example example) {
        this.example = example;
    }
}
