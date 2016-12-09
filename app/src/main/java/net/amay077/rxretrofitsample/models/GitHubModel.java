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
    // 外部公開値を変更するための Subject
    final PublishSubject<User> _user = PublishSubject.create();

    // 外部に公開する Hot な Observable
    public final Observable<User> user = _user;

    // 指定した UserID の GitHub ユーザー情報を得る
    // Model だから戻り値の無いメソッドだよ ref - http://ugaya40.hateblo.jp/entry/model-mistake
    public void getUser(String user) {
        // 毎度 build するのはいかがなものか
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        // Retrofit でレスポンスを取得したら Subject を通じて外部に値を流す
        final Call<User> result = service.getUser(user);
        result.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                _user.onNext(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                User empty = new User();
                _user.onNext(empty); // エラー処理適当過ぎると思われ
            }
        });
    }
}

