package com.example.learngerman.ui.addWord;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.learngerman.R;
import com.example.learngerman.common.Constants;
import com.example.learngerman.common.HelpFunctions;
import com.example.learngerman.model.Adjective;
import com.example.learngerman.model.Example;
import com.example.learngerman.model.Noun;
import com.example.learngerman.model.ResponseApiAdjective;
import com.example.learngerman.model.ResponseApiExample;
import com.example.learngerman.model.ResponseApiNoun;
import com.example.learngerman.model.ResponseApiVerb;
import com.example.learngerman.model.Verb;
import com.example.learngerman.network.RetrofitInstance;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddWordViewModel extends ViewModel {

    private final MutableLiveData<String> creatTypeMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> idCreatedTermMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> creatStatusMutableLiveData = new MutableLiveData<>();


    public MutableLiveData<String> getCreatTypeMutableLiveData() {
        return creatTypeMutableLiveData;
    }

    public MutableLiveData<Integer> getIdCreatedTermMutableLiveData() {
        return idCreatedTermMutableLiveData;
    }

    public MutableLiveData<Boolean> getCreatStatusMutableLiveData() {
        return creatStatusMutableLiveData;
    }

    public AddWordViewModel() {
    }

    public void creatVerb(Verb verb) {
        Call<ResponseApiVerb> call = RetrofitInstance.getRetrofitInstance().postVerb(verb);
        call.enqueue(new Callback<ResponseApiVerb>() {
            @Override
            public void onResponse(@NotNull Call<ResponseApiVerb> call, @NotNull Response<ResponseApiVerb> response) {
                if (response.isSuccessful()) {
                    idCreatedTermMutableLiveData.setValue(response.body().getVerb().getId());
                    creatTypeMutableLiveData.setValue(Constants.VERB);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseApiVerb> call, @NotNull Throwable t) {
                idCreatedTermMutableLiveData.setValue(null);
            }
        });
    }

    private void creatVerbExample(int id, Example example) {
        Call<ResponseApiExample> call = RetrofitInstance.getRetrofitInstance().postVerbExample(id, example);
        call.enqueue(new Callback<ResponseApiExample>() {
            @Override
            public void onResponse(@NotNull Call<ResponseApiExample> call, @NotNull Response<ResponseApiExample> response) {
                if (response.isSuccessful())
                    creatStatusMutableLiveData.setValue(response.isSuccessful());
                else {
                    creatStatusMutableLiveData.setValue(false);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseApiExample> call, @NotNull Throwable t) {
                creatStatusMutableLiveData.setValue(false);
            }
        });
    }

    public void creatNoun(Noun noun) {
        Call<ResponseApiNoun> call = RetrofitInstance.getRetrofitInstance().postNoun(noun);
        call.enqueue(new Callback<ResponseApiNoun>() {
            @Override
            public void onResponse(@NotNull Call<ResponseApiNoun> call, @NotNull Response<ResponseApiNoun> response) {
                if (response.isSuccessful()) {
                    idCreatedTermMutableLiveData.setValue(response.body().getNoun().getId());
                    creatTypeMutableLiveData.setValue(Constants.NOUN);
                } else idCreatedTermMutableLiveData.setValue(null);
            }

            @Override
            public void onFailure(@NotNull Call<ResponseApiNoun> call, @NotNull Throwable t) {
                idCreatedTermMutableLiveData.setValue(null);
            }
        });
    }

    private void creatNounExample(int id, Example example) {
        Call<ResponseApiExample> call = RetrofitInstance.getRetrofitInstance().postNounExample(id, example);
        call.enqueue(new Callback<ResponseApiExample>() {
            @Override
            public void onResponse(@NotNull Call<ResponseApiExample> call, @NotNull Response<ResponseApiExample> response) {
                if (response.isSuccessful())
                    creatStatusMutableLiveData.setValue(response.isSuccessful());
                else {
                    creatStatusMutableLiveData.setValue(false);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseApiExample> call, @NotNull Throwable t) {
                creatStatusMutableLiveData.setValue(false);
            }
        });
    }

    public void creatAlertDialog(Context context, Activity activity, String type, int id, String base, View viewA) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_example_layout, null);
        EditText sentenceEd = dialogView.findViewById(R.id.sentence_Ed);
        EditText keywordEd = dialogView.findViewById(R.id.keyword_Ed);
        Button creatExampleBt = dialogView.findViewById(R.id.creat_example_bt);
        TextView termTitle = dialogView.findViewById(R.id.word_for_example_tv);
        termTitle.setText(base);

        dialogBuilder.setView(dialogView);
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        creatExampleBt.setOnClickListener(view -> {
            Boolean isValid = creatExample(id, type, sentenceEd, keywordEd, context, viewA);
            if (isValid)
                alertDialog.dismiss();
        });
    }

    //Creat new Adjective
    public void creatAdjective(Adjective adjective) {
        Call<ResponseApiAdjective> call = RetrofitInstance.getRetrofitInstance().postAdjective(adjective);
        call.enqueue(new Callback<ResponseApiAdjective>() {
            @Override
            public void onResponse(@NotNull Call<ResponseApiAdjective> call, @NotNull Response<ResponseApiAdjective> response) {
                if (response.isSuccessful()) {
                    idCreatedTermMutableLiveData.setValue(response.body().getAdjective().getId());
                    creatTypeMutableLiveData.setValue(Constants.ADJECTIVE);
                } else {
                    idCreatedTermMutableLiveData.setValue(null);
                }

            }

            @Override
            public void onFailure(@NotNull Call<ResponseApiAdjective> call, @NotNull Throwable t) {
                idCreatedTermMutableLiveData.setValue(null);
            }
        });
    }

    private void creatAdjectiveExample(int id, Example example) {
        Call<ResponseApiExample> call = RetrofitInstance.getRetrofitInstance().postAdjectiveExample(id, example);
        call.enqueue(new Callback<ResponseApiExample>() {
            @Override
            public void onResponse(@NotNull Call<ResponseApiExample> call, @NotNull Response<ResponseApiExample> response) {
                if (response.isSuccessful()) creatStatusMutableLiveData.setValue(response.isSuccessful());
                else {
                    creatStatusMutableLiveData.setValue(false);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseApiExample> call, @NotNull Throwable t) {
                creatStatusMutableLiveData.setValue(false);
            }
        });
    }


    public Boolean creatExample(int id, String type, EditText sentence, EditText keyword, Context context, View view) {
        Example example = HelpFunctions.setExampleToPost(sentence.getText().toString().toLowerCase(), keyword.getText().toString().toLowerCase());
        if (example != null) {
            switch (type) {
                case Constants.VERB: {
                    creatVerbExample(id, example);
                    break;
                }
                case Constants.NOUN: {
                    creatNounExample(id, example);
                    break;
                }
                case Constants.ADJECTIVE: {
                    creatAdjectiveExample(id, example);
                    break;
                }
            }
            return true;
        } else {
            HelpFunctions.showSnackbar(view, context.getResources().getString(R.string.required_fields), context, false);
            return false;
        }
    }
}
