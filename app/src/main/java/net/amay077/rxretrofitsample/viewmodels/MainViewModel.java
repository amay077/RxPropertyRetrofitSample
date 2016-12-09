package net.amay077.rxretrofitsample.viewmodels;

import android.text.TextUtils;

import net.amay077.rxretrofitsample.models.GitHubModel;

import jp.keita.kagurazaka.rxproperty.RxCommand;
import jp.keita.kagurazaka.rxproperty.RxProperty;

public class MainViewModel {
    // View(EditText) と TwoWay Binding する RxProperty
    public final RxProperty<String> userId = new RxProperty<>();
    // ボタンをおした時に実行されるコマンド(＝ボタンを押した時に onNext が呼ばれる Observable)
    public final RxCommand<Void> getUserCommand;

    // Model -> View DataBinding 用の RxProperty（name.value で DataBinding できる）
    // OneWay Binding だから ReadOnlyRxProperty の方が望ましい
    public final RxProperty<String> name;
    public final RxProperty<String> location;
    public final RxProperty<String> bio;

    // GitHub からユーザー情報を得る Model. DIしようね
    final GitHubModel _gitHubModel = new GitHubModel();

    public MainViewModel() {
        // Model -> ViewModel -> View のデータの流れ道を作っておきましょう
        // GitHubModel の user が変更されたら、name/location/bio にそれぞれデータを流すよ
        name = new RxProperty<>(_gitHubModel.user.map(user -> user.getName()));
        location = new RxProperty<>(_gitHubModel.user.map(user -> user.getLocation()));
        bio = new RxProperty<>(_gitHubModel.user.map(user -> user.getBio()));


        // ユーザー取得コマンドは userID が空でない時のみ使用可能です
        getUserCommand = new RxCommand<>(userId.asObservable().map(x -> !TextUtils.isEmpty(x)));
        // ユーザー取得が実行されたら GitHubModel の getUser を呼ぶよ
        getUserCommand.asObservable().subscribe(x -> {
            _gitHubModel.getUser(userId.get());
        });
    }
}
