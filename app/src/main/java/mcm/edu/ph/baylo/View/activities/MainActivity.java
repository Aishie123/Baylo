package mcm.edu.ph.baylo.View.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ui.AppBarConfiguration;

import mcm.edu.ph.baylo.R;
import mcm.edu.ph.baylo.View.fragments.AccountPromptFragment;
import mcm.edu.ph.baylo.View.fragments.LovesFragment;
import mcm.edu.ph.baylo.View.fragments.HomeFragment;
import mcm.edu.ph.baylo.View.fragments.ChatsFragment;
import mcm.edu.ph.baylo.View.fragments.MeFragment;

public class MainActivity extends AppCompatActivity {

    private boolean bayloAcc = false;
    private BottomNavigationView navigation;
    private final Fragment fragment1 = new HomeFragment();
    private final Fragment fragment2 = new LovesFragment();
    private final Fragment fragment3 = new ChatsFragment();
    private final Fragment fragment4 = new MeFragment();
    private final Fragment fragVerify = new AccountPromptFragment();
    private final FragmentManager fm = getSupportFragmentManager();
    private Fragment active = fragment1;
    protected Dialog mSplashDialog;

    @SuppressLint("UseSupportActionBar")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); // for layout to overlap with status bar
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        MyStateSaver data = (MyStateSaver) getLastNonConfigurationInstance();
        if (data != null) {
            // Show splash screen if still loading
            if (data.showSplashScreen) {
                showSplashScreen();
            }
            initUI();
            checkAcc();
        } else {
            showSplashScreen();
            initUI();
            checkAcc();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;

                case R.id.navigation_loves:
                    if (bayloAcc){
                        fm.beginTransaction().hide(active).show(fragment2).commit();
                        active = fragment2;
                    }
                    else{ fm.beginTransaction().hide(active).show(fragVerify).commit(); active = fragVerify; }
                    return true;

                case R.id.navigation_chats:
                    if (bayloAcc){
                        fm.beginTransaction().hide(active).show(fragment3).commit();
                        active = fragment3;
                    }
                    else{ fm.beginTransaction().hide(active).show(fragVerify).commit(); active = fragVerify; }
                    return true;

                case R.id.navigation_me:
                    if (bayloAcc){
                        fm.beginTransaction().hide(active).show(fragment4).commit();
                        active = fragment4;
                    }
                    else{ fm.beginTransaction().hide(active).show(fragVerify).commit(); active = fragVerify; }
                    return true;
            }
            return false;
        }
    };

    // initializing UI ------------------------------------------------------------------------------------
    private void initUI() {
        setContentView(R.layout.activity_main);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.main_container, fragVerify, "5").hide(fragVerify).commit();
        fm.beginTransaction().add(R.id.main_container, fragment4, "4").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.main_container, fragment1, "1").commit();
    }

    // checking if user logged in ------------------------------------------------------------------------------------
    private void checkAcc(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            bayloAcc = extras.getBoolean("key");
        }
    }

    // onClick methods ------------------------------------------------------------------------------------

    public void openItem(View v) {
        Intent i = new Intent(MainActivity.this, ProductPageActivity.class);
        i.putExtra("key", bayloAcc);
        startActivity(i);
    }

    public void openChat(View v) {
        Intent i = new Intent(MainActivity.this, MessageActivity.class);
        startActivity(i);
    }

    public void goToSignIn(View v){
        Intent i = new Intent(MainActivity.this, LogInActivity.class);
        finish();
        startActivity(i);
    }

    public void goToSignUp(View v){
        Intent i = new Intent(MainActivity.this, SignUp1Activity.class);
        finish();
        startActivity(i);
    }

    // methods for splash screen --------------------------------------------------------------------------------------------------------------------

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        MyStateSaver data = new MyStateSaver();

        if (mSplashDialog != null) {
            data.showSplashScreen = true;
            removeSplashScreen();
        }
        return data;
    }

    // Removes the Dialog that displays the splash screen
    protected void removeSplashScreen() {
        if (mSplashDialog != null) {
            mSplashDialog.dismiss();
            mSplashDialog = null;
        }
    }

    // Shows the splash screen over the full Activity
    protected void showSplashScreen() {
        mSplashDialog = new Dialog(this, R.style.SplashScreen);
        mSplashDialog.setContentView(R.layout.splashscreen);

        mSplashDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        mSplashDialog.setCancelable(false);
        mSplashDialog.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                removeSplashScreen();
            }
        }, 4100);
    }

    private class MyStateSaver {
        public boolean showSplashScreen = false;
    }
}