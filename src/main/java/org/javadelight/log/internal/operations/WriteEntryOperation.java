package org.javadelight.log.internal.operations;

import delight.async.properties.PropertyData;
import delight.async.properties.operations.PropertyOperationWithId;

import org.javadelight.log.values.StringLog;

public class WriteEntryOperation extends PropertyOperationWithId<String> {

    private final String message;

    @Override
    public String perform(final PropertyData data) {

        data.get(id, StringLog.class).add(message);

        return message;

    }

    public WriteEntryOperation(final String message) {
        super();
        this.message = message;
    }

}
