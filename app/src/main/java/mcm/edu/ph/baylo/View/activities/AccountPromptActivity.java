package mcm.edu.ph.baylo.View.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import mcm.edu.ph.baylo.R;

public class AccountPromptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); // for layout to overlap with status bar
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setContentView(R.layout.activity_account_prompt);
    }

    // method if "<" button is pressed ------------------------------------------------------------------------------------
    public void backToProduct(View v) {
        finish();
    }

    // onClick methods ------------------------------------------------------------------------------------

    public void login(View v){
        Intent i = new Intent(AccountPromptActivity.this, LogInActivity.class);
        finish();
        startActivity(i);
    }

    public void signup(View v){
        Intent i = new Intent(AccountPromptActivity.this, SignUp1Activity.class);
        finish();
        startActivity(i);
    }

}