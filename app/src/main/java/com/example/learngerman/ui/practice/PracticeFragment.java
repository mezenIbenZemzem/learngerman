package com.example.learngerman.ui.practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.learngerman.ui.learn.LearnActivity;
import com.example.learngerman.R;
import com.example.learngerman.databinding.FragmentPracticeBinding;

public class PracticeFragment extends Fragment implements View.OnClickListener {

    private PracticeViewModel practiceViewModel;
    FragmentPracticeBinding fragmentPracticeBinding ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        practiceViewModel =
                new ViewModelProvider(this).get(PracticeViewModel.class);
        fragmentPracticeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_practice, container, false);
        fragmentPracticeBinding.learnContextBt.setOnClickListener(this);
        return fragmentPracticeBinding.getRoot();
    }

    public void startLearnActivity(){
        Intent intent = new Intent(getContext(), LearnActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.learn_bt:
                // Do something
                break;
            case R.id.smart_learn_bt:
                // Do something
                break;
            case R.id.learn_context_bt:
               startLearnActivity();
                break;
            case R.id.learn_verbs_bt:
                // Do something
                break;
            case R.id.learn_articles_bt:
                // Do something
                break;
            case R.id.learn_german_english_bt:
                // Do something
                break;
            case R.id.learn_english_german:
                // Do something
                break;
        }

    }
}