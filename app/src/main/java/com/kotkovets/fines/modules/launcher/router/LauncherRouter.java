package com.kotkovets.fines.modules.launcher.router;

import android.content.Context;
import android.content.Intent;

import com.kotkovets.fines.modules.list.view.DriversListActivity;

/**
 * Created by igork on 6/19/16.
 */
public class LauncherRouter implements LauncherRouterInput {
    Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * LauncherRouterInterface
     */

    @Override
    public void presentList() {
        Intent intent = new Intent(context, DriversListActivity.class);
        context.startActivity(intent);
    }


}
