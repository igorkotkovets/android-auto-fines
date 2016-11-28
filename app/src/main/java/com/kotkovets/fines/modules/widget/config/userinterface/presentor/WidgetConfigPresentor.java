package com.kotkovets.fines.modules.widget.config.userinterface.presentor;

import android.app.Activity;

import com.kotkovets.fines.common.BasePresenter;
import com.kotkovets.fines.common.DriverDisplaySerializer;
import com.kotkovets.fines.common.display.DriverDisplayItem;
import com.kotkovets.fines.common.model.Driver;
import com.kotkovets.fines.modules.widget.config.applicationlogic.interactor.WidgetConfigInteractor;
import com.kotkovets.fines.modules.widget.config.applicationlogic.interactor.WidgetConfigInteractorInput;
import com.kotkovets.fines.modules.widget.config.applicationlogic.interactor.WidgetConfigInteractorOutput;
import com.kotkovets.fines.modules.widget.config.applicationlogic.router.WidgetConfigRouter;
import com.kotkovets.fines.modules.widget.config.applicationlogic.router.WidgetConfigRouterInterface;
import com.kotkovets.fines.modules.widget.config.moduleinterface.WidgetConfigModuleInterface;
import com.kotkovets.fines.modules.widget.config.userinterface.view.WidgetConfigViewInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by igork on 4/24/16.
 */
public class WidgetConfigPresentor extends BasePresenter<WidgetConfigViewInterface> implements WidgetConfigModuleInterface, WidgetConfigInteractorOutput {
    WidgetConfigInteractorInput interactor;
    WidgetConfigRouterInterface router;
    int widgetId;
    Activity context;


    public WidgetConfigPresentor(Activity context, int widgetId) {
        this.context = context;
        this.widgetId = widgetId;
        router = new WidgetConfigRouter(context, widgetId);
        interactor = new WidgetConfigInteractor(context, this);
    }

    /**
     * WidgetConfigModuleInterface
     */

    @Override
    public void updateView() {
        interactor.loadUsers();
    }

    @Override
    public void selectAction(int userId) {
        interactor.updateWidgetWithUserId(userId, widgetId);
        router.finish();
    }

    /**
     * WidgetConfigInteractorOutput
     */

    @Override
    public void onLoadUsers(List<Driver> driverList) {
        getView().showUsers(createUserDisplayItems(driverList));
    }

    /**
     * private
     */

    List<DriverDisplayItem> createUserDisplayItems(List<Driver> drivers) {
        List<DriverDisplayItem> displayList = new ArrayList();

        if (drivers == null) {
            return displayList;
        }

        DriverDisplaySerializer serializer = new DriverDisplaySerializer();

        for (Driver driver : drivers) {
            displayList.add(serializer.create(context, driver));
        }

        return displayList;
    }
}
