package info.adavis.stickyeventsexample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{

    private static final float ZOOM_LEVEL = 15;

    private EventBus bus;
    private GoogleMap googleMap;
    private LatLng latLng;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_maps );

        bus = Injector.provideEventBus();

        SupportMapFragment mapFragment = ( SupportMapFragment ) getSupportFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );
    }

    @Override
    protected void onStart ()
    {
        super.onStart();

        bus.register( this );
    }

    @Override
    protected void onStop ()
    {
        bus.unregister( this );

        super.onStop();
    }

    @Override
    public void onMapReady (GoogleMap googleMap)
    {
        this.googleMap = googleMap;

        if ( latLng != null )
        {
            // Add a marker and move the camera
            this.googleMap.addMarker( new MarkerOptions().position( latLng ) );
            this.googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom( latLng, ZOOM_LEVEL ) );
        }
    }

    @Subscribe( sticky = true )
    public void locationRetrieved (LocationRetrievedEvent event)
    {
        Log.i( "MapsActivity", "LOCATION: " + event.getLatitude() + " " + event.getLongitude() );

        latLng = new LatLng( event.getLatitude(), event.getLongitude() );
    }
}
