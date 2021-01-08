package com.example.learngerman.ui.learn;

import android.os.Bundle;

import com.example.learngerman.common.HelpFunctions;
import com.example.learngerman.model.LearnExample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;

import com.example.learngerman.R;
import com.example.learngerman.databinding.ActivityLearnBinding;

public class LearnActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityLearnBinding activityLearnBinding;
    public LearnViewModel learnViewModel;
    LearnExample showedLearnExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLearnBinding = DataBindingUtil.setContentView(this, R.layout.activity_learn);
        learnViewModel = new ViewModelProvider(this).get(LearnViewModel.class);
        Toolbar toolbar = activityLearnBinding.toolbar;
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        learnViewModel.getExampleApiCall();
        getExample();
        getResponse();
        activityLearnBinding.verifyWordBt.setOnClickListener(this);
    }

    private void getExample() {
        activityLearnBinding.setIsLoadingExample(true);
        learnViewModel.getExampleMutableLiveData().observe(this, learnExample -> {
            showedLearnExample = learnExample;
            activityLearnBinding.setIsLoadingExample(false);
            activityLearnBinding.setSentence(learnExample.getSentence());
            activityLearnBinding.setKeyword(learnExample.getKeyword());
        });
    }

    private void getResponse() {
        learnViewModel.getResponseLearnAnswerMutableLiveData().observe(this, s -> HelpFunctions.showSnackbar(activityLearnBinding.getRoot(), s, getApplicationContext(), true));

        learnViewModel.getResponseMutableLiveData().observe(this, answerStatus -> {
            if (!answerStatus) {
                HelpFunctions.showSnackbar(activityLearnBinding.getRoot(), getResources().getString(R.string.something_went_wrong), getApplicationContext(), false);
            }
            learnViewModel.getExampleApiCall();
            getExample();
            activityLearnBinding.learnMeaningTv.setText("");
        });
    }

    @Override
    public void onClick(View view) {
        String meaning = activityLearnBinding.learnMeaningTv.getText().toString();
        if (view.getId() == R.id.verify_word_bt) {
            if (!meaning.isEmpty()) {
                learnViewModel.verifyMeaning(showedLearnExample.getId(), activityLearnBinding.learnMeaningTv.getText().toString(), this);
            } else
                HelpFunctions.showSnackbar(activityLearnBinding.getRoot(), this.getResources().getString(R.string.required_fields), this, false);
        }
    }
}