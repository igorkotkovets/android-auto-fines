package com.kotkovets.fines.modules.list.assembly;

import com.kotkovets.fines.modules.list.view.DriversListActivity;

import dagger.Component;

/**
 * Created by igork on 8/6/16.
 */
@Component(modules = {ListModule.class})
public interface ListComponent {
    void inject(DriversListActivity activity);
}
