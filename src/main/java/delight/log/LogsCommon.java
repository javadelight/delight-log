package delight.log;

import delight.async.properties.PropertiesCommon;
import delight.async.properties.PropertyFactory;
import delight.async.properties.PropertyNode;
import delight.async.properties.PropertyOperation;
import delight.factories.Configuration;
import delight.factories.Dependencies;
import delight.factories.Factory;
import delight.log.internal.LogFactory;
import delight.log.internal.operations.WriteEntryOperation;

/**
 * <p>
 * Key methods and factories for Async Log library.
 * 
 * @author <a href="http://www.mxro.de">Max Rohde</a>
 *
 */
public class LogsCommon extends PropertiesCommon {

    public static PropertyNode createUnsafe() {
        final int defaultMaxCapacity = 40;
        return PropertiesCommon.createUnsafe(PropertiesCommon.compositeFactory(PropertiesCommon.defaultFactory(),
                logFactory(defaultMaxCapacity)));
    }

    public static PropertyNode createUnsafe(final int defaultMaxCapacity) {
        return PropertiesCommon.createUnsafe(PropertiesCommon.compositeFactory(PropertiesCommon.defaultFactory(),
                logFactory(defaultMaxCapacity)));
    }

    public static PropertyFactory logFactory(final int defaultMaxCapacity) {
        return new LogFactory(defaultMaxCapacity);
    }

    public static PropertyOperation<String> string(final String id, final String message) {
        return new WriteEntryOperation(message).setId(id);
    }

    public static PropertyOperation<String> string(final Object context, final String message) {
        return string(context.getClass().getName() + ":" + Math.abs(System.identityHashCode(context)), message);
    }

    public static Factory<?, ?, ?> createUnsafeLogsFactory() {
        return new Factory<PropertyNode, LogsConfiguration, Dependencies>() {

            @Override
            public boolean canInstantiate(final Configuration conf) {

                return conf instanceof LogsConfiguration;
            }

            @Override
            public PropertyNode create(final LogsConfiguration conf, final Dependencies dependencies) {

                return LogsCommon.createUnsafe(conf.getMaxCapacity());
            }

        };
    }

}
