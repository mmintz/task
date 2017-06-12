package co.pushfortask.Repository;

/**
 * Created by Marios on 10/06/2017.
 */

public interface RequestStatus {

    void onStartRequestUpdateUI();

    void onSuccessRequestUpdateUI();

    void onErrorRequestUpdateUI();
}
