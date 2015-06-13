package be.ehb.dtsid_inapp.Map;

import android.app.FragmentManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;

import be.ehb.dtsid_inapp.R;

/**
 * Created by tomnahooy on 13/06/15.
 */
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener{

    private GoogleMap mGoogleMap;
    private MapFragment mMapFragment;
    private Spinner mEventSpinner, mRegionSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        mMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        mMapFragment.getMapAsync(this);
        mEventSpinner = (Spinner) findViewById(R.id.sp_map_event);
        mRegionSpinner = (Spinner) findViewById(R.id.sp_map_region);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
