package mcm.edu.ph.baylo.View.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import mcm.edu.ph.baylo.R;

public class SignUp2Activity extends AppCompatActivity {
    private final Calendar myCalendar= Calendar.getInstance();
    private EditText etDOB;
    private TextView txtTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); // for layout to overlap with status bar
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_signup2);

        etDOB = findViewById(R.id.etBirthdate);

        initUI();
        getBirthdate();
        changeTypeface();

    }

    // method if "<" button is pressed ------------------------------------------------------------------------------------
    public void backSU2(View v) {
        finish();
    }

    // method if "Create Account" button is pressed ------------------------------------------------------------------------------------
    public void createAccount(View v) {
        Intent i = new Intent(SignUp2Activity.this, MainActivity.class);
        i.putExtra("key",true);
        finish();
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    // methods for birthdate ------------------------------------------------------------------------------------------------
    private void getBirthdate() {
        etDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(SignUp2Activity.this, R.style.my_dialog_theme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH,month);
                        myCalendar.set(Calendar.DAY_OF_MONTH,day);
                        updateLabel();
                    }
            }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        etDOB.setText(dateFormat.format(myCalendar.getTime()));
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
        txtTerms = findViewById(R.id.txtSignUp2Terms);
    }

}