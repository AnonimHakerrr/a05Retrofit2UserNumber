package edu.itstep.a05retrofit2.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostApi {

    @GET("/posts/{id}")
    Call<edu.itstep.a05retrofit2.model.Post> getPostById(@Path("id") int id);

    @GET("/posts")
    Call<List<edu.itstep.a05retrofit2.model.Post>> getAllPosts();

}