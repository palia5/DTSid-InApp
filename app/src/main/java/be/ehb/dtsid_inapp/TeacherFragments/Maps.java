package be.ehb.dtsid_inapp.TeacherFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import be.ehb.dtsid_inapp.Activities.TeacherActivity;
import be.ehb.dtsid_inapp.R;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by doortje on 12/06/15.
 */
public class Maps extends Fragment implements OnMapReadyCallback,GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener,GoogleMap.OnInfoWindowClickListener {

    TeacherActivity activity;

    private TextView regiosTV;
    private Spinner regiosSP;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_regios_mapscreen);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.frgm_regios_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Toast.makeText(getApplicationContext(), "Map loaded and ready", Toast.LENGTH_SHORT).show();
        mMap = googleMap;

        mMap.setOnMapClickListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);

        LatLng belgCoord = new LatLng(50.846102, 4.3453741);
        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(belgCoord, 8);
        mMap.animateCamera(cu);

        addMarkers();

    }

    private void addMarkers() {
        LatLng erasmusCoord = new LatLng(50.841836, 4.322847);
        mMap.addMarker(new MarkerOptions()
                .position(erasmusCoord)
                .title("Erasmus Hogeschool Brussel")
                .snippet("Opleidingen Dig-x & Multec")
                .icon(BitmapDescriptorFactory.defaultMarker(200)));
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Toast.makeText(getApplicationContext(), "coord, lat = " + latLng.latitude + " long=" + latLng.longitude, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        if (marker.getTitle().equals("Erasmus Hogeschool Brussel")) {
            Toast.makeText(getApplicationContext(), "Word wat jij wil!", Toast.LENGTH_LONG).show();
        }
        return false;
    }
}



