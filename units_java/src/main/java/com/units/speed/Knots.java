/* This file is auto generated.
 * DO NOT EDIT IT MANUALY!
 */
package com.units.speed;

import com.units.Unit;
import com.units.internal.NumericValue;
import com.units.internal.Ratio;

public class Knots extends NumericValue implements Speed{

	public static final double _scale = 0.514444773689 * MetersPerSecond._scale;
	
	public Knots(double value) {
		super(value);
	}

	@Override
	public String pluralName() {
		return "knots";
	}
	
	@Override
	public double scale() {
		return _scale;
	}
	
	public static Knots zero() {
		return new Knots(0);
	}
	
	public static Knots one() {
		return new Knots(1);
	}
	
	public Knots plus(Knots other) {
		return new Knots(value() + other.value());
	}
	
	public Knots minus(Knots other) {
		return new Knots(value() - other.value());
	}
	
	public Knots opposite() {
		return new Knots(-value());
	}

	public Knots multiplyByScalar(double scalar) {
		return new Knots(scalar * value());
	}
	
	public Knots divideByScalar(double scalar) {
		return new Knots(value() / scalar);
	}
	
	public boolean equals(Knots other) {
		if (other == null)
			return false;
		return almostEqualsValue(other.value());
	}
	
	private static Knots castFromScale(double value, double scale) {
		return new Knots(value * scale / _scale);
	}

	public static Knots castFrom(Speed other) {
		return castFromScale(other.value(), other.scale());
	}

	public static Knots divide(Unit unit1, Unit unit2) {
		Ratio resultTypeCode = unit1.typeCode().divide(unit2.typeCode());
		if (!resultTypeCode.equals(_typeCode))
			throw new IllegalArgumentException("Illigal division");
		return castFromScale(
				unit1.value() / unit2.value(),
				unit1.scale() / unit2.scale());
	}
}