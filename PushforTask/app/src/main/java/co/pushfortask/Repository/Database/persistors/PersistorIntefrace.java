package co.pushfortask.Repository.Database.persistors;

import rx.Observable;

/**
 * Created by Marios on 10/06/2017.
 */

public interface PersistorIntefrace<T> {

    Observable<T> persist(T data) throws Exception;

}
