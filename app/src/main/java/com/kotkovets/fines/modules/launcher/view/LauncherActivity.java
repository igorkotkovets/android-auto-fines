package com.kotkovets.fines.modules.launcher.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kotkovets.fines.R;
import com.kotkovets.fines.modules.launcher.assembly.DaggerLaunchComponent;
import com.kotkovets.fines.modules.launcher.assembly.LaunchModule;
import com.kotkovets.fines.modules.launcher.presenter.LauncherModuleInput;

import javax.inject.Inject;

public class LauncherActivity extends AppCompatActivity implements LauncherViewInput {

    @Inject LauncherModuleInput presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        DaggerLaunchComponent.builder().launchModule(new LaunchModule(this)).build().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.onViewDidAppear();
    }



}
