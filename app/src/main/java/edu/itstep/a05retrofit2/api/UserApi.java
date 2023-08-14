package edu.itstep.a05retrofit2.api;

import edu.itstep.a05retrofit2.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApi {

    @GET("/users/{id}")
    Call<User> getUserById(@Path("id") int id);

}
