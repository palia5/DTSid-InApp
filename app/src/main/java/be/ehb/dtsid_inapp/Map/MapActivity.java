package be.ehb.dtsid_inapp.Map;

import android.app.Activity;
import android.content.Intent;
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

import be.ehb.dtsid_inapp.Activities.TeacherActivity;
import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.JSONTasks.JSONContract;
import be.ehb.dtsid_inapp.Models.Department;
import be.ehb.dtsid_inapp.Models.Event;
import be.ehb.dtsid_inapp.Models.EventAdapter;
import be.ehb.dtsid_inapp.Models.Gemeente;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.Models.Teacher;
import be.ehb.dtsid_inapp.Models.XmlHandler;
import be.ehb.dtsid_inapp.R;

/**
 *
 * @author Tom
 * @version 1.0
 *
 *
 */

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
    private MarkerOptions vlaamsBrabant, antwerpen, limburg, westVlaanderen, oostVlaanderen,
    luik, henegouwen, namen, luxemburg, waalsBrabant, brusselHoofdstad;
    private Event currentEvent;
    private Teacher currentTeacher;
    private Department currentDepartment;
    private Marker limburgMark, vlaamsBrabantMark, antwerpenMark, westVlaanderenMark, oostVlaanderenMark,
            luikMark, henegouwenMark, namenMark, luxemburgMark, waalsBrabantMark, brusselHoofdstadMark;
    private int currentYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        currentEvent = (Event) getIntent().getSerializableExtra("CurrentEvent");
        currentTeacher = (Teacher) getIntent().getSerializableExtra("CurrentTeacher");
        currentDepartment = (Department) getIntent().getSerializableExtra("CurrentDepartment");
        currentYear = getIntent().getIntExtra("CurrentYear", 0);

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

        allSubscriptions = dbc.getAllSubscriptions();
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
        CameraUpdate	cu	=	CameraUpdateFactory.newLatLngZoom(BRUSSEL_HOOFDSTAD, 8);
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

        vlaamsBrabant = new MarkerOptions()
                .position(VLAAMS_BRABANT)
                .title("Vlaams-Brabant")
                .icon(BitmapDescriptorFactory.fromResource(iconResource));
        vlaamsBrabantMark = mMap.addMarker(vlaamsBrabant);

        antwerpen = new MarkerOptions()
                .position(ANTWERPEN)
                .title("Antwerpen")
                .icon(BitmapDescriptorFactory.fromResource(iconResource));
        antwerpenMark = mMap.addMarker(antwerpen);

        limburg = new MarkerOptions()
                .position(LIMBURG)
                .title("Limburg")
                .icon(BitmapDescriptorFactory.fromResource(iconResource));
        limburgMark = mMap.addMarker(limburg);

        westVlaanderen = new MarkerOptions()
                .position(WEST_VLAANDEREN)
                .title("West-Vlaanderen")
                .icon(BitmapDescriptorFactory.fromResource(iconResource));
                westVlaanderenMark = mMap.addMarker(westVlaanderen);

        oostVlaanderen = new MarkerOptions()
                .position(OOST_VLAANDEREN)
                .title("Oost-Vlaanderen")
                .icon(BitmapDescriptorFactory.fromResource(iconResource));
                oostVlaanderenMark = mMap.addMarker(oostVlaanderen);

        brusselHoofdstad = new MarkerOptions()
                .position(BRUSSEL_HOOFDSTAD)
                .title("Brussel (19 gemeenten)")
                .icon(BitmapDescriptorFactory.fromResource(iconResource));
        brusselHoofdstadMark = mMap.addMarker(brusselHoofdstad);

        luik = new MarkerOptions()
                .position(LUIK)
                .title("Luik")
                .icon(BitmapDescriptorFactory.fromResource(iconResource));
                luikMark = mMap.addMarker(luik);

        waalsBrabant = new MarkerOptions()
                .position(WAALS_BRABANT)
                .title("Waals-Brabant")
                .icon(BitmapDescriptorFactory.fromResource(iconResource));
                waalsBrabantMark = mMap.addMarker(waalsBrabant);

        henegouwen = new MarkerOptions()
                .position(HENEGOUWEN)
                .title("Henegouwen")
                .icon(BitmapDescriptorFactory.fromResource(iconResource));
                henegouwenMark = mMap.addMarker(henegouwen);

        namen = new MarkerOptions()
                .position(NAMEN)
                .title("Namen")
                .icon(BitmapDescriptorFactory.fromResource(iconResource));
                namenMark = mMap.addMarker(namen);

        luxemburg = new MarkerOptions()
                .position(LUXEMBURG)
                .title("Luxemburg")
                .icon(BitmapDescriptorFactory.fromResource(iconResource));
                luxemburgMark = mMap.addMarker(luxemburg);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        subscriptions = new ArrayList<>();
        long selectedEventId = events.get(position).getId();
        for (Subscription index : allSubscriptions) {
            if (index.getEvent().getId().equals(selectedEventId)){
                subscriptions.add(index);
            }
        }
        setSubCountForProvince(antwerpenMark);
        setSubCountForProvince(vlaamsBrabantMark);
        setSubCountForProvince(limburgMark);
        setSubCountForProvince(westVlaanderenMark);
        setSubCountForProvince(oostVlaanderenMark);
        setSubCountForProvince(brusselHoofdstadMark);
        setSubCountForProvince(henegouwenMark);
        setSubCountForProvince(namenMark);
        setSubCountForProvince(waalsBrabantMark);
        setSubCountForProvince(luikMark);
        setSubCountForProvince(luxemburgMark);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setSubCountForProvince(Marker marker){
        String province = marker.getTitle();
        ArrayList<Gemeente> provinceZips = new ArrayList<>();
        ArrayList<Subscription> provinceSubs = new ArrayList<>();
        for (Gemeente indexGemeente : XmlHandler.gemeenteArrayList) {
            if (indexGemeente.getProvincie() != null && indexGemeente.getProvincie().equalsIgnoreCase(province)){
                provinceZips.add(indexGemeente);
            }
        }
        for (Subscription indexSub : subscriptions){
            for (Gemeente indexGemeente : provinceZips){
                if (indexSub.getZip().equals(indexGemeente.getZip())){
                    provinceSubs.add(indexSub);
                }
            }
        }
        if (provinceSubs.size() == 1)
            marker.setSnippet(provinceSubs.size() + " inschrijving");
        else
        marker.setSnippet(provinceSubs.size() + " inschrijvingen");
        marker.hideInfoWindow();
    }

    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent(this, TeacherActivity.class);
        backIntent.putExtra("CurrentEvent", currentEvent);
        backIntent.putExtra("CurrentDepartment", currentDepartment);
        backIntent.putExtra("CurrentTeacher", currentTeacher);
        backIntent.putExtra("CurrentYear", currentYear);
        startActivity(backIntent);
    }
}
