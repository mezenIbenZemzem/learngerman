package com.example.learngerman.ui.WordDetail;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.example.learngerman.MainActivity;
import com.example.learngerman.common.Constants;
import com.example.learngerman.common.HelpFunctions;
import com.example.learngerman.listner.DeleteWordListner;
import com.example.learngerman.listner.UpdateWordListner;
import com.example.learngerman.model.Adjective;
import com.example.learngerman.model.Noun;
import com.example.learngerman.model.Term;
import com.example.learngerman.model.Verb;
import com.example.learngerman.R;
import com.example.learngerman.databinding.ActivityWordDetailBinding;

public class WordDetailActivity extends AppCompatActivity implements DeleteWordListner, UpdateWordListner, View.OnClickListener {

    ActivityWordDetailBinding activityWordDetailBinding;
    WordDetailsViewModel wordDetailsViewModel;
    private Term putedTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWordDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_word_detail);
        doInitialization();
        getWordDetails(activityWordDetailBinding.getRoot());
        activityWordDetailBinding.deleteBt.setOnClickListener(this);
        activityWordDetailBinding.updateBt.setOnClickListener(this);
    }

    void doInitialization() {
        wordDetailsViewModel = new ViewModelProvider(this).get(WordDetailsViewModel.class);
        Intent intent = getIntent();
        putedTerm = (Term) intent.getSerializableExtra("term");
        setWordDetails(putedTerm);
    }

    void getWordDetails(View view) {

        wordDetailsViewModel.getResponseUpdateStatusLiveData().observe(this, statusUpdate -> {
            activityWordDetailBinding.setIsLoading(false);
            if (statusUpdate) {
                HelpFunctions.showSnackbar(view, getResources().getString(R.string.was_successfully_updated), getApplicationContext(),  true);
            } else {
                HelpFunctions.showSnackbar(view, getResources().getString(R.string.something_went_wrong), getApplicationContext(),false);
            }
        });

        wordDetailsViewModel.getResponseDeleteStatusLiveData().observe(this, statusDelete -> {
            activityWordDetailBinding.setIsLoading(false);
            if (statusDelete) {
                HelpFunctions.showSnackbar(view, getResources().getString(R.string.was_successfully_deleted), getApplicationContext(), true);
                new Handler().postDelayed(() -> {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    startActivity(intent);
                }, 2500);
            } else {
                HelpFunctions.showSnackbar(view, getResources().getString(R.string.something_went_wrong) , getApplicationContext(), false);
            }
        });
    }

    private void updateNoun() {
        Noun noun = HelpFunctions.setNounToPost(activityWordDetailBinding.spinnerArticles.getSelectedItem().toString(), activityWordDetailBinding.baseNounTv.getText().toString(),
                activityWordDetailBinding.meaningTv.getText().toString(), activityWordDetailBinding.pluralTv.getText().toString(), activityWordDetailBinding.meaningAcceptedTv.getText().toString(), activityWordDetailBinding.getWordLearned());
        if (noun !=null) {
        noun.setId(putedTerm.getId());
        wordDetailsViewModel.updateNoun(noun); }
        else  { HelpFunctions.showSnackbar(activityWordDetailBinding.getRoot(),getResources().getString(R.string.required_fields), this,false);
            activityWordDetailBinding.setIsLoading(false);
        }
    }

    private void updateVerb() {
        Verb verb = HelpFunctions.setVerbToPost(activityWordDetailBinding.baseVerbTv.getText().toString(),
                activityWordDetailBinding.meaningTv.getText().toString(), activityWordDetailBinding.perfectTv.getText().toString()
                , activityWordDetailBinding.praeteritumTv.getText().toString(), activityWordDetailBinding.meaningAcceptedTv.getText().toString(), activityWordDetailBinding.getWordLearned());
        if (verb != null) {
        verb.setId(putedTerm.getId());
        wordDetailsViewModel.updateVerb(verb); }
        else {  HelpFunctions.showSnackbar(activityWordDetailBinding.getRoot(),getResources().getString(R.string.required_fields), this,false);
            activityWordDetailBinding.setIsLoading(false);
        }
    }

    private void updateAdjective() {
        Adjective adjective = HelpFunctions.setAdjectiveToPost(activityWordDetailBinding.baseAdjectiveTv.getText().toString(),
                activityWordDetailBinding.meaningTv.getText().toString(), activityWordDetailBinding.comparativeTv.getText().toString()
                , activityWordDetailBinding.superlativeTv.getText().toString(), activityWordDetailBinding.meaningAcceptedTv.getText().toString(), activityWordDetailBinding.getWordLearned());
        if (adjective != null) {
        adjective.setId(putedTerm.getId());
        wordDetailsViewModel.updateAdjective(adjective); }
        else {
            HelpFunctions.showSnackbar(activityWordDetailBinding.getRoot(), getResources().getString(R.string.required_fields), this, false);
            activityWordDetailBinding.setIsLoading(false);
        }
    }

    private void setWordDetails(Term term) {
        activityWordDetailBinding.setWordName(term.getBase());
        activityWordDetailBinding.setWordMeaning(term.getMeaning());
        if (term.getMeaningsAccepted() != null && !term.getMeaningsAccepted().isEmpty())
            activityWordDetailBinding.setWordMeaningAccepted(term.getMeaningsAccepted());

        switch (term.getType()) {
            case Constants.NOUN: {
                activityWordDetailBinding.editNounLayout.setVisibility(View.VISIBLE);
                activityWordDetailBinding.editVerbLayout.setVisibility(View.GONE);
                activityWordDetailBinding.editAdjectiveLayout.setVisibility(View.GONE);
                activityWordDetailBinding.spinnerArticles.setSelection(((ArrayAdapter) activityWordDetailBinding.spinnerArticles.getAdapter()).getPosition(term.getArticle()));
                activityWordDetailBinding.setPlural(term.getPlural());
                break;
            }
            case Constants.VERB: {
                activityWordDetailBinding.editNounLayout.setVisibility(View.GONE);
                activityWordDetailBinding.editVerbLayout.setVisibility(View.VISIBLE);
                activityWordDetailBinding.editAdjectiveLayout.setVisibility(View.GONE);
                activityWordDetailBinding.setPerfect(term.getPerfect());
                activityWordDetailBinding.setPreateritum(term.getPraeteritum());
                break;
            }
            case Constants.ADJECTIVE: {
                activityWordDetailBinding.editNounLayout.setVisibility(View.GONE);
                activityWordDetailBinding.editVerbLayout.setVisibility(View.GONE);
                activityWordDetailBinding.editAdjectiveLayout.setVisibility(View.VISIBLE);
                activityWordDetailBinding.setComparative(term.getComparative());
                activityWordDetailBinding.setSuperlative(term.getSuperlative());
                break;
            }
        }
    }

    @Override
    public void onDeleteClicked(String type) {
        activityWordDetailBinding.setIsLoading(true);
        switch (type) {
            case Constants.NOUN: {
                wordDetailsViewModel.deleteNoun(putedTerm.getId());
                break;
            }
            case Constants.VERB: {
                wordDetailsViewModel.deleteVerb(putedTerm.getId());
                break;
            }
            case Constants.ADJECTIVE: {
                wordDetailsViewModel.deleteAdjective(putedTerm.getId());
                break;
            }
        }
    }

    @Override
    public void onUpdateClicked(String type) {
        activityWordDetailBinding.setIsLoading(true);
        switch (type) {
            case Constants.NOUN: {
                updateNoun();
                break;
            }
            case Constants.VERB: {
                updateVerb();
                break;
            }
            case Constants.ADJECTIVE: {
                updateAdjective();
                break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.delete_bt) {
            onDeleteClicked(putedTerm.getType());
        } else if (id == R.id.update_bt) {
            onUpdateClicked(putedTerm.getType());
        }
    }
}