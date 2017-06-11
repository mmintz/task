package co.pushfortask.Repository.Realm.ReactiveX;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.exceptions.RealmException;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

/**
 * Created by Marios on 10/06/2017.
 */

public abstract class OnSubscribeRealmList<T extends RealmObject> implements Observable.OnSubscribe<RealmList<T>> {

    private Context context;

    public OnSubscribeRealmList(Context context) {
        this.context = context;
    }

    @Override
    public void call(final Subscriber<? super RealmList<T>> subscriber) {
        final Realm realm = Realm.getInstance(context);
        subscriber.add(Subscriptions.create(new Action0() {
            @Override
            public void call() {
                try {
                    realm.close();
                } catch (RealmException ex) {
                    subscriber.onError(ex);
                }
            }
        }));

        RealmList<T> object;
        realm.beginTransaction();
        try {
            object = get(realm);
            realm.commitTransaction();
        } catch (RuntimeException e) {
            realm.cancelTransaction();
            subscriber.onError(new RealmException("Error during transaction.", e));
            return;
        } catch (Error e) {
            realm.cancelTransaction();
            subscriber.onError(e);
            return;
        }
        if (object != null) {
            subscriber.onNext(object);
        }
        subscriber.onCompleted();
    }

    public abstract RealmList<T> get(Realm realm);
}
