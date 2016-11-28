package com.kotkovets.fines.modules.widget.main.userinterface.presentor;

import android.content.Context;

import com.kotkovets.fines.common.DisplayObjectsSerializer;
import com.kotkovets.fines.common.FineDisplaySerializer;
import com.kotkovets.fines.common.DriverDisplaySerializer;
import com.kotkovets.fines.common.display.FineDisplayItem;
import com.kotkovets.fines.common.display.DriverDisplayItem;
import com.kotkovets.fines.common.model.Fine;
import com.kotkovets.fines.common.model.Driver;
import com.kotkovets.fines.modules.widget.main.applicationlogic.interactor.WidgetInteractor;
import com.kotkovets.fines.modules.widget.main.applicationlogic.interactor.WidgetInteractorInputInterface;
import com.kotkovets.fines.modules.widget.main.applicationlogic.interactor.WidgetInteractorOutputInterface;
import com.kotkovets.fines.modules.widget.main.moduleinterface.WidgetModuleInterface;
import com.kotkovets.fines.modules.widget.main.userinterface.view.WidgetViewInterface;
import com.kotkovets.fines.modules.widget.main.applicationlogic.service.WidgetPresentorListener;

/**
 * Created by igork on 2/22/16.
 */
public class WidgetPresentor implements WidgetModuleInterface, WidgetInteractorOutputInterface {
    WidgetViewInterface widgetView;
    Context context;
    WidgetPresentorListener updateListener;
    WidgetInteractorInputInterface widgetInteractor;
    DisplayObjectsSerializer<Driver, DriverDisplayItem> userSerializer;
    DisplayObjectsSerializer<Fine, FineDisplayItem> responseSerializer;


    public WidgetPresentor(Context context, WidgetViewInterface widgetView, int widgetId, WidgetPresentorListener listener) {
        this.context = context;
        this.widgetView = widgetView;
        this.updateListener = listener;
        WidgetInteractor widgetInteractor = new WidgetInteractor(context, widgetId, this);
        this.widgetInteractor = widgetInteractor;

        this.userSerializer = new DriverDisplaySerializer();
        this.responseSerializer = new FineDisplaySerializer();
    }

    @Override
    public void onActionLoadData() {
        widgetInteractor.loadUser();
        widgetInteractor.loadResponse();
    }

    @Override
    public void onActionCheck() {
        widgetInteractor.checkOnRemote();

    }

    /*
    * WidgetInteractorOutputInterface
     */

    @Override
    public void onLoadUser(Driver driver) {
        this.widgetView.displayUser(this.userSerializer.create(context, driver));
        this.updateListener.onFinishUpdate(this.context, this.widgetView.getRemoteViews());
    }

    @Override
    public void onLoadResponse(Fine fine) {
        this.widgetView.displayResponse(this.responseSerializer.create(context, fine));
        this.updateListener.onFinishUpdate(this.context, this.widgetView.getRemoteViews());
    }
}
