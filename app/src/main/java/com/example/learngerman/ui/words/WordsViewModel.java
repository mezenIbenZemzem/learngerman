package com.example.learngerman.ui.words;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.learngerman.model.Term;
import com.example.learngerman.network.RetrofitInstance;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WordsViewModel extends ViewModel {

    private final MutableLiveData<List<Term>> wordsListMutableLiveData = new MutableLiveData<>();

    public WordsViewModel() {
    }

    public LiveData<List<Term>> getWordsListMutableLiveData() {
        return wordsListMutableLiveData;
    }


    public void getWordsListApiCall () {
        Call<List<Term>> call = RetrofitInstance.getRetrofitInstance().getWords();
        call.enqueue(new Callback<List<Term>>() {

            @Override
            public void onResponse(@NotNull Call<List<Term>> call, @NotNull Response<List<Term>> response) {
                if (response.isSuccessful()) {
                    wordsListMutableLiveData.postValue(response.body());
                }
                else  wordsListMutableLiveData.postValue(null);
            }

            @Override
            public void onFailure(@NotNull Call<List<Term>> call, @NotNull Throwable t) {
                wordsListMutableLiveData.postValue(null);
            }
        });
    }
}