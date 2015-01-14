package de.mxro.async.log.values.v01;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.mxro.async.log.values.StringLog;
import de.mxro.async.properties.values.PropertyValue;

public class StringLogData implements PropertyValue, Serializable, StringLog {

    private static final long serialVersionUID = 1L;

    private final int maxCapacity;
    private List<String> entries;

    @Override
    public boolean is(final Class<?> type) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<String> entries() {
        return this.entries;
    }

    @Override
    public void add(final String message) {
        if (entries.size() > maxCapacity) {
            final List<String> oldEntries = this.entries;
            this.entries = new ArrayList<String>(maxCapacity);

            for (int i = Math.round(oldEntries.size() / 2); i < oldEntries.size(); i++) {
                this.entries.add(oldEntries.get(i));
            }
        }

        entries.add(message);
    }

    public StringLogData(final int maxCapacity) {
        super();
        this.maxCapacity = maxCapacity;
        this.entries = new ArrayList<String>(maxCapacity);
    }

}
