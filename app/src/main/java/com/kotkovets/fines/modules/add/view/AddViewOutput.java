package com.kotkovets.fines.modules.add.view;

/**
 * Created by igork on 2/7/16.
 */
public interface AddViewOutput {
    void didUpdateView();
    void didTriggerSaveAction(String name, String surname, String patronymic, String series, String number);
}
