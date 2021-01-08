package com.example.learngerman.ui.WordDetail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.learngerman.model.Adjective;
import com.example.learngerman.model.Noun;
import com.example.learngerman.model.Verb;
import com.example.learngerman.network.RetrofitInstance;

import org.jetbrains.annotations.NotNull;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WordDetailsViewModel extends ViewModel {

    private final MutableLiveData<Boolean> responseUpdateStatus = new MutableLiveData<>();
    private final MutableLiveData<Boolean> responseDeleteStatus = new MutableLiveData<>();


    public WordDetailsViewModel() {}

    public MutableLiveData<Boolean> getResponseUpdateStatusLiveData() {return responseUpdateStatus;}
    public MutableLiveData<Boolean> getResponseDeleteStatusLiveData() {return responseDeleteStatus;}

    public   void deleteVerb(int id){
        Call<ResponseBody> call = RetrofitInstance.getRetrofitInstance().deleteVerb(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                responseDeleteStatus.setValue(response.isSuccessful());
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                responseDeleteStatus.setValue(false);
            }
        });
    }

    public  void updateVerb(Verb verb){
        Call<ResponseBody> call = RetrofitInstance.getRetrofitInstance().updateVerb(verb.getId() ,verb);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                responseUpdateStatus.setValue(response.isSuccessful());
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                responseUpdateStatus.setValue(false);
            }
        });
    }

    public   void deleteNoun(int id){
        Call<ResponseBody> call = RetrofitInstance.getRetrofitInstance().deleteNoun(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                responseDeleteStatus.setValue(response.isSuccessful());
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                responseDeleteStatus.setValue(false);
            }
        });
    }

    public   void updateNoun( Noun noun){
        Call<ResponseBody> call = RetrofitInstance.getRetrofitInstance().updateNoun(noun.getId() , noun);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                responseUpdateStatus.setValue(response.isSuccessful());
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                responseUpdateStatus.setValue(false);
            }
        });
    }

    public   void deleteAdjective(int id){
        Call<ResponseBody> call = RetrofitInstance.getRetrofitInstance().deleteAdjective(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                responseDeleteStatus.setValue(response.isSuccessful());
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                responseDeleteStatus.setValue(false);
            }
        });
    }

    public   void updateAdjective( Adjective adjective){
        Call<ResponseBody> call = RetrofitInstance.getRetrofitInstance().updateAdjective(adjective.getId(), adjective);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                responseUpdateStatus.setValue(response.isSuccessful());
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                responseUpdateStatus.setValue(false);
            }
        });
    }
}
