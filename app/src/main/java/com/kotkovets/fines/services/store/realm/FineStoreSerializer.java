package com.kotkovets.fines.services.store.realm;

import com.kotkovets.fines.common.model.Fine;
import com.kotkovets.fines.services.store.realm.model.FineRealmObject;

/**
 * Created by igork on 4/23/16.
 */
public class FineStoreSerializer implements StoreObjectSerializer<FineRealmObject, Fine> {
    @Override
    public Fine create(FineRealmObject object) {
        if (object == null) {
            return null;
        }

        return new Fine(object.getMessage(),
                object.getDate(),
                object.getUniqueKey());
    }
}
