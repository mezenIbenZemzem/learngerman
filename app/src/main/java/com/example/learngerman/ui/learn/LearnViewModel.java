package com.example.learngerman.ui.learn;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.learngerman.R;
import com.example.learngerman.model.LearnExample;
import com.example.learngerman.model.ResponseApiLearnExample;
import com.example.learngerman.network.RetrofitInstance;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LearnViewModel extends ViewModel {

    private final MutableLiveData<LearnExample> exampleMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> responseStatus = new MutableLiveData<>();
    private final MutableLiveData<String> responseLearnAnswer = new MutableLiveData<>();

    public LearnViewModel() {}

    public LiveData<LearnExample> getExampleMutableLiveData () { return  exampleMutableLiveData ;}
    public LiveData<Boolean> getResponseMutableLiveData() { return responseStatus ;}
    public LiveData<String> getResponseLearnAnswerMutableLiveData() {return  responseLearnAnswer; }

    public void getExampleApiCall() {
        Call<LearnExample> call = RetrofitInstance.getRetrofitInstance().getExample();
        call.enqueue(new Callback<LearnExample>() {
            @Override
            public void onResponse(@NotNull Call<LearnExample> call, @NotNull Response<LearnExample> response) {
                if (response.isSuccessful()) {
                    exampleMutableLiveData.setValue(response.body());
                } else {
                    exampleMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<LearnExample> call, @NotNull Throwable t) {
                exampleMutableLiveData.setValue(null);
            }
        });
    }

    public void verifyMeaning(int id , String meaning, Context context) {
        Call<ResponseApiLearnExample> call = RetrofitInstance.getRetrofitInstance().postContextLearnResponse(id, meaning);
        call.enqueue(new Callback<ResponseApiLearnExample>() {
            @Override
            public void onResponse(@NotNull Call<ResponseApiLearnExample> call, @NotNull Response<ResponseApiLearnExample> response) {
                if (response.isSuccessful()) {
                    responseStatus.setValue(true);
                    responseLearnAnswer.setValue(response.body().getErrors());
                }
                else { responseStatus.setValue(false);
                    responseLearnAnswer.setValue(context.getResources().getString(R.string.wrong_answer));
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseApiLearnExample> call, @NotNull Throwable t) {
                responseStatus.setValue(false);
            }
        });
    }
}
