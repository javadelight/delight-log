package org.javadelight.log.internal;

import delight.async.properties.PropertyFactory;

import org.javadelight.log.values.StringLog;
import org.javadelight.log.values.v01.StringLogData;

public class LogFactory implements PropertyFactory {

    private final int defaultMaxCapacity;

    public LogFactory(final int defaultMaxCapacity) {
        super();
        this.defaultMaxCapacity = defaultMaxCapacity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T create(final Class<T> type) {

        if (type.equals(StringLog.class)) {
            return (T) new StringLogData(defaultMaxCapacity);
        }

        return null;
    }

}
