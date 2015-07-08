package delight.log;

import delight.factories.Configuration;

public class LogsConfiguration implements Configuration {

    private int maxCapacity;

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(final int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public LogsConfiguration() {
        super();
        this.maxCapacity = 20;
    }

}
