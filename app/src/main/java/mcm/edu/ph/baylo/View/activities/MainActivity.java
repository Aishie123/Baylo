package mcm.edu.ph.baylo.View.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import mcm.edu.ph.baylo.R;
import mcm.edu.ph.baylo.View.fragments.AccountPromptFragment;
import mcm.edu.ph.baylo.View.fragments.LovesFragment;
import mcm.edu.ph.baylo.View.fragments.HomeFragment;
import mcm.edu.ph.baylo.View.fragments.ChatsFragment;
import mcm.edu.ph.baylo.View.fragments.MeFragment;

public class MainActivity extends AppCompatActivity {

    private boolean verified = false;

    final Fragment fragment1 = new HomeFragment();
    final Fragment fragment2 = new LovesFragment();
    final Fragment fragment3 = new ChatsFragment();
    final Fragment fragment4 = new MeFragment();
    final Fragment fragVerify = new AccountPromptFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @SuppressLint("UseSupportActionBar")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); // for layout to overlap with status bar
        }

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            verified = extras.getBoolean("key");
        }

        fm.beginTransaction().add(R.id.main_container, fragVerify, "5").hide(fragVerify).commit();
        fm.beginTransaction().add(R.id.main_container, fragment4, "4").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.main_container, fragment1, "1").commit();

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
                    if (verified){
                        fm.beginTransaction().hide(active).show(fragment2).commit();
                        active = fragment2;
                    }
                    else{ fm.beginTransaction().hide(active).show(fragVerify).commit(); active = fragVerify; }
                    return true;

                case R.id.navigation_chats:
                    if (verified){
                        fm.beginTransaction().hide(active).show(fragment3).commit();
                        active = fragment3;
                    }
                    else{ fm.beginTransaction().hide(active).show(fragVerify).commit(); active = fragVerify; }
                    return true;

                case R.id.navigation_me:
                    if (verified){
                        fm.beginTransaction().hide(active).show(fragment4).commit();
                        active = fragment4;
                    }
                    else{ fm.beginTransaction().hide(active).show(fragVerify).commit(); active = fragVerify; }
                    return true;
            }
            return false;
        }
    };

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

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

}