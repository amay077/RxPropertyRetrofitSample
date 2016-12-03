package net.amay077.rxretrofitsample.models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hrnv on 2016/12/03.
 */

public interface GitHubService {
    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

}