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

    private boolean bayloAcc;

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

        checkAcc();
    }

    // checking if user logged in ------------------------------------------------------------------------------------
    private void checkAcc(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            bayloAcc = extras.getBoolean("key");
        }
    }

    // method if "<" button is pressed ------------------------------------------------------------------------------------
    public void backFromProduct(View v) {
        finish();
    }

    // method if "Baylo" button is pressed ------------------------------------------------------------------------------------
    public void goBaylo(View v) {
        if (bayloAcc){
            Intent i = new Intent(ProductPageActivity.this, MessageActivity.class);
            startActivity(i);
        }
        else{
            Intent i = new Intent(ProductPageActivity.this, AccountPromptActivity.class);
            startActivity(i);
        }
    }

    // creating a circle around approximate location ------------------------------------------------------------------------------------
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.getUiSettings().setScrollGesturesEnabled(false);
        LatLng location = new LatLng(7.115294, 125.639103);
        drawCircle(location, googleMap);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 12.0f));
    }

    private void drawCircle(LatLng point, GoogleMap map){
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(point)
                .radius(2000)
                .strokeColor(Color.BLACK)
                .fillColor(0x30000000)
                .strokeWidth(5);
        map.addCircle(circleOptions);
    }
}