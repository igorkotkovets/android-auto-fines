package com.kotkovets.fines.modules.add.assembly;

import com.kotkovets.fines.modules.add.view.AddUserFragment;

import dagger.Component;

/**
 * Created by igork on 8/7/16.
 */

@Component(modules = {AddModule.class})
public interface AddComponent {
    void inject(AddUserFragment fragment);
}
