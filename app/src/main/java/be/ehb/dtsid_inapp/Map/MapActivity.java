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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import be.ehb.dtsid_inapp.R;

public class MapActivity extends Activity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private MapFragment mMapFragment;
    private TextView regiosTV;
    private	Spinner	regiosSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Log.d("MapTest", "What the hell is going on?");

        mMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.frgm_regios_map);
        mMapFragment.getMapAsync(this);


    }

    @Override
    public	void	onMapReady(GoogleMap	googleMap)	{
        Toast.makeText(getApplicationContext(), "Map	loaded	and	ready", Toast.LENGTH_SHORT).show();
        mMap	=	googleMap;
        mMap.setOnMapClickListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        LatLng	belgCoord	=	new	LatLng(50.846102,	4.3453741);
        CameraUpdate	cu	=	CameraUpdateFactory.newLatLngZoom(belgCoord,	8);
        mMap.animateCamera(cu);
        addMarkers();
    }
    private	void	addMarkers()	{
        LatLng	erasmusCoord	=	new	LatLng(50.841836,	4.322847);
        mMap.addMarker(new MarkerOptions()
                .position(erasmusCoord)
                .title("Erasmus	Hogeschool	Brussel")
                .snippet("Opleidingen	Dig-x	&	Multec")
                .icon(BitmapDescriptorFactory.defaultMarker(200)));
    }
    @Override
    public	void	onMapClick(LatLng	latLng)	{
        Toast.makeText(getApplicationContext(),	"coord,	lat	=	"	+	latLng.latitude	+	"	long="	+
                latLng.longitude,	Toast.LENGTH_LONG).show();
    }
    @Override
    public	boolean	onMarkerClick(Marker	marker)	{
        if	(marker.getTitle().equals("Erasmus	Hogeschool	Brussel"))	{
            Toast.makeText(getApplicationContext(),	"Word	wat	jij	wil!",	Toast.LENGTH_LONG).show();
        }
        return	false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }
}
