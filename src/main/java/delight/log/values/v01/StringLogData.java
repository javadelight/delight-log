package delight.log.values.v01;

import delight.async.properties.values.ExplicitInstanceOf;
import delight.json.JSON;
import delight.json.JSONArray;
import delight.json.ToJSON;
import delight.log.values.StringLog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StringLogData implements ExplicitInstanceOf, Serializable, StringLog, ToJSON {

    private static final long serialVersionUID = 1L;

    private final int maxCapacity;
    private List<String> entries;

    @Override
    public boolean instanceOf(final Class<?> type) {
        return type.equals(StringLog.class);
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

    @Override
    public JSON toJSON() {
        final JSONArray o = JSON.createArray();

        for (int i = entries.size() - 1; i >= 0; i--) {
            o.push(entries.get(i));
        }

        return o;
    }

    @Override
    public String toString() {
        return toJSON().render();
    }

}
