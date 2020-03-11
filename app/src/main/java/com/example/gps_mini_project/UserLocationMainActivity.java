package com.example.gps_mini_project;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class UserLocationMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, LocationListener {
     GoogleMap mMap;
     AppBarConfiguration mAppBarConfiguration;
    FirebaseAuth auth;
    GoogleApiClient client;
    LocationRequest request;
    LatLng latLng;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_location_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        auth=FirebaseAuth.getInstance();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_location_main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;



        client = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        client.connect();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_joinCircle) {

        } else if (id == R.id.nav_myCircle) {

        } else if (id == R.id.nav_signout) {

        } else if (id == R.id.nav_shareLocation) {

        } else if (id == R.id.nav_inviteMembers) {

        } else if (id == R.id.nav_signout) {
            FirebaseUser user = auth.getCurrentUser();
            Toast.makeText(getApplicationContext(), "Sign Out", Toast.LENGTH_SHORT).show();
            if (user != null) {
                auth.signOut();

                Intent i = new Intent(UserLocationMainActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }

        }

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nav_signout) {
            FirebaseUser user = auth.getCurrentUser();
            Toast.makeText(getApplicationContext(), "Sign Out", Toast.LENGTH_SHORT).show();
            if (user != null) {
                auth.signOut();

                Intent i = new Intent(UserLocationMainActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
            //  if(id==)
            return super.onOptionsItemSelected(item);
        }
        return true;
    }


        public void onConnected(@Nullable Bundle bundle) {
        new LocationRequest();
        request= LocationRequest.create();
    request.setInterval(1000);
    request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
    {
        return;
    }

    LocationServices.FusedLocationApi.requestLocationUpdates(client,request,this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        if(location==null)
        {
            Toast.makeText(getApplicationContext(),"Could not find Location",Toast.LENGTH_LONG).show();
        }
        else
        {
            latLng=new LatLng(location.getLatitude(),location.getLongitude());
            MarkerOptions options=new MarkerOptions();
            options.position(latLng);
            options.title("Current Location");
            mMap.addMarker(options);

        }
    }
}
