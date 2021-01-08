package com.example.learngerman.ui.addWord;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.learngerman.common.Constants;
import com.example.learngerman.common.HelpFunctions;
import com.example.learngerman.listner.CreatWordListner;
import com.example.learngerman.model.Adjective;
import com.example.learngerman.model.Noun;
import com.example.learngerman.model.Verb;
import com.example.learngerman.R;
import com.example.learngerman.databinding.FragmentAddWordBinding;

public class AddWordFragment extends Fragment implements CreatWordListner, View.OnClickListener {

    private AddWordViewModel addWordViewModel;
    FragmentAddWordBinding fragmentAddWordBinding;
    CreatWordListner creatWordListner;
    private int createdTermId;
    private String actuelType;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addWordViewModel =
                new ViewModelProvider(this).get(AddWordViewModel.class);
        fragmentAddWordBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_word, container, false);
        creatWordListner = this;
        View root = fragmentAddWordBinding.getRoot();
        setTermTypeSelected();
        fragmentAddWordBinding.creatWordBt.setOnClickListener(this);
        getCreatedTermId();
        showDialogToAddExample();
        getCreationExampleResponse();
        return root;
    }

    public void showDialogToAddExample() {
        addWordViewModel.getCreatTypeMutableLiveData().observe(getViewLifecycleOwner(), type -> {
            actuelType = type;
            if (createdTermId != 0)
            addWordViewModel.creatAlertDialog(getContext(), getActivity(), type, createdTermId , getBaseTv(type), fragmentAddWordBinding.getRoot());
        });

    }

    public void getCreatedTermId() {
        addWordViewModel.getIdCreatedTermMutableLiveData().observe(getViewLifecycleOwner(), id -> {
            if (id != null) createdTermId = id;
            else HelpFunctions.showSnackbar(fragmentAddWordBinding.getRoot(),getResources().getString(R.string.something_went_wrong), getContext(), false);
        });
    }

    public void getCreationExampleResponse() {
        addWordViewModel.getCreatStatusMutableLiveData().observe(getViewLifecycleOwner(), status -> {
            if (status)
                HelpFunctions.showSnackbar(fragmentAddWordBinding.getRoot(), getResources().getString(R.string.was_successfully_added), getContext(), true);
            else
                HelpFunctions.showSnackbar(fragmentAddWordBinding.getRoot(), getResources().getString(R.string.something_went_wrong) , getContext(), false);
            clearAddWordInterface(actuelType);
        });
    }

    public void setTermTypeSelected() {
        fragmentAddWordBinding.spinnerWordTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setLayout(fragmentAddWordBinding.spinnerWordTypes);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    public void setLayout(Spinner spinner) {
        String type = spinner.getSelectedItem().toString();
        switch (type) {
            case Constants.NOUN: {
                fragmentAddWordBinding.addNounLayout.setVisibility(View.VISIBLE);
                fragmentAddWordBinding.addVerbLayout.setVisibility(View.GONE);
                fragmentAddWordBinding.addAdjectiveLayout.setVisibility(View.GONE);
                break;
            }
            case Constants.VERB: {
                fragmentAddWordBinding.addVerbLayout.setVisibility(View.VISIBLE);
                fragmentAddWordBinding.addNounLayout.setVisibility(View.GONE);
                fragmentAddWordBinding.addAdjectiveLayout.setVisibility(View.GONE);
                break;
            }
            case Constants.ADJECTIVE: {
                fragmentAddWordBinding.addAdjectiveLayout.setVisibility(View.VISIBLE);
                fragmentAddWordBinding.addNounLayout.setVisibility(View.GONE);
                fragmentAddWordBinding.addVerbLayout.setVisibility(View.GONE);
                break;
            }
            default:
                fragmentAddWordBinding.addNounLayout.setVisibility(View.VISIBLE);
        }
    }

    private void creatVerb() {
        Verb createdVerb = HelpFunctions.setVerbToPost(fragmentAddWordBinding.baseVerbTv.getText().toString(), fragmentAddWordBinding.meaningTv.getText().toString(),
                fragmentAddWordBinding.perfectTv.getText().toString(), fragmentAddWordBinding.praeteritumTv.getText().toString(),
                fragmentAddWordBinding.meaningAcceptedTv.getText().toString(), false);
           if (createdVerb != null)
        addWordViewModel.creatVerb(createdVerb);
           else {
               HelpFunctions.showSnackbar(fragmentAddWordBinding.getRoot(),getResources().getString(R.string.required_fields), getContext(),false);
           }
    }

    private void creatNoun() {
        Noun createdNoun = HelpFunctions.setNounToPost(fragmentAddWordBinding.spinnerArticles.getSelectedItem().toString(), fragmentAddWordBinding.baseNounTv.getText().toString(), fragmentAddWordBinding.meaningTv.getText().toString(),
                fragmentAddWordBinding.pluralTv.getText().toString(), fragmentAddWordBinding.meaningAcceptedTv.getText().toString(), false);
        if (createdNoun!= null)
        addWordViewModel.creatNoun(createdNoun);
        else {
            HelpFunctions.showSnackbar(fragmentAddWordBinding.getRoot(),getResources().getString(R.string.required_fields), getContext(),false);
        }
    }

    private void creatAdjective() {
        Adjective createdAdjective = HelpFunctions.setAdjectiveToPost(fragmentAddWordBinding.baseAdjectiveTv.getText().toString(), fragmentAddWordBinding.meaningTv.getText().toString(), fragmentAddWordBinding.comparativeTv.getText().toString(),
                fragmentAddWordBinding.superlativeTv.getText().toString(), fragmentAddWordBinding.meaningAcceptedTv.getText().toString(), false);
        if (createdAdjective != null)
        addWordViewModel.creatAdjective(createdAdjective);
        else {
            HelpFunctions.showSnackbar(fragmentAddWordBinding.getRoot(),getResources().getString(R.string.required_fields), getContext(),false);
        }
    }

    private void clearAddWordInterface(String type) {
        switch (type) {
            case Constants.VERB : { HelpFunctions.clearEditexts(fragmentAddWordBinding.baseVerbTv, fragmentAddWordBinding.meaningTv,
                    fragmentAddWordBinding.perfectTv, fragmentAddWordBinding.praeteritumTv,
                    fragmentAddWordBinding.meaningAcceptedTv);
            break; }
            case Constants.NOUN : {
                HelpFunctions.clearEditexts( fragmentAddWordBinding.baseNounTv, fragmentAddWordBinding.meaningTv,
                        fragmentAddWordBinding.pluralTv, fragmentAddWordBinding.meaningAcceptedTv , null);
                break;
            }
            case Constants.ADJECTIVE : {
                HelpFunctions.clearEditexts(fragmentAddWordBinding.baseAdjectiveTv, fragmentAddWordBinding.meaningTv, fragmentAddWordBinding.comparativeTv,
                        fragmentAddWordBinding.superlativeTv, fragmentAddWordBinding.meaningAcceptedTv);
                break;
            }
        }
    }

    private String getBaseTv(String type) {
        String base = "";
        switch (type) {
            case Constants.VERB :{ base = fragmentAddWordBinding.baseVerbTv.getText().toString();
                break; }
            case Constants.NOUN : {
                base = fragmentAddWordBinding.baseNounTv.getText().toString();
                break;
            }
            case Constants.ADJECTIVE : {
                base = fragmentAddWordBinding.baseAdjectiveTv.getText().toString();
                break;
            }
        }
        return base;
    }

    @Override
    public void onCreatClicked(String type) {
        switch (type) {
            case Constants.NOUN: {
                creatNoun();
                break;
            }
            case Constants.VERB: {
                creatVerb();
                break;
            }
            case Constants.ADJECTIVE: {
                creatAdjective();
                break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.creat_word_bt) {
            creatWordListner.onCreatClicked(fragmentAddWordBinding.spinnerWordTypes.getSelectedItem().toString());
        }
    }
}