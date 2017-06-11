package co.pushfortask.Repository.Realm.ReactiveX;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Marios on 10/06/2017.
 */

public final class RealmObservable {

    private RealmObservable(){}

    public static <T extends RealmObject> Observable<RealmList<T>> list(Context context, final Func1<Realm, RealmList<T>> function) {
        return Observable.create(new OnSubscribeRealmList<T>(context) {
            @Override
            public RealmList<T> get(Realm realm) {
                return function.call(realm);
            }
        });
    }

    public static <T extends RealmObject> Observable<RealmResults<T>> results(Context context, final Func1<Realm, RealmResults<T>> function) {
        return Observable.create(new OnSubscribeRealmResults<T>(context) {
            @Override
            public RealmResults<T> get(Realm realm) {
                return function.call(realm);
            }
        });
    }
}
