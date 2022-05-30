package mcm.edu.ph.baylo.View.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import mcm.edu.ph.baylo.R;

public class HomeFragment extends Fragment {

    private View v;
    private ScrollView scrollView;
    private SearchView searchView;
    private TextView searchText;
    private int id;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);

        initUI(); // initializing UI
        hideScrollBar();
        setSearchTypeface();
        return v;
    }

    // changing the typeface of the search bar ------------------------------------------------------------------------------
    private void setSearchTypeface(){
        searchText.setTextSize(14);
        Typeface tf = ResourcesCompat.getFont(v.getContext(), R.font.dosis_light);
        searchText.setTypeface(tf);
    }

    // initializing UI ------------------------------------------------------------------------------
    private void initUI() {
        searchView = v.findViewById(R.id.homeSearchView);
        id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        searchText = searchView.findViewById(id);
        scrollView = v.findViewById(R.id.homeScrollView);
    }

    // hiding scroll bar ------------------------------------------------------------------------------
    private void hideScrollBar() {
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);
    }

    // -----------------------------------------------------------------------------------------------
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        v = null;
    }
}