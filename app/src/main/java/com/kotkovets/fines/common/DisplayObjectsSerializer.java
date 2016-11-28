package com.kotkovets.fines.common;

import android.content.Context;

/**
 * Created by igork on 4/23/16.
 */
public interface DisplayObjectsSerializer<InputType, OutputType> {
    OutputType create(Context context, InputType object);
}
