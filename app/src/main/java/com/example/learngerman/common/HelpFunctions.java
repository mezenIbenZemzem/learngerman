package com.example.learngerman.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.learngerman.R;
import com.example.learngerman.model.Adjective;
import com.example.learngerman.model.Example;
import com.example.learngerman.model.Noun;
import com.example.learngerman.model.Verb;
import com.google.android.material.snackbar.Snackbar;

public class HelpFunctions {

    public static boolean isValidExample(String sentence, String keyword) {
        if (TextUtils.isEmpty(sentence)) {
            return false;
        }
        return !TextUtils.isEmpty(keyword);
    }

    //to check if the user fill in all the requireds fields
    public static boolean isValid(String base, String meaning, String param_1, String param_2) {
        if (TextUtils.isEmpty(base)) {
            return false;
        }
        if (TextUtils.isEmpty(meaning)) {
            return false;
        }
        if (TextUtils.isEmpty(param_1)) {
            return false;
        }
        return !TextUtils.isEmpty(param_2);
    }

    public static Example setExampleToPost(String sentence, String keyword) {
        Example example = new Example();
        if (HelpFunctions.isValidExample(sentence, keyword)) {
            example.setSentence(sentence);
            example.setKeyword(keyword);
        } else return null;
        return example;
    }

    public static Noun setNounToPost(String article, String base, String meaning, String plural, String meaningAccepted, Boolean meaningLearned) {
        Noun noun = new Noun();
        if (HelpFunctions.isValid(article, base, meaning, plural)) {
            noun.setBase(base);
            noun.setMeaning(meaning);
            noun.setArticle(article);
            noun.setPlural(plural);
        } else return null;
        if (meaningAccepted != null && !meaningAccepted.isEmpty())
            noun.setMeaningsAccepted(meaningAccepted);
        if (meaningLearned != null) noun.setMeaningLearned(meaningLearned);
        return noun;
    }

    public static Adjective setAdjectiveToPost(String base, String meaning, String comparative, String superlative, String meaningAccepted, Boolean meaningLearned) {
        Adjective adjective = new Adjective();
        if (HelpFunctions.isValid(base, meaning, comparative, superlative)) {
            adjective.setBase(base);
            adjective.setMeaning(meaning);
            adjective.setComparative(comparative);
            adjective.setSuperlative(superlative);
        } else return null;
        if (!meaningAccepted.isEmpty()) adjective.setMeaningsAccepted(meaningAccepted);
        if (meaningLearned != null) adjective.setMeaningLearned(meaningLearned);
        return adjective;
    }

    public static Verb setVerbToPost(String base, String meaning, String perfect, String preateritum, String meaningAccepted, Boolean meaningLearned) {
        Verb verb = new Verb();
        if (HelpFunctions.isValid(base, meaning, perfect, preateritum)) {
            verb.setBase(base);
            verb.setMeaning(meaning);
            verb.setPerfect(perfect);
            verb.setPraeteritum(preateritum);
        } else return null;
        if (!meaningAccepted.isEmpty()) verb.setMeaningsAccepted(meaningAccepted);
        if (meaningLearned != null) verb.setMeaningLearned(meaningLearned);
        return verb;
    }

    public static void showSnackbar(View view, String message, Context context, Boolean typeAnswer) {
        Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(context, R.color.blue_700));
        Snackbar.SnackbarLayout customLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView tv = (TextView) snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setVisibility(View.INVISIBLE);
        LayoutInflater inflater = LayoutInflater.from(context);
        View customSnackView = inflater.inflate(R.layout.my_snackbar_layout, null);
        if (typeAnswer) {
            customSnackView.setBackgroundColor(ContextCompat.getColor(context, R.color.blue_700));
            snackbarView.setBackgroundColor(ContextCompat.getColor(context, R.color.blue_700));
        } else {
            customSnackView.setBackgroundColor(ContextCompat.getColor(context, R.color.black));
            snackbarView.setBackgroundColor(ContextCompat.getColor(context, R.color.black));
        }
        TextView customTv = customSnackView.findViewById(R.id.snackbar_tv);
        customTv.setText(message);
        snackbar.setDuration(2000);
        customLayout.addView(customSnackView);
        snackbar.show();
    }

    public static void clearEditexts(EditText editText_1, EditText editText_2, EditText editText_3, EditText editText_4, EditText editText_5) {
        editText_1.setText("");
        editText_2.setText("");
        editText_3.setText("");
        editText_4.setText("");
        if (editText_5 != null) editText_5.setText("");
    }
}

