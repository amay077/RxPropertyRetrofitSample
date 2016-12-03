package net.amay077.rxretrofitsample.viewmodels;

import android.databinding.ObservableField;
import android.view.View;

import net.amay077.rxretrofitsample.models.GitHubModel;
import net.amay077.rxretrofitsample.models.User;

import java.util.Observable;

import jp.keita.kagurazaka.rxproperty.RxProperty;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainViewModel {
    public final ObservableField<String> userId = new ObservableField<>();

    public final RxProperty<String> name;
    public final RxProperty<String> location;
    public final RxProperty<String> bio;

    final GitHubModel _gitHubModel = new GitHubModel(); // DIしようね

    public MainViewModel() {
        userId.set("amay077");

        name = new RxProperty<String>(_gitHubModel.user.map(user -> user.getName()));
        location = new RxProperty<String>(_gitHubModel.user.map(user -> user.getLocation()));
        bio = new RxProperty<String>(_gitHubModel.user.map(user -> user.getBio()));
    }

    public void onClickGetUser(View view) {
        _gitHubModel.getUser(userId.get());
    }
}
