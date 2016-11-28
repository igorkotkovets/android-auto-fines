package com.kotkovets.fines.modules.list.router;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.kotkovets.fines.modules.add.view.AddUserActivity;
import com.kotkovets.fines.modules.userguide.view.UserGuideFragment;

/**
 * Created by igork on 4/23/16.
 */
public class ListRouter implements ListRouterInput {
    AppCompatActivity context;

    public ListRouter(AppCompatActivity context) {
        this.context = context;
    }

    @Override
    public void openAddNewUser() {
        Intent intent = new Intent(context, AddUserActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void openUserGuide() {
        FragmentManager fm = context.getSupportFragmentManager();
        UserGuideFragment alertDialog = new UserGuideFragment();
        alertDialog.show(fm, "fragment_alert");
    }
}
