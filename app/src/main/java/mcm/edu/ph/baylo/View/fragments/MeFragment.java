package mcm.edu.ph.baylo.View.fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import mcm.edu.ph.baylo.R;
import mcm.edu.ph.baylo.View.activities.MainActivity;

@SuppressWarnings("ConstantConditions")
public class MeFragment extends Fragment {

    private View v;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_me, container, false);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.options_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.option_signout:
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!getActivity().isFinishing()){
                                    new AlertDialog.Builder(v.getContext())
                                            .setTitle("Sign Out")
                                            .setMessage("Are you sure you want to sign out?")
                                            .setCancelable(false)
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    getActivity().finish();
                                                    Intent i = new Intent(getActivity(), MainActivity.class);
                                                    i.putExtra("key", false);
                                                    startActivity(i);
                                                    getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                                }
                                            })
                                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Toast.makeText(v.getContext(),"You remained signed in.",Toast.LENGTH_LONG).show();
                                                }
                                            })
                                            .show();
                                }
                            }
                        });
                        return true;

                    default:
                        Toast.makeText(v.getContext(),"This option is not yet available.",Toast.LENGTH_LONG).show();
                        return false;
            }

        }
    });
    return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        v = null;
    }

}