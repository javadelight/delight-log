package de.mxro.async.log.tests

import org.javadelight.log.jre.Logs
import org.javadelight.log.values.StringLog
import de.oehme.xtend.junit.JUnit
import org.junit.Test

import static extension de.mxro.async.log.tests.TestStringLog.*


@JUnit
class TestStringLog {

	@Test
	def void test() {

		val log = Logs.create

		log.record(Logs.string("log1", "entry 1"));
		log.record(Logs.string("log1", "entry 2"));
		log.record(Logs.string("log1", "entry 3"));

		log.retrieve("log1", StringLog).get.entries.size => 3

		log.stop.get

	}
	

	@Test
	def void test_overflow() {

		val log = Logs.create(20)

		for (i : 1 .. 100) {
			log.record(Logs.string("log1", "entry " + i));

		}

		(log.retrieve("log1", StringLog).get.entries.size < 21) => true
		(log.retrieve("log1", StringLog).get.entries.size > 8) => true

		log.stop.get

	}

	@Test
	def void test_multithreading() {

		val log = Logs.create(18)

		val t1 = new Thread() {

			override run() {
				for (i : 1 .. 20) {
					log.record(Logs.string("log1", "t1 entry " + i));
					Thread.sleep(1)
					Thread.yield()
				}
			}

		};
		
		val t2 = new Thread() {

			override run() {
				for (i : 1 .. 20) {
					log.record(Logs.string("log1", "t2 entry " + i));
					Thread.sleep(1)
					Thread.yield()
				}
			}

		};
		
		val t3 = new Thread() {

			override run() {
				for (i : 1 .. 20) {
					log.record(Logs.string("log1", "t3 entry " + i));
					Thread.sleep(1)
					Thread.yield()
				}
			}

		};
		
		val retrievet = new Thread() {

			override run() {
				for (i : 1 .. 10) {
					(log.retrieve("log1", StringLog).get.entries.size < 21) => true
					Thread.sleep(2)
					Thread.yield()
				}
			}

		};

		t1.start
		t2.start
		t3.start
		retrievet.start
		
		retrievet.join
		t2.join
		t2.join
		t1.join()

		(log.retrieve("log1", StringLog).get.entries.size < 20) => true
		(log.retrieve("log1", StringLog).get.entries.size > 5) => true

		log.stop.get

	}

}
