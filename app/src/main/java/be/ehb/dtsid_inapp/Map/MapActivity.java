package be.ehb.dtsid_inapp.Map;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static be.ehb.dtsid_inapp.Map.MapContract.*;

import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.Models.Event;
import be.ehb.dtsid_inapp.Models.EventAdapter;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.R;

public class MapActivity extends Activity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener, AdapterView.OnItemSelectedListener {

    private GoogleMap mMap;
    private MapFragment mMapFragment;
    private TextView eventsTV;
    private Spinner eventSP;
    private List<Event> events;
    private List<Subscription> subscriptions;
    private List<Subscription> allSubscriptions;
    private EventAdapter eventAdapter;
    private DatabaseContract dbc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.frgm_regios_map);
        mMapFragment.getMapAsync(this);

        eventsTV = (TextView) findViewById(R.id.tv_label_event_mapscreen);
        eventSP = (Spinner) findViewById(R.id.sp_events_mapscreen);

        dbc = new DatabaseContract(getApplicationContext());

        Typeface myCustomFont = Typeface.createFromAsset(this.getAssets()
                , "fonts/ehb_font.ttf");

        eventsTV.setTypeface(myCustomFont);
        events = (ArrayList) dbc.getAllEvents();
        eventAdapter = new EventAdapter(this, events);
        eventSP.setAdapter(eventAdapter);
        eventSP.setOnItemSelectedListener(this);

        subscriptions = new ArrayList<>();

    }

    //TEST TO COMMIT
    @Override
    public	void	onMapReady(GoogleMap	googleMap)	{
        Toast.makeText(getApplicationContext(), "Kaart ingeladen", Toast.LENGTH_SHORT).show();
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.setOnMapClickListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        CameraUpdate	cu	=	CameraUpdateFactory.newLatLngZoom(WAALS_BRABANT, 8);
        mMap.animateCamera(cu);
        addMarkers(R.drawable.custom_marker);
    }

    @Override
    public	void	onMapClick(LatLng	latLng)	{

    }
    @Override
    public	boolean	onMarkerClick(Marker	marker)	{

        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    private void addMarkers(int iconResource){

        mMap.addMarker(new MarkerOptions()
        .position(VLAAMS_BRABANT)
        .title("Vlaams-Brabant")
        .snippet("TO DO")
        .icon(BitmapDescriptorFactory.fromResource(iconResource)));

        mMap.addMarker(new MarkerOptions()
                .position(ANTWERPEN)
                .title("Antwerpen")
                .snippet("TO DO")
                .icon(BitmapDescriptorFactory.fromResource(iconResource)));

        mMap.addMarker(new MarkerOptions()
                .position(LIMBURG)
                .title("Limburg")
                .snippet("TO DO")
                .icon(BitmapDescriptorFactory.fromResource(iconResource)));

        mMap.addMarker(new MarkerOptions()
                .position(WEST_VLAANDEREN)
                .title("West-Vlaanderen")
                .snippet("TO DO")
                .icon(BitmapDescriptorFactory.fromResource(iconResource)));

        mMap.addMarker(new MarkerOptions()
                .position(OOST_VLAANDEREN)
                .title("Oost-Vlaanderen")
                .snippet("TO DO")
                .icon(BitmapDescriptorFactory.fromResource(iconResource)));

        mMap.addMarker(new MarkerOptions()
                .position(LUIK)
                .title("Luik")
                .snippet("TO DO")
                .icon(BitmapDescriptorFactory.fromResource(iconResource)));

        mMap.addMarker(new MarkerOptions()
                .position(WAALS_BRABANT)
                .title("Waals-Brabant")
                .snippet("TO DO")
                .icon(BitmapDescriptorFactory.fromResource(iconResource)));

        mMap.addMarker(new MarkerOptions()
                .position(HENEGOUWEN)
                .title("Henegouwen")
                .snippet("TO DO")
                .icon(BitmapDescriptorFactory.fromResource(iconResource)));

        mMap.addMarker(new MarkerOptions()
                .position(NAMEN)
                .title("NAMEN")
                .snippet("TO DO")
                .icon(BitmapDescriptorFactory.fromResource(iconResource)));

        mMap.addMarker(new MarkerOptions()
                .position(LUXEMBURG)
                .title("Luxemburg")
                .snippet("TO DO")
                .icon(BitmapDescriptorFactory.fromResource(iconResource)));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        allSubscriptions.clear();
        Event selectedEvent = events.get(position);
        allSubscriptions = dbc.getAllSubscriptions();
        for (Subscription index : subscriptions) {
            if (index.getEvent().equals(selectedEvent)){
                subscriptions.add(index);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        subscriptions = new ArrayList<>();
    }
}
