package de.mxro.async.log.tests;

import de.oehme.xtend.junit.JUnit;
import delight.async.properties.PropertyNode;
import delight.async.properties.PropertyOperation;
import delight.functional.Success;
import delight.log.jre.Logs;
import delight.log.values.StringLog;
import delight.promise.Promise;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.ArrayComparisonFailure;

@JUnit
@SuppressWarnings("all")
public class TestStringLog {
  @Test
  public void test() {
    final PropertyNode log = Logs.create();
    PropertyOperation<String> _string = Logs.string("log1", "entry 1");
    log.<String>record(_string);
    PropertyOperation<String> _string_1 = Logs.string("log1", "entry 2");
    log.<String>record(_string_1);
    PropertyOperation<String> _string_2 = Logs.string("log1", "entry 3");
    log.<String>record(_string_2);
    Promise<StringLog> _retrieve = log.<StringLog>retrieve("log1", StringLog.class);
    StringLog _get = _retrieve.get();
    List<String> _entries = _get.entries();
    int _size = _entries.size();
    TestStringLog.<Integer, Integer>operator_doubleArrow(Integer.valueOf(_size), Integer.valueOf(3));
    Promise<Success> _stop = log.stop();
    _stop.get();
  }
  
  @Test
  public void test_overflow() {
    final PropertyNode log = Logs.create(20);
    IntegerRange _upTo = new IntegerRange(1, 100);
    for (final Integer i : _upTo) {
      PropertyOperation<String> _string = Logs.string("log1", ("entry " + i));
      log.<String>record(_string);
    }
    Promise<StringLog> _retrieve = log.<StringLog>retrieve("log1", StringLog.class);
    StringLog _get = _retrieve.get();
    List<String> _entries = _get.entries();
    int _size = _entries.size();
    boolean _lessThan = (_size < 21);
    TestStringLog.<Boolean, Boolean>operator_doubleArrow(Boolean.valueOf(_lessThan), Boolean.valueOf(true));
    Promise<StringLog> _retrieve_1 = log.<StringLog>retrieve("log1", StringLog.class);
    StringLog _get_1 = _retrieve_1.get();
    List<String> _entries_1 = _get_1.entries();
    int _size_1 = _entries_1.size();
    boolean _greaterThan = (_size_1 > 8);
    TestStringLog.<Boolean, Boolean>operator_doubleArrow(Boolean.valueOf(_greaterThan), Boolean.valueOf(true));
    Promise<Success> _stop = log.stop();
    _stop.get();
  }
  
  @Test
  public void test_multithreading() {
    try {
      final PropertyNode log = Logs.create(18);
      final Thread t1 = new Thread() {
        @Override
        public void run() {
          try {
            IntegerRange _upTo = new IntegerRange(1, 20);
            for (final Integer i : _upTo) {
              {
                PropertyOperation<String> _string = Logs.string("log1", ("t1 entry " + i));
                log.<String>record(_string);
                Thread.sleep(1);
                Thread.yield();
              }
            }
          } catch (Throwable _e) {
            throw Exceptions.sneakyThrow(_e);
          }
        }
      };
      final Thread t2 = new Thread() {
        @Override
        public void run() {
          try {
            IntegerRange _upTo = new IntegerRange(1, 20);
            for (final Integer i : _upTo) {
              {
                PropertyOperation<String> _string = Logs.string("log1", ("t2 entry " + i));
                log.<String>record(_string);
                Thread.sleep(1);
                Thread.yield();
              }
            }
          } catch (Throwable _e) {
            throw Exceptions.sneakyThrow(_e);
          }
        }
      };
      final Thread t3 = new Thread() {
        @Override
        public void run() {
          try {
            IntegerRange _upTo = new IntegerRange(1, 20);
            for (final Integer i : _upTo) {
              {
                PropertyOperation<String> _string = Logs.string("log1", ("t3 entry " + i));
                log.<String>record(_string);
                Thread.sleep(1);
                Thread.yield();
              }
            }
          } catch (Throwable _e) {
            throw Exceptions.sneakyThrow(_e);
          }
        }
      };
      final Thread retrievet = new Thread() {
        @Override
        public void run() {
          try {
            IntegerRange _upTo = new IntegerRange(1, 10);
            for (final Integer i : _upTo) {
              {
                Promise<StringLog> _retrieve = log.<StringLog>retrieve("log1", StringLog.class);
                StringLog _get = _retrieve.get();
                List<String> _entries = _get.entries();
                int _size = _entries.size();
                boolean _lessThan = (_size < 21);
                TestStringLog.<Boolean, Boolean>operator_doubleArrow(Boolean.valueOf(_lessThan), Boolean.valueOf(true));
                Thread.sleep(2);
                Thread.yield();
              }
            }
          } catch (Throwable _e) {
            throw Exceptions.sneakyThrow(_e);
          }
        }
      };
      t1.start();
      t2.start();
      t3.start();
      retrievet.start();
      retrievet.join();
      t2.join();
      t2.join();
      t1.join();
      Promise<StringLog> _retrieve = log.<StringLog>retrieve("log1", StringLog.class);
      StringLog _get = _retrieve.get();
      List<String> _entries = _get.entries();
      int _size = _entries.size();
      boolean _lessThan = (_size < 20);
      TestStringLog.<Boolean, Boolean>operator_doubleArrow(Boolean.valueOf(_lessThan), Boolean.valueOf(true));
      Promise<StringLog> _retrieve_1 = log.<StringLog>retrieve("log1", StringLog.class);
      StringLog _get_1 = _retrieve_1.get();
      List<String> _entries_1 = _get_1.entries();
      int _size_1 = _entries_1.size();
      boolean _greaterThan = (_size_1 > 5);
      TestStringLog.<Boolean, Boolean>operator_doubleArrow(Boolean.valueOf(_greaterThan), Boolean.valueOf(true));
      Promise<Success> _stop = log.stop();
      _stop.get();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private static void assertArrayEquals(final Object[] expecteds, final Object[] actuals) {
    Assert.assertArrayEquals(expecteds, actuals);
  }
  
  private static void assertArrayEquals(final byte[] expecteds, final byte[] actuals) {
    Assert.assertArrayEquals(expecteds, actuals);
  }
  
  private static void assertArrayEquals(final char[] expecteds, final char[] actuals) {
    Assert.assertArrayEquals(expecteds, actuals);
  }
  
  private static void assertArrayEquals(final short[] expecteds, final short[] actuals) {
    Assert.assertArrayEquals(expecteds, actuals);
  }
  
  private static void assertArrayEquals(final int[] expecteds, final int[] actuals) {
    Assert.assertArrayEquals(expecteds, actuals);
  }
  
  private static void assertArrayEquals(final long[] expecteds, final long[] actuals) {
    Assert.assertArrayEquals(expecteds, actuals);
  }
  
  private static void assertArrayEquals(final String message, final Object[] expecteds, final Object[] actuals) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals);
  }
  
  private static void assertArrayEquals(final String message, final byte[] expecteds, final byte[] actuals) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals);
  }
  
  private static void assertArrayEquals(final String message, final char[] expecteds, final char[] actuals) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals);
  }
  
  private static void assertArrayEquals(final String message, final short[] expecteds, final short[] actuals) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals);
  }
  
  private static void assertArrayEquals(final String message, final int[] expecteds, final int[] actuals) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals);
  }
  
  private static void assertArrayEquals(final String message, final long[] expecteds, final long[] actuals) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals);
  }
  
  private static void assertArrayEquals(final double[] expecteds, final double[] actuals, final double delta) {
    Assert.assertArrayEquals(expecteds, actuals, delta);
  }
  
  private static void assertArrayEquals(final float[] expecteds, final float[] actuals, final float delta) {
    Assert.assertArrayEquals(expecteds, actuals, delta);
  }
  
  private static void assertArrayEquals(final String message, final double[] expecteds, final double[] actuals, final double delta) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals, delta);
  }
  
  private static void assertArrayEquals(final String message, final float[] expecteds, final float[] actuals, final float delta) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals, delta);
  }
  
  private static void assertEquals(final Object expected, final Object actual) {
    Assert.assertEquals(expected, actual);
  }
  
  private static void assertEquals(final long expected, final long actual) {
    Assert.assertEquals(expected, actual);
  }
  
  private static void assertEquals(final String message, final Object expected, final Object actual) {
    Assert.assertEquals(message, expected, actual);
  }
  
  private static void assertEquals(final String message, final long expected, final long actual) {
    Assert.assertEquals(message, expected, actual);
  }
  
  private static void assertEquals(final double expected, final double actual, final double delta) {
    Assert.assertEquals(expected, actual, delta);
  }
  
  private static void assertEquals(final String message, final double expected, final double actual, final double delta) {
    Assert.assertEquals(message, expected, actual, delta);
  }
  
  private static void assertFalse(final boolean condition) {
    Assert.assertFalse(condition);
  }
  
  private static void assertFalse(final String message, final boolean condition) {
    Assert.assertFalse(message, condition);
  }
  
  private static void assertNotNull(final Object object) {
    Assert.assertNotNull(object);
  }
  
  private static void assertNotNull(final String message, final Object object) {
    Assert.assertNotNull(message, object);
  }
  
  private static void assertNotSame(final Object unexpected, final Object actual) {
    Assert.assertNotSame(unexpected, actual);
  }
  
  private static void assertNotSame(final String message, final Object unexpected, final Object actual) {
    Assert.assertNotSame(message, unexpected, actual);
  }
  
  private static void assertNull(final Object object) {
    Assert.assertNull(object);
  }
  
  private static void assertNull(final String message, final Object object) {
    Assert.assertNull(message, object);
  }
  
  private static void assertSame(final Object expected, final Object actual) {
    Assert.assertSame(expected, actual);
  }
  
  private static void assertSame(final String message, final Object expected, final Object actual) {
    Assert.assertSame(message, expected, actual);
  }
  
  private static <T extends Object> void assertThat(final T actual, final Matcher<T> matcher) {
    Assert.<T>assertThat(actual, matcher);
  }
  
  private static <T extends Object> void assertThat(final String reason, final T actual, final Matcher<T> matcher) {
    Assert.<T>assertThat(reason, actual, matcher);
  }
  
  private static void assertTrue(final boolean condition) {
    Assert.assertTrue(condition);
  }
  
  private static void assertTrue(final String message, final boolean condition) {
    Assert.assertTrue(message, condition);
  }
  
  private static void fail() {
    Assert.fail();
  }
  
  private static void fail(final String message) {
    Assert.fail(message);
  }
  
  private static <T extends Object, U extends T> void operator_doubleArrow(final T actual, final U expected) {
    Assert.assertEquals(expected, actual);
  }
  
  private static <T extends Exception> void isThrownBy(final Class<T> expected, final Procedure0 block) {
    try {
    	block.apply();
    	Assert.fail("Expected a " + expected.getName());
    } catch (Exception e) {
    	Class<?> actual = e.getClass();
    	Assert.assertTrue(
    		"Expected a " + expected.getName() + " but got " + actual.getName(), 
    		expected.isAssignableFrom(actual)
    	);
    }
  }
}
