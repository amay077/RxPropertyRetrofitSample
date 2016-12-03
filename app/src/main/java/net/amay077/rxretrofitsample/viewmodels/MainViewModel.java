package net.amay077.rxretrofitsample.viewmodels;

import android.databinding.ObservableField;
import android.view.View;

import net.amay077.rxretrofitsample.models.GitHubModel;
import net.amay077.rxretrofitsample.models.User;

import java.util.Observable;

import rx.functions.Action1;

public class MainViewModel {
    public final ObservableField<String> userId = new ObservableField<>();

    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> location = new ObservableField<>();
    public final ObservableField<String> bio = new ObservableField<>();

    final GitHubModel _gitHubModel = new GitHubModel(); // DIしようね

    public MainViewModel() {
        userId.set("amay077");

        _gitHubModel.user.subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                name.set(user.getName());
                location.set(user.getLocation());
                bio.set(user.getBio());
            }
        });
    }

    public void onClickGetUser(View view) {
        _gitHubModel.getUser(userId.get());
    }
}
