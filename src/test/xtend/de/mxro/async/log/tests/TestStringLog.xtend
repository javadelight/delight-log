package de.mxro.async.log.tests

import de.mxro.async.log.jre.Logs
import de.mxro.async.log.values.StringLog
import de.oehme.xtend.junit.JUnit
import org.junit.Test

@JUnit
class TestStringLog {

	@Test
	def void test() {

		val log = Logs.create

		log.record(Logs.entry("log1", "entry 1"));
		log.record(Logs.entry("log1", "entry 2"));
		log.record(Logs.entry("log1", "entry 3"));

		log.retrieve("log1", StringLog).get.entries.size => 3

		log.stop.get

	}

	@Test
	def void test_filled() {

		val log = Logs.create(20)

		for (i : 1 .. 100) {
			log.record(Logs.entry("log1", "entry " + i));

		}

		(log.retrieve("log1", StringLog).get.entries.size < 21) => true

		log.stop.get

	}

	@Test
	def void test_multithreading() {

		val log = Logs.create(18)

		val t1 = new Thread() {

			override run() {
				for (i : 1 .. 20) {
					log.record(Logs.entry("log1", "t1 entry " + i));
					Thread.sleep(1)
					Thread.yield()
				}
			}

		};
		
		val t2 = new Thread() {

			override run() {
				for (i : 1 .. 20) {
					log.record(Logs.entry("log1", "t2 entry " + i));
					Thread.sleep(1)
					Thread.yield()
				}
			}

		};
		
		val t3 = new Thread() {

			override run() {
				for (i : 1 .. 20) {
					log.record(Logs.entry("log1", "t3 entry " + i));
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
		t1.join


		println(log.retrieve("log1").get)

		log.stop.get

	}

}