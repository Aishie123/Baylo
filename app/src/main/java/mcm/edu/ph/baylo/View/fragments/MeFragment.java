package mcm.edu.ph.baylo.View.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import mcm.edu.ph.baylo.R;

public class MeFragment extends Fragment {

    private View v;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_me, container, false);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        v = null;
    }

}