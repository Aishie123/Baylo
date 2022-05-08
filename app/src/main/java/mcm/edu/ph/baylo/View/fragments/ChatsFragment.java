package mcm.edu.ph.baylo.View.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import mcm.edu.ph.baylo.R;
import mcm.edu.ph.baylo.View.activities.MainActivity;

public class ChatsFragment extends Fragment {

    private View v;
    private SearchView searchView;
    private TextView searchText;
    private int id;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_chats, container, false);
        initUI();
        setSearchTypeface();
        return v;
    }

    private void setSearchTypeface(){
        searchText.setTextSize(14);
        Typeface tf = ResourcesCompat.getFont(v.getContext(), R.font.dosis_light);
        searchText.setTypeface(tf);
    }

    private void initUI() {
        searchView = v.findViewById(R.id.chatSearchView);
        id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        searchText = searchView.findViewById(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        v = null;
    }
}