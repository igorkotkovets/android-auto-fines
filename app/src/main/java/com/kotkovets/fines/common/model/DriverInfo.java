package com.kotkovets.fines.common.model;

/**
 * Created by igork on 5/25/16.
 */
public class DriverInfo {

    private Driver driver;
    private Fine fine;


    public Driver getDriver() {
        return driver;
    }
    public Fine getFine() {
        return fine;
    }


    public DriverInfo(Driver driver, Fine fine) {
        this.driver = driver;
        this.fine = fine;
    }
}


