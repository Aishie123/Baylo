package mcm.edu.ph.baylo.Controller;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends androidx.appcompat.widget.AppCompatTextView {

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        if (style == 1){
            tf = Typeface.createFromAsset(getContext().getApplicationContext().getAssets(), "dosis_extrabold.otf");
        }else{
            tf = Typeface.createFromAsset(getContext().getApplicationContext().getAssets(), "dosis_semiboldL.otf");
        }
        super.setTypeface(tf, style);
    }
}