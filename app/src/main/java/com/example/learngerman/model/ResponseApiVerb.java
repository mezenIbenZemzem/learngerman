package com.example.learngerman.model;

import androidx.lifecycle.ViewModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseApiVerb implements Serializable {



        public Verb getVerb() {
            return verb;
        }

        public void setVerb(Verb verb) {
            this.verb = verb;
        }

        @SerializedName("verb")
        private Verb verb;


}
