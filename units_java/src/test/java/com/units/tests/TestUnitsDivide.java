package com.units.tests;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.units.angle.Degrees;
import com.units.duration.Minutes;
import com.units.duration.Seconds;
import com.units.length.Meters;
import com.units.length.Yards;
import com.units.speed.MetersPerSecond;

public class TestUnitsDivide {

	private void checkResult(MetersPerSecond actual, MetersPerSecond expected) {
		assertTrue(actual + " != " + expected, actual.equals(expected));
	}
	
	@Test
	public void testDivideMetersBySecondsToCreateMetersPerSecond() {
		Meters meters = new Meters(8);
		Seconds seconds = new Seconds(2);
		checkResult(
				MetersPerSecond.divide(meters, seconds),
				new MetersPerSecond(4));
	}
	
	@Test
	public void testDivideYardsBySecondsToCreateMetersPerSecond() {
		Yards yards = new Yards(8);
		Seconds seconds = new Seconds(2);
		checkResult(
				MetersPerSecond.divide(yards, seconds),
				new MetersPerSecond(3.6576));
	}
	
	@Test
	public void testDivideMetersByMinutesToCreateMetersPerSecond() {
		Meters meters = new Meters(8);
		Minutes minutes = new Minutes(2);
		checkResult(
				MetersPerSecond.divide(meters, minutes),
				new MetersPerSecond(0.0666666666666666));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCantDivideDegreesBySecondsToCreateMetersPerSecond() {
		MetersPerSecond.divide(new Degrees(8), new Seconds(2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCantDivideMetersByDegreesToCreateMetersPerSecond() {
		MetersPerSecond.divide(new Meters(8), new Degrees(2));
	}

}
