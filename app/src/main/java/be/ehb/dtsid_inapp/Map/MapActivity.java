package be.ehb.dtsid_inapp.map;

import android.app.Activity;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import be.ehb.dtsid_inapp.R;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mGoogleMap;
    private MapFragment mMapFragment;
    private Spinner mEventSpinner, mRegionSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Log.d("MapTest", "What the hell is going on?");

        mMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);
       /*/ mEventSpinner = (Spinner) findViewById(R.id.sp_map_event);
        mRegionSpinner = (Spinner) findViewById(R.id.sp_map_region);
        */
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(new LatLng(50.846777, 4.352360), 13);
        mGoogleMap.animateCamera(cu);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        Log.d("MapTest", "Get here?");
    }
}
