package com.kotkovets.fines.modules.widget.main.applicationlogic.interactor;

import com.kotkovets.fines.common.model.Fine;
import com.kotkovets.fines.common.model.Driver;

/**
 * Created by igork on 4/23/16.
 */
public interface WidgetInteractorOutputInterface {
    void onLoadUser(Driver driver);
    void onLoadResponse(Fine fine);
}
