package bicepsa.vanko.traveling_project;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback ,
        GoogleMap.OnMarkerClickListener {


    private GoogleMap mMap;
    Marker myMarker;
    String lat;
    String longit;

    String loc_name;
    String country;
    String web_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Button button = (Button)findViewById(R.id.new_dest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(getApplicationContext(),NewDestination.class);
                startActivity(start);
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        Bundle bundle = getIntent().getExtras();


        if (bundle != null) {
            lat = bundle.getString("lat");
            longit = bundle.getString("longit");
            loc_name = bundle.getString("name");
            country = bundle.getString("country");
            web_info = bundle.getString("web_info");

            SharedPreferences data = getSharedPreferences("DestinationData",MODE_PRIVATE);
            SharedPreferences.Editor editor = data.edit();
            editor.putString("lat",lat);
            editor.putString("longit",longit);
            editor.putString("name",loc_name);
            editor.putString("country",country);
            editor.putString("web_info",web_info);
            editor.apply();
            addmarker(lat,longit);
        }else{
            SharedPreferences data = getSharedPreferences("DestinationData",MODE_PRIVATE);
            if(data.contains("lat") && data.contains("longit") && data.contains("name")
                    && data.contains("country") && data.contains("web_info")){
                lat = data.getString("lat","0");
                longit = data.getString("longit","0");
                loc_name = data.getString("name","N/A");
                country = data.getString("country","N/A");
                web_info = data.getString("web_info","N/A");
                addmarker(lat,longit);
            }else{
                Toast.makeText(this, "Pick new destination", Toast.LENGTH_LONG).show();
            }
        }

    }



    private void addmarker(String lat, String longit) {

        LatLng location = new LatLng(Float.parseFloat(lat),Float.parseFloat(longit));

        MarkerOptions options = new MarkerOptions().position(location).title(loc_name);
        myMarker = mMap.addMarker(options);

        mMap.setOnMarkerClickListener(this);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }



    @Override
    public boolean onMarkerClick(Marker marker) {

        Intent start = new Intent(getApplicationContext(),Information.class);
        start.putExtra("name",loc_name);
        start.putExtra("country",country);
        start.putExtra("web_info",web_info);
        startActivity(start);

        return true;
    }


}
