package info.adavis.stickyeventsexample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity
{

    private Handler handler = new Handler();
    private EventBus bus;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        bus = Injector.provideEventBus();
    }

    @Override
    protected void onResume ()
    {
        super.onResume();

        handler.postDelayed( new Runnable()
        {
            @Override
            public void run ()
            {
                bus.postSticky( new LocationRetrievedEvent( -87.6298, 41.8781 ) );

                startActivity( new Intent( MainActivity.this, MapsActivity.class ) );
                finish();
            }
        }, 2000 );
    }
}
