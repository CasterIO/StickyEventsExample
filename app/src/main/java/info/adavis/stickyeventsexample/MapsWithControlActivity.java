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

public class MapsWithControlActivity extends FragmentActivity implements OnMapReadyCallback
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
    public void onMapReady (GoogleMap googleMap)
    {
        this.googleMap = googleMap;

        LocationRetrievedEvent event = bus.getStickyEvent( LocationRetrievedEvent.class );
        if ( event != null )
        {
            latLng = new LatLng( event.getLatitude(), event.getLongitude() );

            bus.removeStickyEvent( event );
        }

        if ( latLng != null )
        {
            // Add a marker and move the camera
            this.googleMap.addMarker( new MarkerOptions().position( latLng ) );
            this.googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom( latLng, ZOOM_LEVEL ) );
        }
    }

}
