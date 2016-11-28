package com.kotkovets.fines.services.network;

/**
 * Created by igork on 2/7/16.
 */
public interface NetworkServiceInputInterface {
    void performCheck(String name, String surname, String patronymic, String series, String number, NetworkServiceOutputInterface listener);
}
