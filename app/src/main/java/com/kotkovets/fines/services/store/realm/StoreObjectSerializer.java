package com.kotkovets.fines.services.store.realm;

/**
 * Created by igork on 4/28/16.
 */
public interface StoreObjectSerializer<InputType, OutputType> {
    OutputType create(InputType object);
}
