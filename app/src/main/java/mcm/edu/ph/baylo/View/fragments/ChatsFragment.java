package mcm.edu.ph.baylo.View.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import mcm.edu.ph.baylo.R;

public class ChatsFragment extends Fragment {

    private View v;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_chats, container, false);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        v = null;
    }
}