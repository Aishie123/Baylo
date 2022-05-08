package mcm.edu.ph.baylo.View.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import mcm.edu.ph.baylo.R;

public class SignUp1Activity extends AppCompatActivity {

    private TextView txtTerms;
    private EditText etSUPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide the action bar
        setContentView(R.layout.activity_signup1);

        initUI();
        changeTypeface();
    }

    // method for showing and hiding password ------------------------------------------------------------------------------------

    public void showHideSUPass(View v) {

        if(v.getId()==R.id.imgBtnSUShowPass){
            if(etSUPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(v)).setImageResource(R.drawable.ic_visibility_off);
                //Show Password
                etSUPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(v)).setImageResource(R.drawable.ic_visibility);
                //Hide Password
                etSUPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }

    // method if "<" button is pressed ------------------------------------------------------------------------------------
    public void backSU1(View v) {
        Intent i = new Intent(SignUp1Activity.this, MainActivity.class);
        finish();
        i.putExtra("key",false);
        startActivity(i);
    }

    // method if "next" button is pressed ------------------------------------------------------------------------------------
    public void nextPage(View v) {
        Intent i = new Intent(SignUp1Activity.this, SignUp2Activity.class);
        startActivity(i);
    }

    // methods for typeface ------------------------------------------------------------------------------------------------
    private void changeTypeface(){
        Spannable span = new SpannableString("By registering, you agree to our Terms and Conditions and Privacy Policy.");
        span.setSpan(new StyleSpan(Typeface.BOLD), 33, 54, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new StyleSpan(Typeface.BOLD), 58, 72, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtTerms.setText(span);
    }

    // initializing UI ------------------------------------------------------------------------------------
    private void initUI() {
        txtTerms = findViewById(R.id.txtSignUp1Terms);
        etSUPassword = findViewById(R.id.etSUPassword);
        etSUPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }
}