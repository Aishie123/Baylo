package mcm.edu.ph.baylo.View.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import mcm.edu.ph.baylo.R;

public class LogInActivity extends AppCompatActivity {

    private EditText etLIPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide the action bar
        setContentView(R.layout.activity_login);

        initUI();
    }


    // method for showing and hiding password ------------------------------------------------------------------------------------

    public void showHideLIPass(View v) {
        if(v.getId()==R.id.imgBtnLIShowPass){
            if(etLIPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(v)).setImageResource(R.drawable.ic_visibility_off);
                //Show Password
                etLIPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(v)).setImageResource(R.drawable.ic_visibility);
                //Hide Password
                etLIPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }

    // method if "<" button is pressed ------------------------------------------------------------------------------------
    public void backLI(View v) {
        Intent i = new Intent(LogInActivity.this, MainActivity.class);
        finish();
        i.putExtra("key",false);
        startActivity(i);
    }

    // method if "next" button is pressed ------------------------------------------------------------------------------------
    public void logIn(View v) {
        Intent i = new Intent(LogInActivity.this, MainActivity.class);
        finish();
        i.putExtra("key",true);
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    // initializing UI ------------------------------------------------------------------------------------
    private void initUI() {
        etLIPassword = findViewById(R.id.etLIPassword);
        etLIPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

}