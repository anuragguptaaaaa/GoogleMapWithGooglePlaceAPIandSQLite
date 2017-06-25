package com.anuragg.main_task;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener,PlaceSelectionListener {

    private GoogleMap mMap;
   // TextView tv;
    LocationManager locationManager;
    double lat=0.0,lon=0.0;
   // EditText location_tf;
    private TextView mPlaceDetailsText;

    private TextView mPlaceAttribution;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //tv = (TextView) findViewById(R.id.tv1);
       //location_tf = (EditText) findViewById(R.id.tfAddress);

        //mPlaceDetailsText = (TextView) findViewById(R.id.place_details);
       // mPlaceAttribution = (TextView) findViewById(R.id.place_attribution);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.autocomplete_fragment);


        autocompleteFragment.setOnPlaceSelectedListener(this);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng latLng=new LatLng(latitude,longitude);
                    Geocoder geocoder=new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList=  geocoder.getFromLocation(latitude,longitude,1);
                        String str=addressList.get(0).getLocality()+",";
                        str+=addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15.2f));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        }
        else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng latLng=new LatLng(latitude,longitude);
                    Geocoder geocoder=new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList=  geocoder.getFromLocation(latitude,longitude,1);
                        String str=addressList.get(0).getLocality()+",";
                        str+=addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15.2f));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        }


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
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(false);
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                  mMap.clear();
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    //return TODO;
                }
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, MapsActivity.this);

                return false;
            }
        });

    }


    List<Address> addressList;
    Address address;

    /*public void onSearch(View v) {
        mMap.clear();

      //  final String location1 = location_tf.getText().toString().trim();
        if(!location1.isEmpty())
        {
        if (location1 != null || !location1.endsWith("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location1, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title(location1));
            lat=address.getLatitude();
            lon=address.getLongitude();
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            tv.setText(lat+"\n"+lon);

        }


        }
        else
        {
            location_tf.setError("Empty");
            Toast.makeText(this, "Please Enter Address", Toast.LENGTH_SHORT).show();
        }


    }*/
    public void onChange(View v)
    {
        if(mMap.getMapType()==GoogleMap.MAP_TYPE_NORMAL)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        else
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }
    public void onZoom(View v)
    {
        if(v.getId() ==R.id.bZoomin)
        {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
        if(v.getId()==R.id.bZoomout)
        {
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }
    public void wel(View v)
    {

        Intent i=new Intent(this,DetailActivity.class);
        i.putExtra("lat1",""+lat);
        i.putExtra("lon1",""+lon);
        startActivity(i);
       finish();
    }

    @Override
    public void onLocationChanged(Location location) {
        mMap.clear();
        LatLng  latLng=new LatLng(location.getLatitude(),location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng).title("current"));
        lat=location.getLatitude();
        lon=location.getLongitude();
       // tv.setText(lat+"\n"+lon);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onPlaceSelected(Place place) {
      //  Log.i(TAG, "Place Selected: " + place.getName());

        // Format the returned place's details and display them in the TextView.
        //location_tf.setText(formatPlaceDetails(getResources(), place.getName(), place.getId(),place.getAddress(), place.getPhoneNumber(), place.getWebsiteUri()));
        mMap.clear();

        final String location1 = String.valueOf(formatPlaceDetails(getResources(), place.getName(), place.getId(),
                place.getAddress(), place.getPhoneNumber(), place.getWebsiteUri()));
        if(!location1.isEmpty())
        {
            if (location1 != null || !location1.endsWith("")) {
                Geocoder geocoder = new Geocoder(this);
                try {
                    addressList = geocoder.getFromLocationName(location1, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                address = addressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title(location1));
                lat=address.getLatitude();
                lon=address.getLongitude();
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
              //  tv.setText(lat+"\n"+lon);

            }


        }




        CharSequence attributions = place.getAttributions();
        if (!TextUtils.isEmpty(attributions)) {
           // mPlaceAttribution.setText(Html.fromHtml(attributions.toString()));
        } else {
           // mPlaceAttribution.setText("");
        }
    }

    @Override
    public void onError(Status status) {
        Toast.makeText(this, "Place selection failed: " + status.getStatusMessage(),
                Toast.LENGTH_SHORT).show();
    }
    private static Spanned formatPlaceDetails(Resources res, CharSequence name, String id,
                                              CharSequence address, CharSequence phoneNumber, Uri websiteUri) {
        //Log.e(TAG, res.getString(R.string.place_details, name, id, address, phoneNumber,websiteUri));
        //return Html.fromHtml(res.getString(R.string.place_details, name, id, address, phoneNumber,websiteUri));
        return Html.fromHtml(res.getString(R.string.place_details1, name, id, address));

    }
}
