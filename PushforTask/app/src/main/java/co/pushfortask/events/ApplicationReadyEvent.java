package co.pushfortask.events;

/**
 * Created by Marios on 10/06/2017.
 */

public class ApplicationReadyEvent extends AbstractBusEvent {

    private boolean applicationIsReady;

    public ApplicationReadyEvent(boolean applicationIsReady)
    {
        this.applicationIsReady = applicationIsReady;
    }

    public boolean isApplicationIsReady() {
        return applicationIsReady;
    }
}
