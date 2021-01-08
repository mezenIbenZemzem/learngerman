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
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api/terms.json")
    Call<List<Term>> getWords();

    //Learn in context Api Calls
    @GET("api/examples/learn.json")
    Call<LearnExample> getExample();

    @POST("api/examples/{id}/verify_meaning.json")
    Call<ResponseApiLearnExample> postContextLearnResponse(@Path("id") int id, @Query("meaning") String response);

    //Verbs
    @POST("api/verbs.json")
    Call<ResponseApiVerb> postVerb(@Body Verb verb);

    @GET("api/verbs/{id}.json")
    Call<String> getVerb(@Path("id") int id);

    @PUT("api/verbs/{id}.json")
    Call<ResponseBody> updateVerb(@Path("id") int id, @Body Verb verb);

    @DELETE("api/verbs/{id}.json")
    Call<ResponseBody> deleteVerb(@Path("id") int id);

    //Adjectives
    @POST("api/adjectives.json")
    Call<ResponseApiAdjective> postAdjective(@Body Adjective adjective);

    @GET("api/adjectives/{id}.json")
    Call<String> getAdjective(@Path("id") int id);

    @PUT("api/adjectives/{id}.json")
    Call<ResponseBody> updateAdjective(@Path("id") int id, @Body Adjective adjective);

    @DELETE("api/adjectives/{id}.json")
    Call<ResponseBody> deleteAdjective(@Path("id") int id);

    //Nouns
    @POST("api/nouns.json")
    Call<ResponseApiNoun> postNoun(@Body Noun noun);

    @GET("api/nouns/{id}.json")
    Call<String> getNoun(@Path("id") int id);

    @PUT("api/nouns/{id}.json")
    Call<ResponseBody> updateNoun(@Path("id") int id, @Body Noun noun);

    @DELETE("api/nouns/{id}.json")
    Call<ResponseBody> deleteNoun(@Path("id") int id);

    //Examples :
     ///Verbs
    @POST("api/verbs/{verb_id}/examples")
    Call<ResponseApiExample> postVerbExample(@Path("verb_id") int id, @Body Example example);

    ///Nouns
    @POST("api/nouns/{noun_id}/examples")
    Call<ResponseApiExample> postNounExample(@Path("noun_id") int id, @Body Example example);

    ///Adjectives
    @POST("api/adjectives/{adjective_id}/examples")
    Call<ResponseApiExample> postAdjectiveExample(@Path("adjective_id") int id, @Body Example example);



}
