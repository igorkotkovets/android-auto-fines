package com.kotkovets.fines.modules.launcher.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kotkovets.fines.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class LauncherActivityFragment extends Fragment {

    public LauncherActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_launcher, container, false);
    }
}
