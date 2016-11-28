package com.kotkovets.fines.modules.list.assembly;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.kotkovets.fines.modules.list.interactor.DriversListInteractor;
import com.kotkovets.fines.modules.list.view.DriversListViewOutput;
import com.kotkovets.fines.modules.list.presenter.DriversListPresenter;
import com.kotkovets.fines.modules.list.router.ListRouter;
import com.kotkovets.fines.modules.list.view.DriversListViewInput;
import com.kotkovets.fines.modules.utils.settings.PreferencesStorage;
import com.kotkovets.fines.services.store.realm.RealmDatabase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by igork on 8/6/16.
 */
@Module
public class ListModule {
    private Activity activity;

    public ListModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    DriversListViewOutput providesPresenter() {
        DriversListPresenter presenter = new DriversListPresenter(activity);
        DriversListInteractor interactor = new DriversListInteractor();
        interactor.setContext(activity);
        interactor.setDatabase(new RealmDatabase());
        ListRouter router = new ListRouter((AppCompatActivity) activity);
        presenter.setView((DriversListViewInput) activity);
        presenter.setInteractor(interactor);
        presenter.setRouter(router);
        presenter.setSettings(new PreferencesStorage(activity));
        interactor.setOutput(presenter);
        return presenter;
    }
}
