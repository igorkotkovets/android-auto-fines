package com.kotkovets.fines.services.store.realm;

import com.kotkovets.fines.common.model.Driver;
import com.kotkovets.fines.services.store.realm.model.DriverRealmObject;

/**
 * Created by igork on 4/23/16.
 */
public class DriverStoreSerializer implements StoreObjectSerializer<DriverRealmObject, Driver> {
    @Override
    public Driver create(DriverRealmObject object) {
        if (object == null) {
            return null;
        }

        return new Driver(object.getName(),
                object.getSurname(),
                object.getPatronymic(),
                object.getSeries(),
                object.getNumber(),
                object.getUniqueKey());
    }
}
