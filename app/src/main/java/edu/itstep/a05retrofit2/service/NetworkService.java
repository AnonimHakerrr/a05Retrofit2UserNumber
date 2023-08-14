package edu.itstep.a05retrofit2.service;

import edu.itstep.a05retrofit2.api.PostApi;
import edu.itstep.a05retrofit2.api.UserApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private Retrofit retrofit;
    private static NetworkService networkService;

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private final PostApi postApi;
    private final UserApi userApi;

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postApi = retrofit.create(PostApi.class);
        userApi = retrofit.create(UserApi.class);
    }

    public static NetworkService getInstance() {
        if (networkService == null) {
            networkService = new NetworkService();
        }
        return networkService;
    }

    public PostApi getPostApi() {
        return postApi;
    }

    public UserApi getUserApi() {
        return userApi;
    }

}
