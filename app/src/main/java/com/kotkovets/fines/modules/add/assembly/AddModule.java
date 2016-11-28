package com.kotkovets.fines.modules.add.assembly;

import android.app.Activity;

import com.kotkovets.fines.modules.add.interactor.AddInteractor;
import com.kotkovets.fines.modules.add.view.AddViewOutput;
import com.kotkovets.fines.modules.add.presentor.AddPresentor;
import com.kotkovets.fines.modules.add.router.AddRouter;
import com.kotkovets.fines.modules.add.router.AddRouterInput;
import com.kotkovets.fines.modules.add.view.AddViewInput;
import com.kotkovets.fines.services.store.realm.RealmDatabase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by igork on 8/7/16.
 */

@Module
public class AddModule {
    AddViewInput view;
    Activity activity;

    public AddModule(Activity activity, AddViewInput view) {
        this.activity = activity;
        this.view = view;
    }

    @Provides
    AddViewOutput providesViewOutput() {
        AddPresentor presentor = new AddPresentor();
        presentor.setView(view);
        presentor.setContext(activity);

        AddInteractor interactor = new AddInteractor();
        interactor.setOutput(presentor);
        interactor.setDatabaseStoreService(new RealmDatabase());

        presentor.setInteractor(interactor);

        AddRouterInput router = new AddRouter(activity);
        presentor.setRouter(router);

        return presentor;
    }
}
