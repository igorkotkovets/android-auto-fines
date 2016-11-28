package com.kotkovets.fines.modules.list.view;

/**
 * Created by igork on 4/23/16.
 */
public interface ListViewCellInterface {
    void set(String name, String surname, String patronymic);
    void set(String series, String number);
    void setLoadingAnimationEnabled(boolean animating);
    void setFineResult(String text);
    void setUpdateDate(String text);
}
