package de.mxro.async.log.examples;

import delight.async.properties.PropertyNode;

import org.javadelight.log.jre.Logs;
import org.javadelight.log.values.StringLog;

public class WriteAndReadLog {

    public static void main(final String[] args) {

        final PropertyNode logs = Logs.create();

        logs.record(Logs.string("log1", "entry 1"));
        logs.record(Logs.string("log1", "entry 2"));

        final String log1 = logs.retrieve("log1", StringLog.class).get().toString();

        System.out.println(log1);

        logs.stop().get();

    }

}
