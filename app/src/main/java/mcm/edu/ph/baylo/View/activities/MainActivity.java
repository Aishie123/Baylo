package mcm.edu.ph.baylo.View.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import mcm.edu.ph.baylo.R;
import mcm.edu.ph.baylo.View.fragments.AccountPromptFragment;
import mcm.edu.ph.baylo.View.fragments.LovesFragment;
import mcm.edu.ph.baylo.View.fragments.HomeFragment;
import mcm.edu.ph.baylo.View.fragments.ChatsFragment;
import mcm.edu.ph.baylo.View.fragments.MeFragment;

public class MainActivity extends AppCompatActivity {

    private boolean bayloAcc = false;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide the action bar

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            bayloAcc = extras.getBoolean("key");
        }
        //NavHostFragment navHostFragment =
        //        (NavHostFragment) getSupportFragmentManager()
        //                .findFragmentById(R.id.nav_host_fragment_activity_main);
        //BottomNavigationView navView = findViewById(R.id.nav_view);
        //NavController navController = navHostFragment.getNavController();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(navView, navController);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                fragment = null;
                switch (item.getItemId())
                {
                    case R.id.navigation_home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.navigation_loves:
                        if (bayloAcc) { fragment = new LovesFragment(); }
                        else { fragment = new AccountPromptFragment(); }
                        break;

                    case R.id.navigation_chats:
                        if (bayloAcc) { fragment = new ChatsFragment(); }
                        else { fragment = new AccountPromptFragment(); }
                        break;

                    case R.id.navigation_me:
                        if (bayloAcc) { fragment = new MeFragment(); }
                        else { fragment = new AccountPromptFragment(); }
                        break;

                    default:
                        Toast.makeText(MainActivity.this, "Item can't be opened",
                                Toast.LENGTH_LONG).show();
                        break;
                }
                replaceFragment();
                return true;
            }
        });

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

    private void replaceFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.addToBackStack(null);
        if (fragment != null) {
            transaction.replace(R.id.container, fragment, null);
        }
        transaction.commit();
    }
    

}