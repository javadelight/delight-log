package delight.log.jre;

import delight.async.properties.PropertiesCommon;
import delight.async.properties.PropertyNode;
import delight.async.properties.jre.Properties;
import delight.factories.Configuration;
import delight.factories.Dependencies;
import delight.factories.Factory;
import delight.log.LogsCommon;
import delight.log.LogsConfiguration;

public class Logs extends LogsCommon {

    public static PropertyNode create() {
        final int defaultMaxCapacity = 20;
        return create(defaultMaxCapacity);
    }

    public static PropertyNode create(final int capacity) {

        return Properties.create(PropertiesCommon.compositeFactory(PropertiesCommon.defaultFactory(),
                logFactory(capacity)));
    }

    public static Factory<?, ?, ?> createLogsFactory() {
        return new Factory<PropertyNode, LogsConfiguration, Dependencies>() {

            @Override
            public boolean canInstantiate(final Configuration conf) {

                return conf instanceof LogsConfiguration;
            }

            @Override
            public PropertyNode create(final LogsConfiguration conf, final Dependencies dependencies) {

                return Logs.create(conf.getMaxCapacity());
            }

        };
    }
}
