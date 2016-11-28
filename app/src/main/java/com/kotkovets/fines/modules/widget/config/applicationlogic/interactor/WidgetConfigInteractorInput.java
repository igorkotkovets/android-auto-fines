package com.kotkovets.fines.modules.widget.config.applicationlogic.interactor;

/**
 * Created by igork on 4/24/16.
 */
public interface WidgetConfigInteractorInput {
    void loadUsers();
    void updateWidgetWithUserId(int userId, int widgetConfig);
}
