package mcm.edu.ph.baylo.View.activities;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import mcm.edu.ph.baylo.R;
import mcm.edu.ph.baylo.View.fragments.LovesFragment;
import mcm.edu.ph.baylo.View.fragments.HomeFragment;
import mcm.edu.ph.baylo.View.fragments.ChatsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide the action bar

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                Fragment fragment = null;
                switch (item.getItemId())
                {
                    case R.id.navigation_home: fragment = new HomeFragment(); break;
                    case R.id.navigation_loves: fragment = new LovesFragment(); break;
                    case R.id.navigation_chats: fragment = new ChatsFragment(); break;

                    default:
                        Toast.makeText(MainActivity.this, "Item can't be opened",
                                Toast.LENGTH_LONG).show();
                        break;
                }


                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                transaction.addToBackStack(null);
                if (fragment != null) {
                    transaction.replace(R.id.container, fragment, null);
                }
                transaction.commit();
                return true;
            }
        });

        NavigationUI.setupWithNavController(navView, navController);
    }

}