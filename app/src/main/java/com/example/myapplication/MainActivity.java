package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.LocaleList;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION_PERMISSION = 1;

    private LocationManager locationManager;
    private LocationListener locationListener;
    private TextView appMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        loadComponents();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loadComponents() {
        appMessage = findViewById(R.id.app_message);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Button buttonUpdateLocation = findViewById(R.id.btn_update_location);
        buttonUpdateLocation.setOnClickListener(button -> {
            updateLocation();
        });

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                @SuppressLint("DefaultLocale") String text = String.format("Latitude: %.2f | Longitude: %.2f", location.getLatitude(), location.getLongitude());
                appMessage.setText(text);
            }
        };
    }

    private void updateLocation() {
        if (!checkLocationPermission()) {
            requestLocationPermission();
        }

        if (!checkLocationPermission()) {
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10, locationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode != REQUEST_LOCATION_PERMISSION) {
            return;
        }

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            return;
        }

        appMessage.setText("Vc negou a permissão amigo...");
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, REQUEST_LOCATION_PERMISSION);
    }

    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
}