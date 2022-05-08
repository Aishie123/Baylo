package mcm.edu.ph.baylo.View.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import mcm.edu.ph.baylo.R;

public class ProductPageActivity extends AppCompatActivity implements OnMapReadyCallback {

    CircleOptions circleOptions = new CircleOptions();
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); // for layout to overlap with status bar
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_product_page);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    // method if "<" button is pressed ------------------------------------------------------------------------------------
    public void backFromProduct(View v) {
        finish();
    }

    // method if "Baylo" button is pressed ------------------------------------------------------------------------------------
    public void goBaylo(View v) {
        Intent i = new Intent(ProductPageActivity.this, MessageActivity.class);
        startActivity(i);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setScrollGesturesEnabled(false);
        LatLng location = new LatLng(7.115294, 125.639103);
        drawCircle(location, map);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 12.0f));
    }

    private void drawCircle(LatLng point, GoogleMap map){
        // Instantiating CircleOptions to draw a circle around the marker
        CircleOptions circleOptions = new CircleOptions();
        // Specifying the center of the circle
        circleOptions.center(point);
        // Radius of the circle
        circleOptions.radius(2000);
        // Border color of the circle
        circleOptions.strokeColor(Color.BLACK);
        // Fill color of the circle
        circleOptions.fillColor(0x30000000);
        // Border width of the circle
        circleOptions.strokeWidth(5);
        // Adding the circle to the GoogleMap
        map.addCircle(circleOptions);
    }
}