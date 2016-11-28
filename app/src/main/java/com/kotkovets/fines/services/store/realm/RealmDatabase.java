package com.kotkovets.fines.services.store.realm;

import com.kotkovets.fines.common.model.Fine;
import com.kotkovets.fines.common.model.Driver;
import com.kotkovets.fines.services.store.DatabaseServiceInterface;
import com.kotkovets.fines.services.store.realm.model.DriverRealmObject;
import com.kotkovets.fines.services.store.realm.model.FineRealmObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by igork on 4/2/16.
 */
public class RealmDatabase implements DatabaseServiceInterface {
    Realm realm;
    StoreObjectSerializer<DriverRealmObject, Driver> userSerializer;
    StoreObjectSerializer<FineRealmObject, Fine> responseSerializer;


    public RealmDatabase() {
        realm = Realm.getDefaultInstance();
        userSerializer = new DriverStoreSerializer();
        responseSerializer = new FineStoreSerializer();
    }

    @Override
    public boolean saveUser(Driver driverStoreObject) {
        DriverRealmObject driverRealmObject = new DriverRealmObject();
        driverRealmObject.setName(driverStoreObject.getName());
        driverRealmObject.setSurname(driverStoreObject.getSurname());
        driverRealmObject.setPatronymic(driverStoreObject.getPatronymic());
        driverRealmObject.setNumber(driverStoreObject.getNumber());
        driverRealmObject.setSeries(driverStoreObject.getSeries());
        driverRealmObject.setUniqueKey(driverStoreObject.getUniqueKey());

        realm.beginTransaction();
            realm.copyToRealmOrUpdate(driverRealmObject);
        realm.commitTransaction();
        return true;
    }

    @Override
    public List<Driver> loadAllDrivers() {
        RealmResults<DriverRealmObject> realmObjects = realm.where(DriverRealmObject.class).findAll();
        List<Driver> list = new ArrayList<>();

        if (realmObjects == null) {
            return null;
        }

        for (DriverRealmObject realmObject: realmObjects) {
            list.add(userSerializer.create(realmObject));
        }

        return list;
    }

    @Override
    public Driver loadDriver(int userId) {
        DriverRealmObject driverRealmObject = realm.where(DriverRealmObject.class).equalTo("uniqueKey", userId).findFirst();

        return userSerializer.create(driverRealmObject);
    }

    @Override
    public Driver deleteDriverIfExists(int uniqueUserId) {
        DriverRealmObject driverRealmObject = realm.where(DriverRealmObject.class).equalTo("uniqueKey", uniqueUserId).findFirst();
        Driver driver = userSerializer.create(driverRealmObject);
        RealmResults<FineRealmObject> responses = realm.where(FineRealmObject.class).equalTo("userUniqueKey", uniqueUserId).findAll();

        realm.beginTransaction();
            driverRealmObject.removeFromRealm();
            for (int i = 0; i < responses.size(); i++) {
                responses.get(i).removeFromRealm();
            }
        realm.commitTransaction();

        return driver;
    }

    @Override
    public boolean saveFine(Fine fineStoreObject, int userUniqueKey) {
        FineRealmObject fineRealmObject = new FineRealmObject();
        fineRealmObject.setMessage(fineStoreObject.getMessage());
        fineRealmObject.setUniqueKey(fineStoreObject.getUniqueKey());
        fineRealmObject.setDate(fineStoreObject.getDate());
        fineRealmObject.setUserUniqueKey(userUniqueKey);

        realm.beginTransaction();
            realm.copyToRealmOrUpdate(fineRealmObject);
        realm.commitTransaction();
        return true;
    }

    @Override
    public Fine loadFine(int userUniqueKey) {
        FineRealmObject fineRealmObject = realm.where(FineRealmObject.class).equalTo("userUniqueKey", userUniqueKey).findFirst();
        return responseSerializer.create(fineRealmObject);
    }
}
