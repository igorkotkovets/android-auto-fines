package com.kotkovets.fines.modules.add.router;

import android.app.Activity;

/**
 * Created by igork on 4/23/16.
 */
public class AddRouter implements AddRouterInput {
    Activity activity;

    public AddRouter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void openUsersList() {
        activity.finish();
    }
}
