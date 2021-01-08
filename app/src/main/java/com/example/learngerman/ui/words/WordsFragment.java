package com.example.learngerman.ui.words;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.learngerman.R;
import com.example.learngerman.common.HelpFunctions;
import com.example.learngerman.databinding.FragmentWordsBinding;
import com.example.learngerman.listner.WordsListListner;
import com.example.learngerman.model.Term;
import com.example.learngerman.ui.WordDetail.WordDetailActivity;
import java.util.ArrayList;
import java.util.List;

public class WordsFragment extends Fragment implements WordsListListner, SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {
    private FragmentWordsBinding fragmentWordsBinding;
    private WordsViewModel wordsViewModel;
    private List<Term> wordList = new ArrayList<>();
    private WordsAdapter wordsAdapter;
    private WordsListListner wordsListListner;
    private SearchView.OnQueryTextListener onQueryTextListener;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fragmentWordsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_words, container, false);
        View root = fragmentWordsBinding.getRoot();
        wordsListListner = this;
        onQueryTextListener = this;
        wordsViewModel = new ViewModelProvider(this).get(WordsViewModel.class);
        wordsViewModel.getWordsListApiCall();
        getWords();
        return root;
    }

    public void getWords() {
        fragmentWordsBinding.setIsLoading(true);
        wordsViewModel.getWordsListMutableLiveData().observe(getViewLifecycleOwner(), terms -> {
            fragmentWordsBinding.setIsLoading(false);
            if (terms != null) {
                wordList.clear();
                wordList.addAll(terms);
                wordsAdapter = new WordsAdapter(wordList, getContext(), wordsListListner);
                fragmentWordsBinding.wordsListRv.setAdapter(wordsAdapter);
                fragmentWordsBinding.searchTerm.setOnQueryTextListener((android.widget.SearchView.OnQueryTextListener) onQueryTextListener);
                wordsAdapter.notifyDataSetChanged();
            } else {
                HelpFunctions.showSnackbar(fragmentWordsBinding.getRoot(),getResources().getString(R.string.something_went_wrong), getContext(),false);        }
        });
    }

    @Override
    public void onWordClicked(Term term) {
        Intent intent = new Intent(getContext(), WordDetailActivity.class);
        intent.putExtra("term", term);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        wordsAdapter.getFilter().filter(newText);
        wordsAdapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        wordsViewModel.getWordsListApiCall();
    }
}