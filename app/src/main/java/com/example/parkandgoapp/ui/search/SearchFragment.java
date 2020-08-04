package com.example.parkandgoapp.ui.search;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.parkandgoapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment implements OnMapReadyCallback{
    private MapView mapView;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_search, container, false);
            //SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);

            mapView = root.findViewById(R.id.map);
            mapView.onCreate(savedInstanceState);
            mapView.onResume();
            mapView.getMapAsync(this);

            mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
            searchView= root.findViewById(R.id.searchView);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    geoLocate();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
            return root;
        }

    private GoogleMap mMap;
    private final LatLng mDefaultLocation=new LatLng(0,0);
    private final static int DEFAULT_ZOOM=15;
    private static final int PERMISSION_REQUEST_ACCESS_FINE_LOCATION=1;
    private boolean mLocationPermissionGranted;
    private Location mLastKnownLocation;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final String TAG="MapsActivity";
    private SearchView searchView;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation,DEFAULT_ZOOM));
        this.getLocationPermission();
        this.updateLocationUI();
        this.getDeviceLocation();
    }


    private void getLocationPermission(){
        if(ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            mLocationPermissionGranted=true;
        }else{
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    private void updateLocationUI(){
        if(mMap==null){
            return;
        }
        try{
            if(mLocationPermissionGranted){
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            }else{
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation=null;
                getLocationPermission();
            }

        }catch(SecurityException e){
            Log.e(TAG+"Exception: ",e.getMessage());

        }
    }

    private void geoLocate(){
        String searchString=searchView.getQuery().toString();
        Geocoder geocoder=new Geocoder(getActivity());
        List<Address> addressList=new ArrayList<>();
        try{
            addressList=geocoder.getFromLocationName(searchString,1);
        }catch(Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
        }

        Log.e(TAG, addressList.toString());
        if(addressList.size()>0){
            Address foundAdderess=addressList.get(0);
            foundAdderess.getCountryName();
            foundAdderess.getSubAdminArea(); //city
            foundAdderess.getThoroughfare(); //street
            foundAdderess.getPostalCode();
            LatLng foundAddress=new LatLng(foundAdderess.getLatitude(),foundAdderess.getLongitude());
            //mMap.clear();
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(foundAddress,DEFAULT_ZOOM));
            //mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
            mMap.addMarker(new MarkerOptions().position(foundAddress).title(searchString));
        }
    }


    private void getDeviceLocation(){
        try{
            if(mLocationPermissionGranted){
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if(task.isSuccessful()){
                            mLastKnownLocation=task.getResult();
                            LatLng currentLocation=new LatLng(mLastKnownLocation.getLatitude(),mLastKnownLocation.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,DEFAULT_ZOOM));
                            mMap.addMarker(new MarkerOptions().position(currentLocation).title("You are here!")).showInfoWindow();
                            Log.e(TAG,"Lat: "+currentLocation.latitude+"Lng: "+currentLocation.longitude);
                        }else{
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation,DEFAULT_ZOOM));
                            mMap.addMarker(new MarkerOptions().position(mDefaultLocation).title("You are here!")).showInfoWindow();
                            Log.e(TAG,"Lat: "+mDefaultLocation.latitude+"Lng: "+mDefaultLocation.longitude);
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        }catch(SecurityException e){
            Log.e(TAG+"Exception",e.getMessage());
        }

    }
}
