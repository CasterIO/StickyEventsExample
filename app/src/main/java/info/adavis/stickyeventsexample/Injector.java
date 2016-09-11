package info.adavis.stickyeventsexample;

import org.greenrobot.eventbus.EventBus;

/**
 * @author Annyce Davis
 */
public class Injector
{
    private static EventBus eventBus;

    public static EventBus provideEventBus ()
    {
        if ( eventBus == null )
        {
            eventBus = EventBus.builder()
                               .logNoSubscriberMessages( BuildConfig.DEBUG )
                               .sendNoSubscriberEvent( BuildConfig.DEBUG )
                               .build();
        }
        return eventBus;
    }

}
