package com.example.learngerman.network;

import com.example.learngerman.model.Adjective;
import com.example.learngerman.model.Example;
import com.example.learngerman.model.LearnExample;
import com.example.learngerman.model.Noun;
import com.example.learngerman.model.ResponseApiAdjective;
import com.example.learngerman.model.ResponseApiExample;
import com.example.learngerman.model.ResponseApiLearnExample;
import com.example.learngerman.model.ResponseApiNoun;
import com.example.learngerman.model.Term;
import com.example.learngerman.model.ResponseApiVerb;
import com.example.learngerman.model.Verb;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static final String BASE_URL = "https://api-learn-german.herokuapp.com/";
    public ApiService apiService;
    public static RetrofitInstance retrofitInstance;
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


    private RetrofitInstance () {
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json; charset=utf-8")
                    .build();
            return chain.proceed(request);
        });
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static RetrofitInstance getRetrofitInstance(){
        if (retrofitInstance == null) {
            retrofitInstance = new RetrofitInstance();
        }
        return retrofitInstance;
    }

    public Call<List<Term>> getWords() {
        return apiService.getWords();
    }

    public Call<LearnExample> getExample() {
        return apiService.getExample();
    }

    public Call<ResponseApiLearnExample> postContextLearnResponse(int id, String response) { return apiService.postContextLearnResponse(id, response);
    }

    public Call<ResponseApiVerb> postVerb(Verb verb) { return  apiService.postVerb(verb);}

    public Call<ResponseBody> deleteVerb(int id) {return apiService.deleteVerb(id);}

    public Call<ResponseBody> updateVerb(int id , Verb verb) {return apiService.updateVerb(id, verb);}

    public Call<ResponseApiNoun> postNoun(Noun noun) { return  apiService.postNoun(noun);}

    public Call<ResponseBody> deleteNoun(int id) {return apiService.deleteNoun(id);}

    public Call<ResponseBody> updateNoun(int id , Noun noun) {return apiService.updateNoun(id , noun);}

    public Call<ResponseApiAdjective> postAdjective(Adjective adjective) { return  apiService.postAdjective(adjective);}

    public Call<ResponseBody> deleteAdjective(int id) {return apiService.deleteAdjective(id);}

    public Call<ResponseBody> updateAdjective(int id , Adjective adjective) {return apiService.updateAdjective(id , adjective);}

    public Call<ResponseApiExample> postVerbExample (int id, Example example) {return apiService.postVerbExample(id,example);}

    public Call<ResponseApiExample> postNounExample (int id, Example example) {return apiService.postNounExample(id,example);}

    public Call<ResponseApiExample> postAdjectiveExample (int id, Example example) {return apiService.postAdjectiveExample(id,example);}


}
