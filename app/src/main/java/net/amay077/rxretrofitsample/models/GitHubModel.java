package net.amay077.rxretrofitsample.models;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.subjects.PublishSubject;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hrnv on 2016/12/03.
 */

public class GitHubModel {
    final PublishSubject<User> _user = PublishSubject.create();
    public final Observable<User> user = _user;

    public void getUser(String user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        final Call<User> result = service.getUser(user);
        result.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                _user.onNext(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                _user.onError(t); // 適当過ぎると思われ
            }
        });
    }
}

